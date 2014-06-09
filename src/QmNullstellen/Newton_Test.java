package QmNullstellen;

/**
 * Created by Luca on 09.06.2014.
 */
public class Newton_Test {

    public static void main(String[] args)  {

        Funktionen f = new Funktionen();

        double x=1.035;  // Startwert
        final double eps=0.01;

        do{
            x = f.newton(x);
        }
        while ( (Math.abs(f.f1(x))) >=0.01);

        System.out.println("Die Nullstelle ist " +x);

    }

}
