/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * Customer.java
 */
package aufgabe2;

/**
 * Die Klasse Customer repraesentiert die Nutzer des Webshops
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */

/**
* Die Objektvariable Vorname repräsentiert den Vornamen vom Kunden,
* die Objektvariable Nachname repräsentiert den Nachnamen vom Kunden,
* die Objektvariable ID repräsentiert eine eindeutige Nummer die dem Kunden beim erzeugen zugeordnet wird,
* die Objektvariable (Klassenvariale) objektzaehler erzeugt unabhängig von den Objekten die ID für den Kunden,
* die Objektvariable schlüssel repräsentiert den Key für die Knoten und wird aus Vor- und Nachname generiert.
*/
public class Customer {
	private String vorname;
	private String nachname;
	private final int id;
	public static int objektzaehler = 0;									
	private String schluessel;

	/**
	* Konstruktor erzeugt das Kundenobjekt 
	 * @param vorname ist der Vorname vom Kunden
	 * @param nachname ist der Nachname vom Kunden
	 */
	public Customer(String vorname, String nachname) {
		objektzaehler++;
		this.vorname = vorname;
		this.nachname = nachname;
		this.id = objektzaehler;
		generateKey();
	}

	/**
	 * Getter und Setter
	 */

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getId() {
		return id;
	}

	private void generateKey() {
		schluessel = this.getNachname() + ", " + this.getVorname();
	}

	public String getSchluessel() {
		return schluessel;
	}

	/**
	 * Gibt die Objektvariablen als String geordnet zurueck.
	 */
	@Override
	public String toString() {
		return nachname + ", " + vorname + " " + "(id: " + id + ")";
	}
}