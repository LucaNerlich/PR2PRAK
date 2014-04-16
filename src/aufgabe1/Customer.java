/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * Customer.java
 */
package aufgabe1;

/**
 * Diese Klasse repräsentiert die Benutzer vom Webshop
 * @author  (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */

/**
* Objektvariablen
*/
public class Customer {
	private String vorname;
	private String nachname;
	private final int id;
	public static int objektzaehler = 0; //Static damit Objekt unabhängig (eindeutig) gezählt werden kann
	
	/**
	 * Konstruktor
	 * @param vorname
	 * @param nachname
	 */
	public Customer(String vorname, String nachname){
		objektzaehler++;
		this.vorname = vorname;
		this.nachname = nachname;
		this.id = objektzaehler;		
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

	/**
	 * Gibt die Objektvariablen als String geordnet zurueck.
	 */
	@Override
	public String toString() {
		return nachname + ", " +  vorname + " " + "(id: " + id + ")";
	}
}