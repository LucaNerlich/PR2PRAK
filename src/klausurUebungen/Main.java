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

        writer.println(new Date());
        for(int i = 0; i <= 10000; i++){
            writer.println(i);
         }
        writer.println(new Date());

       controller.closeWriter(writer);
    }
}
