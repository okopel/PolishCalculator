/********************************************************
 * Task ass4.
 * @author Ori Kopel <kopelor> <okopel@gmail.com>
 * @version 1.0
 * @since 29-4-2018.
 * @author Ori Kopel
 ******************************************************/

public class SimplificationDemo {
    /**
     * This main has to show my bonus to the tester.
     *
     * @param args not in use.
     */
    public static void main(String[] args) {
        //x + (-x) --> 0
        System.out.println(new Plus("x", new Neg("x")));
        System.out.println(new Plus("x", new Neg("x")).simplify());
        System.out.println("-----");
        //(-y) / (-x) --> (y)/(x)
        System.out.println(new Div(new Neg("x"), new Neg("y")));
        System.out.println(new Div(new Neg("x"), new Neg("y")).simplify());
        System.out.println("-----");
        //sin^2(x) + cos^2(x) = 1
        System.out.println(new Plus(new Pow(new Sin("x"), 2), new Pow(new Cos("x"), 2)));
        System.out.println(new Plus(new Pow(new Sin("x"), 2), new Pow(new Cos("x"), 2)).simplify());
        System.out.println("-----");
        //log(x+y,y+x) -->1
        System.out.println(new Log(new Plus("x", "y"), new Plus("y", "x")));
        System.out.println(new Log(new Plus("x", "y"), new Plus("y", "x")).simplify());
        System.out.println("-----");
        //(x * y) - (y * x)  = 0
        System.out.println(new Minus(new Mult("x", "y"), new Mult("y", "x")));
        System.out.println(new Minus(new Mult("x", "y"), new Mult("y", "x")).simplify());
        System.out.println("-----");
        //x^0 --> 1
        System.out.println(new Pow("x", 0));
        System.out.println(new Pow("x", 0).simplify());
        System.out.println("-----");
        //log(x+y,1) --> 0
        System.out.println(new Log(new Plus("x", "y"), 1));
        System.out.println(new Log(new Plus("x", "y"), 1).simplify());
        System.out.println("-----");
        //log(num, (num2*x)) --> log(num, num2) + log num, x
        //x^1 --> x
        System.out.println(new Pow("x", 1));
        System.out.println(new Pow("x", 1).simplify());
        System.out.println("-----");
        //x*x --> x^2
        System.out.println(new Mult(new Mult("x", "y"), new Mult("x", "y")));
        System.out.println(new Mult(new Mult("x", "y"), new Mult("x", "y")).simplify());
        System.out.println("-----");
        //(exp)+(exp) --> 2 * exp
        System.out.println(new Plus("y", "y"));
        System.out.println(new Plus("y", "y").simplify());
        System.out.println("-----");
        //x^-1 -->1/x
        System.out.println(new Pow("x", -1));
        System.out.println(new Pow("x", -1).simplify());
        System.out.println("-----");
        //2x + 4x --> 6x || x + x ->> 2x
        System.out.println(new Plus(new Mult(2, "x"), new Mult(4, "x")));
        System.out.println(new Plus(new Mult(2, "x"), new Mult(4, "x")).simplify());
        System.out.println("-----");
        //x^-a -->1/(x^a)
        System.out.println(new Pow("x", -15));
        System.out.println(new Pow("x", -15).simplify());
        System.out.println("-----");
        //(a/b) / (c/d) --> (a*d) / (b*c)
        System.out.println(new Div(new Div(10, "x"), new Div("y", "z")));
        System.out.println(new Div(new Div(10, "x"), new Div("y", "z")).simplify());
        System.out.println("-----");
        // x^y^z --> x^(y*z)
        System.out.println(new Pow(new Pow("x", "y"), "z"));
        System.out.println(new Pow(new Pow("x", "y"), "z").simplify());
        System.out.println("-----");
        //(x^a) / (y^a) --> (x/y)^a
        System.out.println(new Div(new Pow("x", "a"), new Pow("y", "a")));
        System.out.println(new Div(new Pow("x", "a"), new Pow("y", "a")).simplify());
        System.out.println("-----");
        //(x^a) / (x^b) --> x^(a-b)
        System.out.println(new Div(new Pow("x", "a"), new Pow("x", "b")));
        System.out.println(new Div(new Pow("x", "a"), new Pow("x", "b")).simplify());
        System.out.println("-----");
        //x^a * x^b --> x^(a+b)
        System.out.println(new Mult(new Pow("x", "a"), new Pow("x", "b")));
        System.out.println(new Mult(new Pow("x", "a"), new Pow("x", "b")).simplify());
        System.out.println("-----");
        //x^a * y^a  --> (x*y)^a
        System.out.println(new Mult(new Pow("x", "a"), new Pow("y", "a")));
        System.out.println(new Mult(new Pow("x", "a"), new Pow("y", "a")).simplify());
        System.out.println("-----");
        // --a --> a
        System.out.println(new Neg(-5));
        System.out.println(new Neg(-5).simplify());
        System.out.println("-----");
        //(x+y) / (y+x) --> 1
        System.out.println(new Div(new Plus("x", 5), new Plus(5, "x")));
        System.out.println(new Div(new Plus("x", 5), new Plus(5, "x")).simplify());
        System.out.println("-----");
        //(x * y) / (y * x) --> 1
        System.out.println(new Div(new Mult("x", 5), new Mult(5, "x")));
        System.out.println(new Div(new Mult("x", 5), new Mult(5, "x")).simplify());
        System.out.println("-----");
        // x * 2 --> 2 * x
        System.out.println(new Mult("x", 2));
        System.out.println(new Mult("x", 2).simplify());
        System.out.println("-----");
        // (2 * x) * (3 * x) --> 6 * (x^2)
        System.out.println(new Mult(new Mult(2, "x"), new Mult(3, "x")));
        System.out.println(new Mult(new Mult(2, "x"), new Mult(3, "x")).simplify());
        System.out.println("-----");
        //a^(log(a,b) --> b
        System.out.println(new Pow(2, new Log(2, "x")));
        System.out.println(new Pow(2, new Log(2, "x")).simplify());
        System.out.println("-----");

    }
}
