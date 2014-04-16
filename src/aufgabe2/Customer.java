/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * Customer.java
 */
package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * Customer.java
 */

/**
 * Repraesentiert die Nutzer des Webshops
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */
public class Customer {
	private String vorname;
	private String nachname;
	private final int id;
	public static int objektzaehler = 0; // Static damit Objekt unabhängig
											// (eindeutig) gezaehlt werden kann
	private String schluessel;

	/**
	 * Konstruktor
	 * 
	 * @param vorname
	 * @param nachname
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