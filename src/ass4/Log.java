/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.Map;

/**
 * class of log expression.
 */
public class Log extends BinaryExpression {
    /**
     * the base and the expression of the log.
     * NOTE: i used new Members in order to do the class readible.
     */
    private Expression base;
    private Expression expression;

    /**
     * Constructor.
     *
     * @param e1 the base of the log.
     * @param e2 the expression of the log.
     */
    public Log(Expression e1, Expression e2) {
        super(e1, e2);
        this.base = e1;
        this.expression = e2;
    }

    /**
     * Constructor.
     *
     * @param var1 the base of the log.
     * @param e2   the expression of the log.
     */
    public Log(String var1, Expression e2) {
        super(var1, e2);
        this.base = getE1();
        this.expression = e2;
    }

    /**
     * Constructor.
     *
     * @param e1   the base of the log.
     * @param var2 the expression of the log.
     */
    public Log(Expression e1, String var2) {
        super(e1, var2);
        this.base = e1;
        this.expression = getE2();
    }

    /**
     * Constructor.
     *
     * @param num the base of the log.
     * @param e2  the expression of the log.
     */
    public Log(double num, Expression e2) {
        super(num, e2);
        this.base = getE1();
        this.expression = e2;
    }

    /**
     * Constructor.
     *
     * @param e1   the base of the log.
     * @param num2 the expression of the log.
     */
    public Log(Expression e1, double num2) {
        super(e1, num2);
        this.base = e1;
        this.expression = getE2();
    }

    /**
     * Constructor.
     *
     * @param var the base of the log.
     * @param num the expression of the log.
     */
    public Log(String var, double num) {
        super(var, num);
        this.base = getE1();
        this.expression = getE2();
    }

    /**
     * Constructor.
     *
     * @param num1 the base of the log.
     * @param num2 the expression of the log.
     */
    public Log(double num1, double num2) {
        super(num1, num2);
        this.base = getE1();
        this.expression = getE2();
    }

    /**
     * Constructor.
     *
     * @param num the base of the log.
     * @param var the expression of the log.
     */
    public Log(double num, String var) {
        super(num, var);
        this.base = getE1();
        this.expression = getE2();
    }

    /**
     * Constructor.
     *
     * @param var1 the base of the log.
     * @param var2 the expression of the log.
     */
    public Log(String var1, String var2) {
        super(var1, var2);
        this.base = getE1();
        this.expression = getE2();
    }

    /**
     * @return the base of the log.
     */
    public Expression getBase() {
        return base;
    }

    /**
     * set the base of the log.
     *
     * @param basein is the wanted base.
     */
    public void setBase(Expression basein) {
        this.base = basein;
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
        try {
            //check the the base the expression low.
            if (base.simplify().evaluate(assignment) != 1 && base.simplify().evaluate(assignment) > 0
                    && expression.simplify().evaluate(assignment) > 0
                    && Math.log(base.simplify().evaluate(assignment)) != 0) {
                return Math.log(expression.simplify().evaluate(assignment))
                        / Math.log(base.simplify().evaluate(assignment));
            }
        } catch (Exception e) {
            throw new Exception("Error in log!");
        }
        throw new Exception("Error in log!");
    }

    /**
     * the function ger expression (expression or num or var)
     * and put it in our expression.
     *
     * @param var          is the var to replace with the expression.
     * @param expressionin the expression to replace.
     * @return the new expression after replace.
     */
    @Override
    public Expression assign(String var, Expression expressionin) {
        return new Log(base.assign(var, expressionin), expression.assign(var, expressionin));
    }

    @Override
    public String toString() {
        return ("log(" + base + ", " + expression + ")");
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
        Expression up = expression.differentiate(var);
        Expression down = new Mult(expression, new Log("e", base));
        try {
            if (down.evaluate() == 0 || down.toString().equals("0.0")) {
                throw new Exception("divid by 0!!");
            } else {
                return new Div(up, down);
            }
        } catch (Exception e) {
            return new Div(up, down);
        }
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(Math.log(expression.evaluate()) / Math.log(base.evaluate()));
        } catch (Exception e) {
            try {
                if (base.evaluate() == expression.evaluate()) {
                    return new Num(1);
                }
            } catch (Exception e2) {
                if (base.toString().equals(expression.toString())) {
                    return new Num(1);
                }
                if (base.toStringRevers().equals(expression.toString())) {
                    return new Num(1);
                }
            }
            try {
                if (base.evaluate() == 0) {
                    return new Log(0, expression.simplify());
                }
            } catch (Exception e2) {
                if (base.toString().equals("0.0")) {
                    return new Log(0, expression.simplify());
                }
            }
            //bonus of log(x,1) --> 0
            try {
                if (expression.evaluate() == 1) {
                    return new Num(0);
                }
            } catch (Exception e2) {
                if (expression.toString().equals("1.0")) {
                    return new Num(0);
                }
            }
        }
        return new Log(base.simplify(), expression.simplify());
    }
}