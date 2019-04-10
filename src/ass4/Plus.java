/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class for plus expression.
 */
public class Plus extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param e1 is the first Expression.
     * @param e2 is the sec Expression.
     */
    public Plus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first Expression.
     * @param e2   is the sec Expression.
     */
    public Plus(String var1, Expression e2) {
        super(var1, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first Expression.
     * @param var2 is the sec Expression.
     */
    public Plus(Expression e1, String var2) {
        super(e1, var2);
    }

    /**
     * Constructor.
     *
     * @param num is the first Expression.
     * @param e2  is the sec Expression.
     */
    public Plus(double num, Expression e2) {
        super(num, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first Expression.
     * @param num2 is the sec Expression.
     */
    public Plus(Expression e1, double num2) {
        super(e1, num2);
    }

    /**
     * Constructor.
     *
     * @param var is the first Expression.
     * @param num is the sec Expression.
     */
    public Plus(String var, double num) {
        super(var, num);
    }

    /**
     * Constructor.
     *
     * @param num1 is the first Expression.
     * @param num2 is the sec Expression.
     */
    public Plus(double num1, double num2) {
        super(num1, num2);
    }

    /**
     * Constructor.
     *
     * @param num is the first Expression.
     * @param var is the sec Expression.
     */
    public Plus(double num, String var) {
        super(num, var);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first Expression.
     * @param var2 is the sec Expression.
     */
    public Plus(String var1, String var2) {
        super(var1, var2);
    }

    /**
     * Evaluate its value for a given variable assignment to numbers.
     *
     * @param assignment is the map of the vars.
     * @return the answer of the expression.
     * @throws Exception if the evaluate not exist.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getE1().simplify().evaluate(assignment) + getE2().simplify().evaluate(assignment);
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
        return new Plus(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("(" + getE1() + " + " + getE2() + ")");
    }

    /**
     * @return reverse of toString.
     */
    @Override
    public String toStringRevers() {
        return ("(" + getE2() + " + " + getE1() + ")");
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(getE1().differentiate(var), getE2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(getE1().evaluate() + getE2().evaluate());
        } catch (Exception e) {
            try {
                if (getE1().evaluate() == 0) {
                    return getE2().simplify();
                }
            } catch (Exception e2) {
                if (getE1().toString().equals("0.0")) {
                    return getE2().simplify();
                }
            }
            try {
                if (getE2().evaluate() == 0) {
                    return getE1().simplify();
                }
            } catch (Exception e2) {
                if (getE2().toString().equals("0.0")) {
                    return getE1().simplify();
                }
            }
        }
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        try {
            return new Num(e1.evaluate() + e2.evaluate());
        } catch (Exception e) {
            if (e1.toString().equals("0.0") || e2.toString().equals("0.0")) {
                return new Plus(e1.simplify(), e2.simplify()).simplify();
            }
        }

        //bonus
        //x + (-x) --> 0
        if (e1 instanceof Var && e2 instanceof Neg) {
            if (e1.simplify().toString().equals(((Neg) e2).getExpression().simplify().toString())) {
                return new Num(0);
            }
        }
        if (e2 instanceof Var && e1 instanceof Neg) {
            if (e2.simplify().toString().equals(((Neg) e1).getExpression().simplify().toString())) {
                return new Num(0);
            }
        }
        //x + x --> 2 * x
        if (e1.toString().equals(e2.toString())) {
            return new Mult(2, e1).simplify();
        }
        //2x + 4x ==> 6x
        //there is simplify that replace x4 to 4x so we have just one case.
        if (e1 instanceof Mult && e2 instanceof Mult) {
            if (((Mult) e1).getE1() instanceof Var && ((Mult) e1).getE2() instanceof Num) {
                e1 = e1.simplify();
            }
            if (((Mult) e2).getE1() instanceof Var && ((Mult) e2).getE2() instanceof Num) {
                e2 = e2.simplify();
            }
            if (((Mult) e1).getE2().simplify().toString().equals(
                    ((Mult) e2.simplify()).getE2().simplify().toString())) {
                return new Mult(new Plus(((Mult) e1).getE1(), ((Mult) e2).getE1()).simplify(),
                        ((Mult) e1).getE2().simplify()).simplify();
            }
        }
        //sin^2(x) + cos^2(x) = 1
        if (e1 instanceof Pow && e2 instanceof Pow) {
            if ((((Pow) e1).getE1() instanceof Sin && ((Pow) e2).getE1() instanceof Cos)
                    || (((Pow) e1).getE1() instanceof Cos && ((Pow) e2).getE1() instanceof Sin)) {
                if (((Pow) e1).getE2().toString().equals("2.0") && ((Pow) e2).getE2().toString().equals("2.0")) {
                    return new Num(1);
                }
            }
        }
        return new Plus(e1.simplify(), e2.simplify());
    }
}