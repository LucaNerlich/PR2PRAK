/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * CustomerTesting.java
 */
package aufgabe1;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Diese Klasse repräsentiert den JUnit-Test für unsere beiden Comparatoren
 * @author  (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */
public class CustomerTesting {

	@Test
	public void testNachnameVornameComparator(){
		NachnameVornameComparator comp = new NachnameVornameComparator();
		
		//den return-Wert der compare Methode zwischenspeichern und entsprechend auf 1,-1 oder 0 setzen.
		int result = comp.compare(new Customer("","aNachname"), new Customer("","zNachname"));
		if(result < 0){
			result = -1;
		}
		assertEquals(result, -1);
		
		result = comp.compare(new Customer("","zNachname"), new Customer("","aNachname"));
		if(result > 0){
			result = 1;
		}
		assertEquals(result, 1);
		
		result = comp.compare(new Customer("","aNachname"), new Customer("","aNachname"));		
		assertEquals(result, 0);
		
		result = comp.compare(new Customer("aVorname","aNachname"), new Customer("zVorname","aNachname"));
		if(result < 0){
			result = -1;
		}
		assertEquals(result, -1);
		
		result = comp.compare(new Customer("zVorname","aNachname"), new Customer("aVorname","aNachname"));
		if(result > 0){
			result = 1;
		}
		assertEquals(result, 1);
		
		result = comp.compare(new Customer("aVorname","bNachname"), new Customer("aVorname","bNachname"));
		assertEquals(result, 0);
		//nur Nachname -- schlaegt fehlt, wenn die zu vergleichenden Werte mehr als 1 Abstand im Alphabet haben.
		/*
		assertEquals(comp.compare(new Customer("","aNachname"), new Customer("","zNachname")), -1);
		assertEquals(comp.compare(new Customer("","bNachname"), new Customer("","aNachname")), 1);
		assertEquals(comp.compare(new Customer("","aNachname"), new Customer("","aNachname")), 0);
		
		//erst Nachname, dann Vorname
		assertEquals(comp.compare(new Customer("aVorname","aNachname"), new Customer("bVorname","aNachname")), -1);
		assertEquals(comp.compare(new Customer("bVorname","aNachname"), new Customer("aVorname","aNachname")), 1);
		assertEquals(comp.compare(new Customer("aVorname","bNachname"), new Customer("aVorname","bNachname")), 0);
		*/
	}
	
	@Test
	public void testIdComparator(){
		IdComparator idComp = new IdComparator();
		// Customer erst Erstellen, damit wir die IDs klar definiert haben.
		
		Customer customer1 = new Customer("aVName","bNName");
		Customer customer2 = new Customer("cVName","dNName");
		Customer customer3 = new Customer("eVName","fNName");
		
		//nur Id
		assertEquals(idComp.compare(customer1,customer2), -1);
		assertEquals(idComp.compare(customer2,customer1), 1);
		assertEquals(idComp.compare(customer2,customer2), 0);
		assertEquals(idComp.compare(customer1,customer3), -1);
	}
}