/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * class to the negative numbers.
 */
public class Neg extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression is the expression to neg.
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param num is the expression to neg.
     */
    public Neg(double num) {
        super(num);
    }

    /**
     * Constructor.
     *
     * @param var is the expression to neg.
     */
    public Neg(String var) {
        super(var);
    }

    /**
     * Constructor.
     *
     * @param num is the expression to neg.
     */
    public Neg(Num num) {
        super(num);
    }

    /**
     * Constructor.
     *
     * @param var is the expression to neg.
     */
    public Neg(Var var) {
        super(var);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -(getExpression().simplify().evaluate(assignment));
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(expression.assign(var, expression));
    }

    @Override
    public String toString() {
        return "(-" + getExpression() + ")";
    }

    /**
     * @return reverse of toString.
     * not rellevante here.
     */
    @Override
    public String toStringRevers() {
        return "(-" + getExpression() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(getExpression().differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            //check if we have just number in the Neg.
            //evaluate return the - value so we dont have to put the minus here.
            return new Num(this.evaluate());
        } catch (Exception e) {
            return new Neg(getExpression().simplify());
        }
    }
}
