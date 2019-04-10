/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class of minus.
 */
public class Minus extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param e1 is the first expression.
     * @param e2 is the secobd expression.
     */
    public Minus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first expression.
     * @param e2   is the secobd expression.
     */
    public Minus(String var1, Expression e2) {
        super(var1, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first expression.
     * @param var2 is the secobd expression.
     */
    public Minus(Expression e1, String var2) {
        super(e1, var2);
    }

    /**
     * Constructor.
     *
     * @param num is the first expression.
     * @param e2  is the secobd expression.
     */
    public Minus(double num, Expression e2) {
        super(num, e2);
    }

    /**
     * Constructor.
     *
     * @param e1   is the first expression.
     * @param num2 is the secobd expression.
     */
    public Minus(Expression e1, double num2) {
        super(e1, num2);
    }

    /**
     * Constructor.
     *
     * @param var is the first expression.
     * @param num is the secobd expression.
     */
    public Minus(String var, double num) {
        super(var, num);
    }

    /**
     * Constructor.
     *
     * @param num1 is the first expression.
     * @param num2 is the secobd expression.
     */
    public Minus(double num1, double num2) {
        super(num1, num2);
    }

    /**
     * Constructor.
     *
     * @param num is the first expression.
     * @param var is the secobd expression.
     */
    public Minus(double num, String var) {
        super(num, var);
    }

    /**
     * Constructor.
     *
     * @param var1 is the first expression.
     * @param var2 is the secobd expression.
     */
    public Minus(String var1, String var2) {
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
        return getE1().simplify().evaluate(assignment) - getE2().simplify().evaluate(assignment);
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
        return new Minus(getE1().assign(var, expression), getE2().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("(" + getE1() + " - " + getE2() + ")");
    }

    /**
     * @return reverse of toString.
     * Not relevant here.
     */
    @Override
    public String toStringRevers() {
        return this.toString();
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(getE1().differentiate(var), getE2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            if (getE1().evaluate() == getE2().evaluate()) {
                return new Num(0);
            }
        } catch (Exception e) {
            if (getE1().toString().equals(getE2().toString())) {
                return new Num(0);
            }
            //bonus: xy - yx = 0
            if (getE1().toStringRevers().equals(getE2().toString())) {
                return new Num(0);
            }
        }
        try {
            if (getE2().evaluate() == 0) {
                return getE1().simplify();
            }
        } catch (Exception e) {
            if (getE2().toString().equals("0.0")) {
                return getE1().simplify();
            }
        }
        try {
            if (getE1().evaluate() == 0) {
                return new Neg(getE2().simplify());
            }
        } catch (Exception e) {
            if (getE1().toString().equals("0.0")) {
                return new Neg(getE2().simplify());
            }
        }
        Expression e1 = getE1().simplify();
        Expression e2 = getE2().simplify();
        if (e1.toString().equals("0.0") || e2.toString().equals("0.0")) {
            return new Minus(getE1().simplify(), getE2().simplify()).simplify();
        }
        return new Minus(getE1().simplify(), getE2().simplify());
    }
}
