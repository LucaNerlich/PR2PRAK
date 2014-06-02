/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Philipp Jenke
 * 
 *         Test the read stream functionality.
 * 
 */
public class ReadTest {

	/**
	 * Read a file and save all lines in a List<String>.
	 * 
	 * @return List object.
	 */
	public List<String> readList() {
		BufferedReader reader = openReader("unsorted.txt");

		List<String> list = new ArrayList<String>();
		String zeile = null;
		try {
			while ((zeile = reader.readLine()) != null) {
				list.add(zeile);
			}
		} catch (IOException e) {
			closeReader(reader);
			e.printStackTrace();
		}

		closeReader(reader);
		return list;
	}

	/**
	 * Create buffered reader object for file
	 */
	private BufferedReader openReader(String filename) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			closeReader(reader);
			e.printStackTrace();
		}
		return reader;
	}

	/**
	 * Close the reader.
	 */
	private void closeReader(BufferedReader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Class entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ReadTest readTest = new ReadTest();
		System.out.println(readTest.readList());
	}
}
