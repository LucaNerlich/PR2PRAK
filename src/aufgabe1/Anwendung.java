package aufgabe1;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * Anwendung.java
 */


/**
 * Diese Klasse repräsentiert die Anwendung des WebShops und  die Ausgabe
 * @author (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */
public class Anwendung {

	public static void main(String[] args) {
		
			System.out.println("###Test - Nach Nachnamen sortieren:");	
			
			WebShop webShop = new WebShop();
						
			webShop.addCustomer("Luca", "A");
			webShop.addCustomer("Lucc", "C");
			webShop.addCustomer("Lucc", "C"); // Wenn 2x exakt der gleiche Vorname/Nachname sortiert er nach id
			webShop.addCustomer("Lucb", "B");
			webShop.addCustomer("Daniel", "S");
			System.out.println(webShop.printListOfCustomer(SortingCriterion.SORT_BY_LASTNAME_FIRSTNAME));
			
			System.out.println("###Test2 - Daniel löschen und unsortiert ausgeben:");
						
			webShop.addCustomer("Hans","z");
			webShop.removeCustomer("Daniel", "S");
			webShop.addCustomer("Lucb", "A");
			webShop.addCustomer("Bjoern", "B");
			
			System.out.println(webShop.printListUnsorted());			
			
			System.out.println("###Test3 - Nach ID sortiert ausgeben:");			
			System.out.println(webShop.printListOfCustomer(SortingCriterion.SORT_BY_ID));	
			
			System.out.println("###Test4 - Weitere Kunden hinzufügen und nach Nachname sortieren:");
			webShop.addCustomer("Jogi", "Löw");
			webShop.addCustomer("Uli", "Hoeneß");
			webShop.addCustomer("Hansi", "Flick");
			webShop.addCustomer("Dieter", "Hoeneß");
			webShop.addCustomer("Hansi", "Flick");
			webShop.addCustomer("Mehmet", "Scholl");
			webShop.addCustomer("Marco", "Reus");
			System.out.println(webShop.printListOfCustomer(SortingCriterion.SORT_BY_LASTNAME_FIRSTNAME));
			
			System.out.println("###Test5 - Nach ID sortieren:");
			System.out.println(webShop.printListOfCustomer(SortingCriterion.SORT_BY_ID)); 
			
			System.out.println("_________________________");
			
			webShop.addCustomer("aaaa", "cccc");
			webShop.addCustomer("aaaa", "cccc");
			System.out.println(webShop.printListUnsorted());
			webShop.removeCustomer("aaaa", "cccc");
			System.out.println(webShop.printListUnsorted());
	}
}
