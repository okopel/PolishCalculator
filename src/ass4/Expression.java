/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

import java.util.List;
import java.util.Map;

/**
 * interface of expressions that we can calculate.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment is the map
     * @return the value.
     * @throws Exception if there is worst map.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the value.
     * @throws Exception not in use.
     */
    double evaluate() throws Exception;

    /**
     * @return Returns a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return Returns a nice string representation of the expression.
     */
    String toString();

    /**
     * @return reverse of toString.
     */
    String toStringRevers();
    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        is varible.
     * @param expression is the expression to replace.
     * @return the new Expression after replace.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var is the var to differnece.
     * @return the differnce.
     */
    Expression differentiate(String var);

    /**
     * @return Returned a simplified version of the current expression.
     */
    Expression simplify();
}
