/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * this class is united the classes with expression.
 */
abstract class BaseExpression implements Expression {
    private List<String> l = new ArrayList<>();

    /**
     * @return the list fo vars
     */
    public List<String> getList() {
        return l;
    }

    /**
     * set out list of vars.
     *
     * @param lin is the wanted lisr.
     */
    public void setList(List<String> lin) {
        this.l = lin;
    }

    /**
     * @return the evaluate of nums by evalute per class with map.
     * @throws Exception not in use.
     */
    public double evaluate() throws Exception {
        try {
            return this.evaluate(new TreeMap<>());
        } catch (Exception e) {
            throw new RuntimeException("Error in evaluate!");
        }
    }
}