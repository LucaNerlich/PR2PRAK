/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * WebShop.java
 */

package aufgabe3;

import aufgabe2.SortingCriterion;
import aufgabe3.bufferServerWebShop.BoundedBuffer;
import aufgabe3.bufferServerWebShop.Order;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse repraesentiert den Webshop und die Verwaltung der Customer (add,
 * remove und print)
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */
public class WebShop extends Thread {

	@SuppressWarnings("rawtypes")
	StringComparator stringComparator = new StringComparator();

	BinaryTree<String, Product> tree = new BinaryTree<String, Product>(
			stringComparator);


	public static BoundedBuffer<Order> currentBuffer;
	public static Order item;
	
	public static Customer customer1 = new Customer("vA", "nA");
	public static Customer customer2 = new Customer("vB", "nB");
	public static Customer customer3 = new Customer("vC", "nC");
	public static Customer customer4 = new Customer("vD", "nD");
	public static Customer customer5 = new Customer("vE", "nE");
	public static Customer customer6 = new Customer("vF", "nF");
	public static Customer customer7 = new Customer("vG", "nG");
	public static Customer customer8 = new Customer("vH", "nH");
	public static Customer customer9 = new Customer("vI", "nI");
	public static Customer customer10 = new Customer("vJ", "nJ");

	public static Product product1 = new Product("Seife", 1.2);
	public static Product product2 = new Product("Auto", 42000);
	public static Product product3 = new Product("Computer", 1337);
	public static Product product4 = new Product("Stueck Kaese", 4.99);
	public static Product product5 = new Product("Wurst", 4.99);
	public static Product product6 = new Product("Huhn", 12.99);
	public static Product product7 = new Product("Salat", 0.89);
	public static Product product8 = new Product("Broetchen", 3.25);
	public static Product product9 = new Product("Sixpack Bier", 6.99);
	public static Product product10 = new Product("Smartphone", 299.99);
	
	BinaryTreeHelper treeHelper = new BinaryTreeHelper();

	public WebShop(BoundedBuffer<Order> buffer) {
		currentBuffer = buffer;		
	}

	public void run() {

		while (!isInterrupted()) {
			statusmeldungZugriffswunsch();			
			item = currentBuffer.remove();	
			
			if (item != null) {
				System.err.println("Item removed: " + item + "\n");
			}

			if (!isInterrupted()) {				
				pause();
			}
		}
	}

	/**
	 * Gib einen Zugriffswunsch auf der Konsole aus.
	 */
	public void statusmeldungZugriffswunsch() {

		System.err.println("                                           "
				+ this.getName() + " moechte auf den Puffer zugreifen!");
	}

	/**
	 * Verbraucher benutzen diese Methode, um fuer eine Zufallszeit untaetig zu
	 * sein
	 */
	public void pause() {
		// int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
		try {
			// Thread blockieren
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags fuer den eigenen Thread
			this.interrupt();
		}
	}

	private ArrayList<Customer> kundenListe = new ArrayList<Customer>();

	/**
	 * Methoden f�r die Nutzerverwaltung
	 */

	/**
	 * Fuegt ein neues Element zur ArrayList hinzu.
	 * 
	 * @param vorname
	 *            -> repr�sentiert den Vornamen vom Kunden
	 * @param nachname
	 *            -> repr�sentiert den Nachnamen vom Kunden
	 */
	public void addCustomer(String vorname, String nachname) {
		kundenListe.add(new Customer(vorname, nachname));
	}

	/**
	 * Entfernt alle passenden Elemente
	 * 
	 * @param vorname
	 *            -> repr�sentiert den Vornamen vom Kunden
	 * @param nachname
	 *            -> repr�sentiert den Nachnamen vom Kunden
	 */
	public void removeCustomer(String vorname, String nachname) {
		// Customer rmvCustomer = null;
		for (int i = 0; i < kundenListe.size(); i++) {
			if (kundenListe.get(i).getVorname().equals(vorname)
					&& kundenListe.get(i).getNachname().equals(nachname)) {
				// rmvCustomer = kundenListe.get(i);
				kundenListe.remove(kundenListe.get(i));
				i--;
			}
		}
	}

	/**
	 * Soritert die Elemente der ArrayList. Nach dem Namen oder der ID von
	 * Customer.
	 * 
	 * @param comp
	 *            -> repr�sentiert den Comparator
	 * @return ausgabe -> repr�sentiert die Liste der Customer - sortiert
	 */
	public String printListOfCustomer(SortingCriterion comp) {

		switch (comp) {
		case SORT_BY_LASTNAME_FIRSTNAME:
			Collections.sort(kundenListe, new NachnameVornameComparator());
			break;

		case SORT_BY_ID:
			Collections.sort(kundenListe, new IdComparator());
			break;
		}

		String ausgabe = "";
		for (Customer customer : kundenListe) {
			ausgabe += customer.toString() + "\n";
		}
		return ausgabe;
	}

	/**
	 * Gibt einfach die ArrayList aus.
	 * 
	 * @return ausgabe -> repr�sentiert die Liste der Customer - unsortiert
	 */
	public String printListUnsorted() {
		String ausgabe = "";
		for (Customer customer : kundenListe) {
			ausgabe += customer.toString() + "\n";
		}
		return ausgabe;
	}
}
