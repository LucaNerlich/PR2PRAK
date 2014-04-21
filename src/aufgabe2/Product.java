package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * NodeException.java
 */

/**
 * Standard Klasse zur Repräsentation der Produkte
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */
public class Product {
	/**
	 * Die Objektvariable Preis repräsentiert den Preis vom Produkt, die
	 * Objektvariable Name repräsentiert den Namen vom Produkt,
	 */
	private double preis;
	private String name;

	/**
	 * Konstruktor erzeugt das Produktobjekt
	 */
	public Product(String name, double preis) {
		this.preis = preis;
		this.name = name;
	}

	@Override
	/**
	 * Repräsentiert das Produktobjekt 
	 */
	public String toString() {
		return "Product [preis=" + preis + ", name=" + name + "]";
	}

	public double getWert() {
		return preis;
	}

	public void setWert(double preis) {
		this.preis = preis;
	}

	public String getSchluessel() {
		return name;
	}

	public void setSchluessel(String name) {
		this.name = name;
	}

}
