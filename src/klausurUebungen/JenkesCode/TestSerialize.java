package pr2.streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Test Java serialization functionality.
 * 
 * @author Philipp Jenke
 *
 */
public class TestSerialize {

	/**
	 * Serialize an object
	 */
	private static void serialize(Serializable object, String filename) {
		try (ObjectOutputStream dateiStream = new ObjectOutputStream(
				new FileOutputStream(filename))) {
			dateiStream.writeObject(object);
		} catch (IOException e) {
			System.out.println("Failed to serialize object");
		}
	}

	/**
	 * Deserialize a byte-stream.
	 */
	private static Object deserialize(String filename) {
		Object object = null;
		try (ObjectInputStream fileStream = new ObjectInputStream(
				new FileInputStream(filename))) {
			object = fileStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Failed to deserialize file.");
		}
		return object;
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		String testString = "Test-String";
		String filename = "serializedObject.object";
		serialize(testString, filename);
		Object result = deserialize(filename);
		System.out.println(result);
	}
}
