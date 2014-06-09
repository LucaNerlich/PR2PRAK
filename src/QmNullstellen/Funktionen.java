package QmNullstellen;

/**
 * Created by Luca on 09.06.2014.
 */
public class Funktionen {

    final double d=0.001;

    public double f1(double x) {
        return 3*x*x-8*x-20;
    }

    public double ableitung(double x) {
        return (f1(x+d) - f1(x-d))/(2*d);
    }  // näherungsweise mit dem zentralen Differenzen-
    // quotienten berechnet


    public double newton(double x) {
        return x-f1(x)/ableitung(x);
    }

}
