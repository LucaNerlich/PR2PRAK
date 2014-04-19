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
		
		/**
		 * Den return-Wert der compare Methode zwischenspeichern und entsprechend auf 1,-1 oder 0 prüfen. 
		 */
		int result = comp.compare(new Customer("","aNachname"), new Customer("","zNachname"));
		assertTrue(result < 0);
		
		result = comp.compare(new Customer("","zNachname"), new Customer("","aNachname"));
		assertTrue(result > 0);
		
		result = comp.compare(new Customer("","aNachname"), new Customer("","aNachname"));		
		assertEquals(result, 0);
		
		result = comp.compare(new Customer("aVorname","aNachname"), new Customer("zVorname","aNachname"));
		assertTrue(result < 0);
		
		result = comp.compare(new Customer("zVorname","aNachname"), new Customer("aVorname","aNachname"));
		assertTrue(result > 0);
		
		result = comp.compare(new Customer("aVorname","bNachname"), new Customer("aVorname","bNachname"));
		assertEquals(result, 0);

	}
	
	@Test
	public void testIdComparator(){
		IdComparator idComp = new IdComparator();
		// Customer erst erstellen, damit wir die IDs klar definiert haben.
		
		Customer customer1 = new Customer("aVName","bNName");
		Customer customer2 = new Customer("cVName","dNName");
		Customer customer3 = new Customer("eVName","fNName");
		
		//Nur  die ID prüfen
		assertEquals(idComp.compare(customer1,customer2), -1);
		assertEquals(idComp.compare(customer2,customer1), 1);
		assertEquals(idComp.compare(customer2,customer2), 0);
		assertEquals(idComp.compare(customer1,customer3), -1);
	}
	/*
	@Test
	public void testHinzufuegen(){
		WebShop webShopTest = new WebShop();		
		
		webShopTest.addCustomer("Paul", "Panzer");
		
		String result = webShopTest.getVorname();
		assertTrue(result.equals("Paul"));
	}
	*/
	
    
	/*
	@Test
	public void testEntfernen(){
		WebShop webShopTest2 = new WebShop();		
	
		webShopTest2.addCustomer("Klaus", "dir");
		String result = webShopTest2.getVorname();
		webShopTest2.removeCustomer("Klaus", "dir");
		assertTrue(result == NULL));
	}
	 */
	
}


