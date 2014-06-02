/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * Solution code to exercise.
 * 
 * @author Philipp Jenke
 * 
 */
public class ExerciseAppendNumberToFile {

    /**
     * Program entry point.
     */
    public static void main(String[] args) {

        final String filename = "dummy.txt";
        File file = new File(filename);
        if (file.exists()) {
            // Read content to list
            Vector<String> list = new Vector<String>();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(filename));
                String zeile = null;
                while ((zeile = reader.readLine()) != null) {
                    list.add(zeile);
                }
                // Close reader
                reader.close();
            } catch (IOException e) {
                System.out.println("Failed to read from file.");
            }

            // Compute new value
            list.add(""
                    + (Integer.parseInt(list.elementAt(list.size() - 1)) + 1));

            // Open file and write list to it.
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(
                        filename)));
            } catch (IOException e) {
                System.out.println("Failed to write to file.");
            }
            for (String s : list) {
                writer.println(s);
            }
            writer.close();
        } else {
            // Create file and write "1" to it.
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(
                        filename)));
            } catch (IOException e) {
                System.out.println("Failed to write to file.");
            }
            writer.println("1");
            writer.close();

        }

    }
}
