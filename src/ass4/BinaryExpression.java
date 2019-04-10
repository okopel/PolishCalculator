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
 * the class united the 2 expressions classes.
 */
abstract class BinaryExpression extends BaseExpression {
    private Expression e1;
    private Expression e2;

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(Expression e1in, Expression e2in) {
        this.e1 = e1in;
        this.e2 = e2in;
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(String e1in, Expression e2in) {
        this.e1 = new Var(e1in);
        this.e2 = e2in;
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(String e1in, String e2in) {
        this.e1 = new Var(e1in);
        this.e2 = new Var(e2in);
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(Expression e1in, String e2in) {
        this.e2 = new Var(e2in);
        this.e1 = e1in;
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(double e1in, Expression e2in) {
        this.e1 = new Num(e1in);
        this.e2 = e2in;
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(Expression e1in, double e2in) {
        this.e2 = new Num(e2in);
        this.e1 = e1in;
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(double e1in, String e2in) {
        this.e1 = new Num(e1in);
        this.e2 = new Var(e2in);
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(String e1in, double e2in) {
        this.e1 = new Var(e1in);
        this.e2 = new Num(e2in);
    }

    /**
     * Constructor.
     *
     * @param e1in the first expression.
     * @param e2in the secend expression.
     */
    BinaryExpression(double e1in, double e2in) {
        this.e2 = new Num(e2in);
        this.e1 = new Num(e1in);
    }

    /**
     * @return the first expression.
     */
    Expression getE1() {
        return e1;
    }

    /**
     * @param e1in the wnted expression.
     */
    void setE1(Expression e1in) {
        this.e1 = e1in;
    }

    /**
     * @return the secend expression.
     */
    Expression getE2() {
        return e2;
    }

    /**
     * @param e2in the wanted expression to set.
     */
    public void setE2(Expression e2in) {
        this.e2 = e2in;
    }

    /**
     * @return the vars from our expression.
     */
    public List<String> getVariables() {
        List<String> listPrivate = new ArrayList<>();
        List<String> global = getList();
        listPrivate.addAll(e1.getVariables());
        listPrivate.addAll(e2.getVariables());
        for (String s : listPrivate) {
            if (!global.contains(s)) {
                global.add(s);
            }
        }
        return global;
    }
}