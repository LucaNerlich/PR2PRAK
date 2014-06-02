/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.util.Scanner;

/**
 * @author Philipp Jenke
 * 
 *         Read input from the console.
 * 
 */
public class ReadFromConsole {

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your age.");
        int age = scanner.nextInt();
        System.out.println("Age in 10 years: " + (age + 10));
        scanner.close();
    }
}
