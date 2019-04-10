/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * the class of cosinus.
 */
public class Cos extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression the wanted expression.
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param num the wanted expression.
     */
    public Cos(double num) {
        super(num);
    }

    /**
     * Constructor.
     *
     * @param num the wanted expression.
     */
    public Cos(int num) {
        super((double) num);
    }


    /**
     * Constructor.
     *
     * @param var the wanted expression.
     */
    public Cos(String var) {
        super(var);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getExpression().simplify().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expressionin) {
        return new Cos(getExpression().assign(var, expressionin));
    }

    @Override
    public String toString() {
        return "cos(" + getExpression() + ")";
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
        return new Neg(new Mult(new Sin(this.getExpression()), getExpression().differentiate(var)));
    }

    @Override
    public Expression simplify() {
        try {
            //try to calculate the expression.
            return new Num(this.evaluate());
        } catch (Exception e) {
            return new Cos(getExpression().simplify());
        }
    }
}