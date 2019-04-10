/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class for power expression.
 */
public class Pow extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param e1 the first expression.
     * @param e2 the secend expression.
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     *
     * @param var1 the first expression.
     * @param e2   the secend expression.
     */
    public Pow(String var1, Expression e2) {
        super(var1, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   the first expression.
     * @param var2 the secend expression.
     */

    public Pow(Expression e1, String var2) {
        super(e1, var2);
    }

    /**
     * Constructor.
     *
     * @param num the first expression.
     * @param e2  the secend expression.
     */
    public Pow(double num, Expression e2) {
        super(num, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   the first expression.
     * @param num2 the secend expression.
     */
    public Pow(Expression e1, double num2) {
        super(e1, num2);
    }

    /**
     * Constructor.
     *
     * @param var the first expression.
     * @param num the secend expression.
     */
    public Pow(String var, double num) {
        super(var, num);
    }

    /**
     * Constructor.
     *
     * @param num1 the first expression.
     * @param num2 the secend expression.
     */
    public Pow(double num1, double num2) {
        super(num1, num2);
    }

    /**
     * Constructor.
     *
     * @param num the first expression.
     * @param var the secend expression.
     */
    public Pow(double num, String var) {
        super(num, var);
    }

    /**
     * Constructor.
     *
     * @param var1 the first expression.
     * @param var2 the secend expression.
     */
    public Pow(String var1, String var2) {
        super(var1, var2);
    }

    /**
     * Evaluate its value for a given variable assignment to numbers.
     *
     * @param assignment is the map of the vars.
     * @return the answer of the expression.
     * @throws Exception if evalute is var without map.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Double answer = Math.pow(getE1().simplify().evaluate(assignment), getE2().simplify().evaluate(assignment));
        // minus in sqrt
        if (Double.isNaN(answer)) {
            throw new Exception("minus in sqrt");
        }
        return answer;
    }

    /**
     * the function ger expression (expression or num or var)
     * and put it in our expression.
     *
     * @param var        is the var to replace with the expression.
     * @param expression the expression to replace.
     * @return the new expression after replace.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("(" + getE1() + "^" + getE2() + ")");
    }

    /**
     * @return reverse of toString.
     */
    @Override
    public String toStringRevers() {
        return this.toString();
    }

    @Override
    public Expression differentiate(String var) {
        try {
            //f^g
            Expression pow = this;
            //f'*g/f
            Expression plusF = new Mult(getE1().differentiate(var), new Div(getE2(), getE1()));
            Expression plusS = new Mult(getE2().differentiate(var), new Log("e", getE1()));
            return new Mult(pow, new Plus(plusF, plusS));
        } catch (Exception e) {
            System.out.println("Error in pow diff");
        }
        return null;
    }

    @Override
    public Expression simplify() {
        try {
            Double answer = Math.pow(getE1().evaluate(), getE2().evaluate());
            if (!Double.isNaN(answer)) {
                return new Num(Math.pow(getE1().evaluate(), getE2().evaluate()));
            }
        } catch (Exception e) {
            ;
        }
        try {
            double d = getE1().evaluate();
            setE1(new Num(d));
            if (d == 0) {
                return new Num(0);
            }
            if (d == 1) {
                return new Num(1);
            }
        } catch (Exception e2) {
            ;
        }
        try {
            double d = getE2().evaluate();
            setE2(new Num(d));
            if (d == 0) {
                return new Num(1);
            }
            if (d == 1) {
                return getE1().simplify();
            }
        } catch (Exception e3) {
            //bonus
            //(a^b)^c --> a^(b*c)
            if (getE1() instanceof Pow) {
                Expression a = ((Pow) getE1()).getE1().simplify();
                Expression b = ((Pow) getE1()).getE2().simplify();
                Expression c = getE2().simplify();
                return new Pow(a, new Mult(b, c)).simplify();
            }
        }
        //x^-1 >== 1/x ||  x^-5 >== 1/x^5
        try {
            double d = getE2().evaluate();
            if (d < 0) {
                return new Div(1, new Pow(getE1(), new Num(-d).simplify()).simplify()).simplify();
            }
        } catch (Exception e1) {
            if (getE2() instanceof Neg) {
                return new Div(1, new Pow(getE1(),
                        new Neg(getE2().simplify()).simplify()).simplify()).simplify();
            }
        }
        //bonus: a^(log(a,b) --> b
        if (getE2() instanceof Log) {
            if (((Log) getE2().simplify()).getE1().simplify().toString().equals(getE1().simplify().toString())) {
                return ((Log) getE2()).getE2().simplify();
            }
        }

        Expression e = new Pow(getE1().simplify(), getE2().simplify());
        return e;

    }
}