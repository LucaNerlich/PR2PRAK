package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * BinaryTree.java
 */

/**
 * Dies ist die Hauptklasse des Binaerbaums
 *  * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */
public class BinaryTree<T, U> {

	private BinaryNode<T, U> wurzel;
	@SuppressWarnings("rawtypes")
	private StringComparator comp;

	/**
	 * Konstruktor des Baums. Bekommt den Comparator uebergeben, nachdem die
	 * Inhalte sortiert / angelegt werden sollen.
	 */
	@SuppressWarnings("rawtypes")
	public BinaryTree(StringComparator comp) {
		this.comp = comp;
	}

	/**
	 * Getter und Setter
	 */

	public BinaryNode<T, U> getWurzel() {
		return wurzel;
	}

	public void setWurzel(BinaryNode<T, U> wurzel) {
		this.wurzel = wurzel;
	}

	/**
	 * Hauptmethode des Baums. Bekommt einen neuen Knoten und platziert diesen
	 * entsprechenden dem definierten Comparator. Geht rekursiv den vorhandenen
	 * Baum durch, um den besten Platz zu finden.
	 * 
	 * @param neu -> repräsentiert den neuen Knoten
	 * @param current -> repräsentiert den aktuellen Knoten
	 * @throws NodeException -> Kann eine NodeException werfen wenn ein Knoten
	 * bereits mit demselben Schlüssel vorhanden ist
	 */
	@SuppressWarnings("unchecked")
	public void addKnoten(BinaryNode<T, U> neu, BinaryNode<T, U> current)
			throws NodeException {

		if (wurzel == null) {
			this.wurzel = neu;
			// current = wurzel;

		}

		else if (comp.compare(neu, current) == -1) {
			if (current.getLeft() == null) {
				current.setLeft(neu);
			} else {
				addKnoten(neu, current.getLeft());
			}
		}

		else if (comp.compare(neu, current) == 1) {
			if (current.getRight() == null) {
				current.setRight(neu);
			} else {
				addKnoten(neu, current.getRight());
			}
		}

		else {
			throw new NodeException(
					"Es gibt bereits ein Element mit folgendem Schluessel: "
							+ neu.getSchluessel());
		}
	}

	/**
	 * Testmethode zur Ausgabe aller Knoten
	 * @param b
	 */
	private void draw(BinaryNode<T, U> b) {

		if (b != null) {
			draw(b.getLeft());
			System.out.println(b.toString());
			draw(b.getRight());
		}
	}

	/**
	 * Aufruf der draw Methode, damit keine Fehler bei der Knotenuebergabe
	 * entstehen koennen.
	 */
	public void ausgeben() {
		draw(wurzel);
	}
	
	/**
	 * Aufruf der Hilfsmethode findKeyHelper um einen Schlüssel zu finden
	 * @param givenKey -> repräsentiert den gesuchten Wert
	 */
	public U findKey(T givenKey) {

		/*
		 * findKeyHelper(new BinaryNode<T, U>(givenKey, null), wurzel);
		 * return value;
		 */

		return findKeyHelper(new BinaryNode<T, U>(givenKey, null), wurzel);
	}

	/**
	 * Hilfsmethode zur FindKeymethode. Sucht zum gegeben Key den passenden Wert.
	 * @param nodeFind -> repräsentiert den gesuchten Knotenschlüssel
	 * @param current -> repräsentiert den aktuellen Knoten
	 * @return value -> repräsentiert den gesuchten Wert 
	 */
	@SuppressWarnings("unchecked")
	private U findKeyHelper(BinaryNode<T, U> nodeFind, BinaryNode<T, U> current) {

		U value = null;

		if (wurzel.getSchluessel().equals(nodeFind.getSchluessel())) {
			value = wurzel.getWert();
		}

		else if (comp.compare(nodeFind, current) == -1) {
			if (current.getLeft() == null) {
				System.out.println("Es gibt kein Objekt mit dem Schluessel: "
						+ nodeFind.getSchluessel());
			} else {
				System.err.println(current.getSchluessel());
				return findKeyHelper(nodeFind, current.getLeft()); // return
																	// wichtig
																	// fuers
																	// hochreichen
			}
		}

		else if (comp.compare(nodeFind, current) == 1) {
			if (current.getRight() == null) {
				System.out.println("Es gibt kein Objekt mit dem Schluessel: "
						+ nodeFind.getSchluessel());
			} else {
				System.err.println(current.getSchluessel());
				return findKeyHelper(nodeFind, current.getRight()); // return
																	// wichtig
																	// fuers
																	// hochreichen
			}
		}

		else if (comp.compare(nodeFind, current) == 0) {
			System.err.println(current.getWert());
			return current.getWert(); // return wichtig fuers hochreichen
		}
		return value;
	}
}
