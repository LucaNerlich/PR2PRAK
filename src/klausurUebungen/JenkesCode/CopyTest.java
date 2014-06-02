/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Philipp Jenke
 * 
 */
public class CopyTest {
	/**
	 * Java-7 code to read from file.
	 */
	private static void testInJava7() {
		try (FileInputStream inFile = new FileInputStream("unsorted.txt");
				FileOutputStream outFile = new FileOutputStream(
						"unsorted_copy.txt")) {
			// A buffer is required for the copied data
			byte[] buffer = new byte[65536];
			int len;
			// Read to buffer, write to destination
			while ((len = inFile.read(buffer)) > 0) {
				outFile.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			// File not found
			e.printStackTrace();
		} catch (IOException e) {
			// I/O error
			e.printStackTrace();
		}
	}

	/**
	 * Program entry point
	 */
	public static void main(String[] args) {
		testInJava7();
	}
}
