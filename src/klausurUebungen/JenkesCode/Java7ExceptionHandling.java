/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Philipp Jenke
 * 
 */
public class Java7ExceptionHandling {

	/**
	 * Java-6 code to read from file.
	 */
	private static void testInJava6() {
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream("file.txt");
			// A buffer is required for the copied data
			byte[] buffer = new byte[65536];
			int len;
			// Read to buffer, write to destination
			while ((len = inFile.read(buffer)) > 0) {
				System.out.println(buffer + " with length: " + len);
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			// File not found
			e.printStackTrace();
			try {
				inFile.close();
			} catch (IOException e1) {
				// Error closing file
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// I/O error
			e.printStackTrace();
			try {
				inFile.close();
			} catch (IOException e1) {
				// Error closing file
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Java-7 code to read from file.
	 */
	private static void testInJava7() {
		try (FileInputStream inFile = new FileInputStream("file.txt");) {
			// A buffer is required for the copied data
			byte[] buffer = new byte[65536];
			int len = 0;
			// Read to buffer, write to destination
			while ((len = inFile.read(buffer)) > 0) {
				System.out.println(buffer + " with length: " + len);
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
	 * Program entra point
	 */
	public static void main(String[] args) {
		testInJava6();
		testInJava7();
	}
}
