package pr2.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simple test of System.in read.
 * 
 * @author Philipp Jenke
 *
 */
public class ReadFromSystemIn {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println(br.readLine());
		} catch (IOException e) {
			System.out.println("Failed to read line from input stream.");
		}
	}
}
