package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * BinaryNode.java
 */

/**
 * Die generische Klasse BinaryNode repraesentiert einen einzlnen Knoten des Baums
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */

/**
 * Schlüssel hat die Typvariable T zugewiesen Wert hat die Typvariable U
 * zugewiesen Typvariablen sind Platzhalter für einen konkreten Typen Jeder
 * Knoten kann einen linken und rechten Kinderknoten haben
 */
public class BinaryNode<T, U> {

	private T schluessel;
	private U wert;
	private BinaryNode<T, U> left;
	private BinaryNode<T, U> right;

	/**
	 * Konstruktor des Knoten. Kriegt einen Schluessel und einen Wert. Mit
	 * diesen wird er im Baum repraesentiert.
	 */
	public BinaryNode(T schluessel, U wert) {
		this.schluessel = schluessel;
		this.wert = wert;
	}

	/**
	 * Getter und Setter
	 */

	public BinaryNode<T, U> getLeft() {
		return left;
	}

	public BinaryNode<T, U> getRight() {
		return right;
	}

	public void setLeft(BinaryNode<T, U> left) {
		this.left = left;
	}

	public void setRight(BinaryNode<T, U> right) {
		this.right = right;
	}

	public T getSchluessel() {
		return schluessel;
	}

	public void setSchluessel(T schluessel) {
		this.schluessel = schluessel;
	}

	public U getWert() {
		return wert;
	}

	public void setWert(U wert) {
		this.wert = wert;
	}

	/**
	 * Gibt den Wert und den Schluessel des Knoten aus.
	 */
	@Override
	public String toString() {
		return "BinaryNode [schluessel=" + schluessel + ", wert=" + wert;
	}

}
