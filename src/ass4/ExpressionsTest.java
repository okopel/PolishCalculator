import java.util.Map;
import java.util.TreeMap;

/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

public class ExpressionsTest {
    /**
     * Tester class to this ass.
     *
     * @param args not in use.
     * @throws Exception to calculate the evaluate if possible.
     */
    public static void main(String[] args) throws Exception {
        Expression ex = new Plus(new Mult(2, "x"), new Plus(new Sin(new Mult(4, "y")), new Pow("e", "x")));
        System.out.println(ex);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(ex.evaluate(assignment));
        System.out.println(ex.differentiate("x"));
        System.out.println(ex.differentiate("x").evaluate(assignment));
        System.out.println(ex.differentiate("x").simplify());
    }
}
