/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * WebShop.java
 */

package aufgabe1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse repr�sentiert den Webshop und die Verwaltung der Customer (add, remove und print)
 * @author (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */
public class WebShop {

	private ArrayList<Customer> kundenListe = new ArrayList<Customer>();
	
	/**
	* Methoden f�r die Nutzerverwaltung
	*/ 
	
	/**
	 * Fuegt ein neues Element zur ArrayList hinzu.
	 * @param vorname
	 * @param nachname
	 */
	public void addCustomer(String vorname, String nachname){
		kundenListe.add(new Customer(vorname, nachname));
	}
	
	/**
	 * Entfernt das passende Element
	 * @param vorname
	 * @param nachname
	 */
	/* // bei doppelten Eintr�gen hintereinander wird nur der erste gel�scht.
	public void removeCustomer(String vorname, String nachname){
		Customer rmvCustomer = null;
		for (Customer customer : kundenListe){
			if(customer.getVorname().equals(vorname) && customer.getNachname().equals(nachname)){
				rmvCustomer = customer;
				}			
			kundenListe.remove(rmvCustomer);
		}		
	} */
	
	/**
	 * Entfernt alle passenden Elemente
	 * @param vorname
	 * @param nachname
	 */
	public void removeCustomer(String vorname, String nachname){
		// Customer rmvCustomer = null;
		for(int i = 0;i < kundenListe.size();i++){
			if(kundenListe.get(i).getVorname().equals(vorname) && kundenListe.get(i).getNachname().equals(nachname)){
				// rmvCustomer = kundenListe.get(i);
				kundenListe.remove(kundenListe.get(i));
				i--;
			}			
		}
	}
	
	/**
	 * Soritert die Elemente der ArrayList. Nach Name oder ID.
	 * @param comp
	 * @return Liste der Customer - sortiert
	 */
	public String printListOfCustomer(SortingCriterion comp){ 	
		
		switch(comp){
		case SORT_BY_LASTNAME_FIRSTNAME :  Collections.sort(kundenListe, new NachnameVornameComparator());
			break;
		
		case SORT_BY_ID : Collections.sort(kundenListe, new IdComparator());
			break;		
		}	
		
		String ausgabe = "";
		for (Customer customer : kundenListe){
			ausgabe += customer.toString() + "\n";         
        }		
        return ausgabe;
	}
	
	/**
	 * Gibt einfach die ArrayList aus.
	 * @return Liste der Customer - unsortiert
	 */
	public String printListUnsorted(){
		String ausgabe = "";
		for (Customer customer : kundenListe){
			ausgabe += customer.toString() + "\n";         
        }		
        return ausgabe;		
	}
}
