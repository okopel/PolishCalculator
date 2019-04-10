/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * the class of Varibles in expressions.
 */
public class Var implements Expression {
    private String var;

    /**
     * @param varin is the wanted var.
     */
    public Var(String varin) {
        this.var = varin;
    }

    /**
     * @return this var.
     */
    public String getVar() {
        return var;
    }

    /**
     * @param varin the wanted var.
     */
    public void setVar(String varin) {
        this.var = varin;
    }

    /**
     * @return the print form of Var.
     */
    @Override
    public String toString() {
        return "" + var;
    }

    /**
     * @return reverse of toString.
     */
    @Override
    public String toStringRevers() {
        return this.toString();
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return assignment.get(this.var);
        } catch (Exception e) {
            throw new Exception("#1 - Var isn't in Expression"); //todo what with not key??
        }
    }

    /**
     * @return not in use.
     * @throws Exception because the user wants to know the value without map!
     */
    @Override
    public double evaluate() throws Exception {
        throw new Exception("#2 - Var didnt get value");
    }

    /**
     * @return this var in list.
     */
    @Override
    public List<String> getVariables() { //todo check if is this list ok.
        List<String> l = new ArrayList<>();
        l.add(var);
        return l;
    }

    /**
     * replace string with expression.
     *
     * @param varin      in thestring to lookFor.
     * @param expression is the express to replace with the string.
     * @return the new Expression after replacment.
     */
    @Override
    public Expression assign(String varin, Expression expression) {
        if (this.var.equals(varin)) {
            return expression;
        }
        //if e didnt recived from the user.
        if (varin.equals("e")) {
            return new Num(Math.E);
        }
        return this;
    }

    @Override
    public Expression differentiate(String varin) {
        if (this.var.equals(varin)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
