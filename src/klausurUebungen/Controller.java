package klausurUebungen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lnerlich on 02.06.14.
 */
public class Controller {

    List<String> list = new ArrayList<String>();
    String zeile = null;

    public BufferedReader openReader(String filename){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            while((zeile = reader.readLine()) != null){
                list.add(zeile);
            }
        }
        catch(IOException e){
            closeReader(reader);
            e.printStackTrace();
        }
        return reader;
    }

    public PrintWriter openWriter(String filename){
        PrintWriter writer = null;
        try {
              writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        }
        catch(IOException e){
            closeWriter(writer);
            e.printStackTrace();
        }
        return writer;
    }

    public void closeReader(BufferedReader reader) {
        try{
            if (reader != null){
                reader.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeWriter(PrintWriter writer) {

            if (writer != null){
                writer.close();
            }
    }

    public List<String> getList(){
        return list;
    }
}
