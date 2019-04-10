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
 * the class for the atomic num.
 */
public class Num implements Expression {
    private double num;

    /**
     * Constructor.
     *
     * @param numin is the num to Num.
     */
    public Num(int numin) {
        this.num = (double) numin;
    }

    /**
     * Constructor.
     *
     * @param num is the num to Num.
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * @return this num.
     */
    public double getNum() {
        return num;
    }

    /**
     * @param numin is the wanted num.
     */
    public void setNum(double numin) {
        this.num = numin;
    }

    /**
     * num is atomic class so we dont have to use this.
     *
     * @param assignment not in use.
     * @return the value of num.
     * @throws Exception not in use.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return num;
    }

    /**
     * @return the Num value.
     * @throws Exception not in use
     */
    @Override
    public double evaluate() throws Exception {
        return num;
    }

    /**
     * num is atomic class so we dont have to use this.
     *
     * @return an empty list.
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * num is atomic class so we dont have to use this.
     *
     * @param var        is the string to checj.
     * @param expression is the expression to replace with the string.
     * @return this because not in use.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * @return print string.
     */
    @Override
    public String toString() {
        if (num == Math.E) {
            return "e";
        }
        if (num == Math.PI) {
            //return String.valueOf('\u03c0');
            return "pi";
        }
        return num + "";
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
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
