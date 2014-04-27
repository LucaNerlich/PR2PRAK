package aufgabe3;

import java.util.Comparator;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * StringComparator.java
 */

/**
 * Comparatorklasse zum Vergleich zweiter Strings
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */
public class StringComparator<T extends String, U> implements
		Comparator<BinaryNode<T, U>> {

	/**
	 * Vergleicht zwei Strings und als Rückgabewert wird entsprechend <0 ,0 oder
	 * >0 zurueckgegeben.
	 */

	public int compare(BinaryNode<T, U> arg0, BinaryNode<T, U> arg1) {

		int result = -1;

		if (arg0.getSchluessel().compareTo(arg1.getSchluessel()) > 0) {
			result = 1;
		}

		if (arg0.getSchluessel().compareTo(arg1.getSchluessel()) == 0) {
			result = 0;
		}

		return result;
	}
}
