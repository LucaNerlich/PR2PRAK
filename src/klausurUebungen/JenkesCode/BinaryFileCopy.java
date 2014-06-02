/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams.exercises;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Philipp Jenke
 * 
 */
public class BinaryFileCopy {
    public static void main(String[] args) {
        try (FileInputStream inFile = new FileInputStream("PR2.jpg");
                FileOutputStream outFile = new FileOutputStream("PR2neu.jpg")) {
            byte[] buffer = new byte[65536];
            int len;
            while ((len = inFile.read(buffer)) > 0) {
                outFile.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
