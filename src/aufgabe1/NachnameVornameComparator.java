/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * NachnameVornameComparator.java
 */
package aufgabe1;

import java.util.Comparator;

/**
 * Diese Klasse repr�sentiert den Nachname/Vorname-Comparator (Vergleich von Vor- und Nachnamen)
 * @author  (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 * 
 */
public class NachnameVornameComparator implements Comparator<Customer>{

	/**
	 * Override Compare f�r Nachname/Vorname 
	 * @param Customer x, Customer y
	 * @return Ergebniswert aus dem Vergleich
	 */
	public int compare(Customer arg0, Customer arg1) {
		
		int result = 0;
		
		result = arg0.getNachname().compareTo(arg1.getNachname());	
		
		if(result == 0){
			result = arg0.getVorname().compareTo(arg1.getVorname());
		}
		return result;
	}

}