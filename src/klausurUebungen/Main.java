package klausurUebungen;

import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by lnerlich on 02.06.14.
 */
public class Main {

    public static void main(String[] args) {


        Controller controller = new Controller();
        controller.openReader("C:\\Users\\lnerlich\\IdeaProjects\\PR2PRAK\\src\\klausurUebungen\\testRead.txt");
        PrintWriter writer = controller.openWriter("C:\\Users\\lnerlich\\IdeaProjects\\PR2PRAK\\src\\klausurUebungen\\testWrite.txt");


        for(String zeileOut : controller.getList()){
            System.out.println(zeileOut);
        }

        long starTime = new Date().getTime();
        for(int i = 0; i <= 10000; i++){
            writer.println(i);
         }
        long endTime = new Date().getTime();
        long passedTime = endTime - starTime;
        writer.println("Benoetigte Zeit zum verarbeiten in Millisekunden: " + passedTime);

       controller.closeWriter(writer);
    }
}
