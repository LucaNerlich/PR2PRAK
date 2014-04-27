package aufgabe3;
/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * NodeException.java
 */

/**
 * Hilfsklasse fuer den Tree. Gibt die Anzahl der Knoten und die Tiefe des Baums.
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 *
 */
public class BinaryTreeHelper {

	/**
	 * Guckt fuer jeden Knoten den Linken und Rechten an. Zaehlt sich selber dann hoch
	 * @param current -> repräsentiert den aktuellen Knoten
	 * @return -> gibt die Anzahl der gezählten Knoten wieder
	 */
	public static <T, U> int countNodes(BinaryNode<T, U> current) {

		if (current != null) {
			return 1 + countNodes(current.getLeft())
					+ countNodes(current.getRight());
		}

		else {
			return 0;
		}
	}

	/**
	 * Fuer jede Ebene wird "max" berechnet!
	 * @param current  -> repräsentiert den aktuellen Knoten
	 * @return -> gibt die Tiefe der Knoten wieder
	 */
	public static <T, U> int treeDepth(BinaryNode<T, U> current) {

		if (current != null) {
			return 1 + Math.max(countNodes(current.getLeft()),
					countNodes(current.getRight()));
		}

		else {
			return 0;
		}
	}
}
