/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * class of divimenet.
 */
public class Div extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param e1 is the first Expression.
     * @param e2 is the secend Expression.
     */
    public Div(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first Expression.
     * @param e2   is the secend Expression.
     */
    public Div(String var1, Expression e2) {
        super(var1, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first Expression.
     * @param var2 is the secend Expression.
     */
    public Div(Expression e1, String var2) {
        super(e1, var2);
    }

    /**
     * Constructor.
     *
     * @param num is the first Expression.
     * @param e2  is the secend Expression.
     */
    public Div(double num, Expression e2) {
        super(num, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first Expression.
     * @param num2 is the secend Expression.
     */
    public Div(Expression e1, double num2) {
        super(e1, num2);
    }

    /**
     * Constructor.
     *
     * @param var is the first Expression.
     * @param num is the secend Expression.
     */
    public Div(String var, double num) {
        super(var, num);
    }

    /**
     * Constructor.
     *
     * @param num1 is the first Expression.
     * @param num2 is the secend Expression.
     */
    public Div(double num1, double num2) {
        super(num1, num2);
    }

    /**
     * Constructor.
     *
     * @param num is the first Expression.
     * @param var is the secend Expression.
     */
    public Div(double num, String var) {
        super(num, var);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first Expression.
     * @param var2 is the secend Expression.
     */
    public Div(String var1, String var2) {
        super(var1, var2);
    }

    /**
     * Evaluate its value for a given variable assignment to numbers.
     *
     * @param assignment is the map of the vars.
     * @return the answer of the expression.
     * @throws Exception if var isnt in map.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            Expression e1 = getE1().simplify();
            Expression e2 = getE2().simplify();
            double checkZero = e2.evaluate(assignment);
            if (checkZero == 0) {
                throw new Exception("you try to divide in zero");
            }
            return e1.simplify().evaluate(assignment) / checkZero;
        } catch (Exception e) {
            throw new Exception("you try to divide in 0!");
        }
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

        return new Div(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("(" + getE1() + " / " + getE2() + ")");
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
        Expression up1 = new Mult(getE1().differentiate(var), getE2());
        Expression up2 = new Mult(getE1(), getE2().differentiate(var));
        Expression down = new Pow(getE2(), 2);
        return new Div(new Minus(up1, up2), down);
    }

    @Override
    public Expression simplify() {
        try {
            double e = getE1().evaluate();
            if (e == 0) {
                return new Num(0);
            }
        } catch (Exception e) {
            if (getE1().toString().equals("0.0")) {
                return new Num(0);
            }
        }
        try {
            if (getE1().evaluate() == getE2().evaluate()) {
                return new Num(1);
            }
        } catch (Exception e) {
            if (getE1().toString().equals(getE2().toString())) {
                return new Num(1);
            }
            if (getE1().toStringRevers().equals(getE2().toString())) {
                return new Num(1);
            }
        }
        try {
            if (getE2().evaluate() == 1) {
                return getE1().simplify();
            }
        } catch (Exception e) {
            if (getE2().toString().equals("1.0")) {
                return getE1().simplify();
            }
        }
        try {
            if (getE2().evaluate() == 0) {
                throw new Exception("divide by 0!");
            }
        } catch (Exception e) {
            if (getE2().toString().equals("0.0")) {
                return new Div(getE1().simplify(), 0);
            }
        }
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        //bonus
        //(x/a)/(y/b) ==> (x*b)/(y*a)
        if (e1 instanceof Div && e2 instanceof Div) {
            Expression x = ((Div) e1).getE1().simplify();
            Expression y = ((Div) e2).getE1().simplify();
            Expression a = ((Div) e1).getE2().simplify();
            Expression b = ((Div) e2).getE2().simplify();
            return new Div(new Mult(x, b).simplify(), new Mult(y, a).simplify()).simplify();
        }
        //x^a / y^a ==> (x/y)^a
        if (e1 instanceof Pow && e2 instanceof Pow) {
            //check if there have the same "a"
            if (((Pow) e1).getE2().simplify().toString().equals(
                    ((Pow) e2).getE2().simplify().toString())) {
                return new Pow(
                        new Div(((Pow) e1).getE1().simplify(),
                                ((Pow) e2).getE1().simplify()).simplify(),
                        ((Pow) e1).getE2().simplify()).simplify();
            }
            //x^b / x^c ==> x^(b-c)
            if (((Pow) e1).getE1().toString().equals(((Pow) e2).getE1().toString())) {
                return new Pow(((Pow) e1).getE1(),
                        new Minus(((Pow) e1).getE2(), ((Pow) e2).getE2()).simplify()).simplify();
            }

        }
        //bonus (-x) / (-y) --> (x)/(y)
        if (e1 instanceof Neg && e2 instanceof Neg) {
            return new Div(((Neg) e1).getExpression().simplify(), ((Neg) e2).getExpression().simplify()).simplify();
        }
        e1 = getE1().simplify();
        e2 = getE2().simplify();
        //check for re-Simplify
        if (e1.toString().equals("0.0") || e1.toString().equals("1.0")
                || e2.toString().equals("0.0") || e2.toString().equals("1.0")) {
            return new Div(getE1().simplify(), getE2().simplify());
        }
        return new Div(getE1().simplify(), getE2().simplify());
    }
}