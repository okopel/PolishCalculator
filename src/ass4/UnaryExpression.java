/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.ArrayList;
import java.util.List;

/**
 * the class to the unary expressions.
 */
abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(Expression e) {
        this.expression = e;
    }

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(Var e) {
        this.expression = e;
    }

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(Num e) {
        this.expression = e;
    }

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(String e) {
        this.expression = new Var(e);
    }

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(double e) {
        this.expression = new Num(e);
    }

    /**
     * Constructor.
     *
     * @param e is the value of the expreesion.
     */
    UnaryExpression(int e) {
        this.expression = new Num((double) e);
    }

    /**
     * @return our expression.
     */
    Expression getExpression() {
        return expression;
    }

    /**
     * @param expressionin the wanted expression to set.
     */
    public void setExpression(Expression expressionin) {
        this.expression = expressionin;
    }

    /**
     * @return the vars from our expression.
     */
    public List<String> getVariables() {
        List<String> listPrivate = new ArrayList<>();
        listPrivate.addAll(expression.getVariables());
        List<String> global = getList();
        for (String s : listPrivate) {
            if (!global.contains(s)) {
                global.add(s);
            }
        }
        return global;
    }
}
