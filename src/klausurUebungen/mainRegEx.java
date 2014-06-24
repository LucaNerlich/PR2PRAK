package klausurUebungen;

import java.io.*;

/**
 * Created by Luca on 24.06.2014.
 */
public class MainRegEx {

    public static void main(String[] args) {
        RegExController regExCon = new RegExController();

        regExCon.setRegExPattern();

        Controller controller2 = new Controller();
        PrintWriter writer = controller2.openWriter("C:\\Users\\Luca\\Google Drive\\Dropbox\\Praktikum\\Semester 2\\PR 2\\PR2PRAK\\src\\klausurUebungen\\testWrite.txt");

       writer.println(regExCon.getRegExPattern());
       writer.println(regExCon.getInputString());
       writer.println("asdf");

       controller2.closeWriter(writer);
    }
}
