/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class of nultify.
 */
public class Mult extends BinaryExpression {

    /**
     * Constructor.
     *
     * @param e1 the first Expression.
     * @param e2 the sec expression.
     */
    public Mult(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     *
     * @param var1 the first Expression.
     * @param e2   the sec expression.
     */
    public Mult(String var1, Expression e2) {
        super(var1, e2);

    }

    /**
     * Constructor.
     *
     * @param e1   the first Expression.
     * @param var2 the sec expression.
     */
    public Mult(Expression e1, String var2) {
        super(e1, var2);
    }

    /**
     * Constructor.
     *
     * @param num the first Expression.
     * @param e2  the sec expression.
     */
    public Mult(double num, Expression e2) {
        super(num, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   the first Expression.
     * @param num2 the sec expression.
     */
    public Mult(Expression e1, double num2) {
        super(e1, num2);
    }

    /**
     * Constructor.
     *
     * @param var the first Expression.
     * @param num the sec expression.
     */
    public Mult(String var, double num) {
        super(var, num);
    }

    /**
     * Constructor.
     *
     * @param num1 the first Expression.
     * @param num2 the sec expression.
     */
    public Mult(double num1, double num2) {
        super(num1, num2);
    }

    /**
     * Constructor.
     *
     * @param num the first Expression.
     * @param var the sec expression.
     */
    public Mult(double num, String var) {
        super(num, var);
    }

    /**
     * Constructor.
     *
     * @param var1 the first Expression.
     * @param var2 the sec expression.
     */
    public Mult(String var1, String var2) {
        super(var1, var2);
    }

    /**
     * Evaluate its value for a given variable assignment to numbers.
     *
     * @param assignment is the map of the vars.
     * @return the answer of the expression.
     * @throws Exception if var not in map.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getE1().simplify().evaluate(assignment) * getE2().simplify().evaluate(assignment));
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
        return new Mult(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("(" + getE1() + " * " + getE2() + ")");
    }

    /**
     * @return reverse of toString.
     */
    @Override
    public String toStringRevers() {
        return ("(" + getE2() + " * " + getE1() + ")");
    }

    @Override
    public Expression differentiate(String var) {
        Expression start = new Mult(getE1().differentiate(var), getE2());
        Expression end = new Mult(getE1(), getE2().differentiate(var));
        return new Plus(start, end);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(getE1().evaluate() * getE2().evaluate());
        } catch (Exception e) {
            try {
                if (getE1().evaluate() == 1) {
                    return getE2().simplify();
                }
            } catch (Exception e2) {
                if (getE1().toString().equals("1.0")) {
                    return getE2().simplify();
                }
            }
            try {
                if (getE1().evaluate() == 0) {
                    return new Num(0);
                }
            } catch (Exception e2) {
                if (getE1().toString().equals("0.0")) {
                    return new Num(0);
                }
            }
            try {
                if (getE2().evaluate() == 1) {
                    return getE1().simplify();
                }
                if (getE2().evaluate() == 0) {
                    return new Num(0);
                }
            } catch (Exception e2) {
                if (getE2().toString().equals("1.0")) {
                    return getE1().simplify();
                }
                if (getE2().toString().equals("0.0")) {
                    return new Num(0);
                }
            }
        }
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();

        //bonus
        //x*x ==> x^2
        if (e1.toString().equals(e2.toString())) {
            return new Pow(e1, 2).simplify();
        }
        // x * 2 =>> 2 * x
        if ((e1 instanceof Var) && !(e2 instanceof Var)) {
            return new Mult(e2, e1).simplify();
        }
        //2x * 3x = 6*(x^2)
        if (e1 instanceof Mult && e2 instanceof Mult) {
            if (((Mult) e1).getE2().toString().equals(((Mult) e2).getE2().toString())) {
                return new Mult(new Mult(((Mult) e1).getE1(), ((Mult) e2).getE1()).simplify(),
                        new Pow(((Mult) e1).getE2(), 2).simplify()).simplify();
            }
        }
        //x^a * x^b =>> x^(a+b)
        if (e1 instanceof Pow && e2 instanceof Pow) {
            if (((Pow) e1).getE1().toString().equals(((Pow) e2).getE1().toString())) {
                return new Pow(((Pow) e1).getE1(),
                        new Plus(((Pow) e1).getE2(), ((Pow) e2).getE2()).simplify()).simplify();
            }
            //x^a * y^a  ==> (x*y)^a
            if (((Pow) e1).getE2().toString().equals(((Pow) e2).getE2().toString())) {
                return new Pow(new Mult(((Pow) e1).getE1(),
                        ((Pow) e2).getE1()).simplify(), ((Pow) e1).getE2()).simplify();
            }
        }
        //check for re-Simplify
        if (e1.toString().equals("0.0") || e1.toString().equals("1.0")
                || e2.toString().equals("0.0") || e2.toString().equals("1.0")) {
            return new Mult(getE1().simplify(), getE2().simplify()).simplify();
        }
        return new Mult(getE1().simplify(), getE2().simplify());
    }
}
