/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class to sinus.
 */
public class Sin extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression our expression.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param num our expression.
     */
    public Sin(double num) {
        super(num);
    }

    /**
     * Constructor.
     *
     * @param num our expression.
     */
    public Sin(int num) {
        super((double) num);
    }

    /**
     * Constructor.
     *
     * @param var our expression.
     */
    public Sin(String var) {
        super(var);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(getExpression().simplify().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expressionin) {
        return new Sin(getExpression().assign(var, expressionin));
    }

    @Override
    public String toString() {
        return "sin(" + getExpression() + ")";
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
        return new Mult(new Cos(getExpression()), getExpression().differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return new Sin(getExpression().simplify());
        }
    }
}