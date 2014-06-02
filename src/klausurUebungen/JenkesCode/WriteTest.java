/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * @author Philipp Jenke
 * 
 */
public class WriteTest {
	public static void main(String[] args) {

		// Reuse list read by ReadTest class and sort it
		ReadTest readTest = new ReadTest();
		List<String> list = readTest.readList();
		Collections.sort(list);

		PrintWriter writer = openWriter("sorted.txt");
		for (String line : list) {
			writer.println(line);
		}
		closeWriter(writer);
	}

	/**
	 * Create a writer object for the file.
	 */
	private static PrintWriter openWriter(String filename) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(
					new FileWriter(filename)));
		} catch (IOException e) {
			closeWriter(writer);
			e.printStackTrace();
		}
		return writer;
	}

	/**
	 * Close the writer stream.
	 */
	private static void closeWriter(PrintWriter writer) {
		if (writer != null) {
			writer.close();
		}
	}
}
