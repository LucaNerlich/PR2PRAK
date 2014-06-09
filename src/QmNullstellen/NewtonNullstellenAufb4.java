package QmNullstellen;

/**
 * Created by Luca on 09.06.2014.
 */
public class NewtonNullstellenAufb4 {

    /**
     * Nullstelle = startwert - (f(x)/f'(x))
     */

    //Funktionen und Werte:

    public static double startwert;


    public NewtonNullstellenAufb4(double startwert){
    this.startwert = startwert;
    }

    public static double f1(double x) {
        return 24*Math.pow(x,11)-(349*x)+325;
    }

    public static double ableitung(double x) {
        return 24*11*Math.pow(x,10)-349;
    }


    public static double getNullstelle(double x) {
        return x-(f1(x)/ableitung(x));
    }



    public static void main(String[] args) {

        NewtonNullstellenAufb4 newton = new NewtonNullstellenAufb4(1.035);

        do{
            startwert = newton.getNullstelle(startwert);
            System.out.println("Die Nullstelle ist " +startwert);
        }
        //f1(0) -> nullstelle gefunden
        while ( (Math.abs(newton.f1(startwert))) >=0.0000001);

        System.out.println("FINAL Die Nullstelle fuer 1.035 ist " +startwert);

        System.out.println("#######################################################");

        NewtonNullstellenAufb4 newton2 = new NewtonNullstellenAufb4(1.04);

        do{
            startwert = newton2.getNullstelle(startwert);
            System.out.println("Die Nullstelle ist " +startwert);
        }
        //f1(0) -> nullstelle gefunden
        while ( (Math.abs(newton2.f1(startwert))) >=0.00001);

        System.out.println("FINAL Die Nullstelle fuer 1.04 ist " +startwert);

        System.out.println("#######################################################");

        NewtonNullstellenAufb4 newton3 = new NewtonNullstellenAufb4(1.05);

        do{
            startwert = newton3.getNullstelle(startwert);
            System.out.println("Die Nullstelle ist " +startwert);
        }
        //f1(0) -> nullstelle gefunden
        while ( (Math.abs(newton3.f1(startwert))) >=0.0000001);

        System.out.println("FINAL Die Nullstelle fuer 1.05 ist " +startwert);

        System.out.println("#######################################################");

        NewtonNullstellenAufb4 newton4 = new NewtonNullstellenAufb4(1.06);

        do{
            startwert = newton4.getNullstelle(startwert);
            System.out.println("Die Nullstelle ist " +startwert);
        }
        //f1(0) -> nullstelle gefunden
        while ( (Math.abs(newton4.f1(startwert))) >=0.0000001);

        System.out.println("FINAL Die Nullstelle fuer 1.06 ist " +startwert);

        System.out.println("#######################################################");

        NewtonNullstellenAufb4 newton5 = new NewtonNullstellenAufb4(1.065);

        do{
            startwert = newton5.getNullstelle(startwert);
            System.out.println("Die Nullstelle ist " +startwert);
        }
        //f1(0) -> nullstelle gefunden
        while ( (Math.abs(newton5.f1(startwert))) >=0.0000001);

        System.out.println("FINAL Die Nullstelle fuer 1,065 ist " +startwert);
    }




}
