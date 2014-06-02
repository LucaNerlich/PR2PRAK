/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams.exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Philipp Jenke
 * 
 */
public class TextFileIO {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("PR2.txt"));) {
            String str = br.readLine();
            System.out.println(str);
        } catch (IOException e) {
            System.out.println("OOPS");
        }
    }
}
