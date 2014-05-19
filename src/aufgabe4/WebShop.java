/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * WebShop.java
 */

package aufgabe4;

import aufgabe2.SortingCriterion;
import aufgabe3.*;
import aufgabe3.bufferServerWebShop.BoundedBuffer;
import aufgabe3.bufferServerWebShop.Order;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse repraesentiert den Webshop und die Verwaltung der Customer (add,
 * remove und print)
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de) *
 */
public class WebShop extends Thread {

	StringComparator stringComparator = new StringComparator();

	BinaryTree<String, Product> tree = new BinaryTree<String, Product>(
			stringComparator);

	public static BoundedBuffer<Order> currentBuffer;
	public static Order item;

	BinaryTreeHelper treeHelper = new BinaryTreeHelper();

	public WebShop(BoundedBuffer<Order> buffer) {
		currentBuffer = buffer;
	}

	public void run() {

		while (!isInterrupted()) {
			statusmeldungZugriffswunsch();
			item = currentBuffer.remove();

			if (item != null) {
				System.err.println("\nStatus der Bestellung:"
						+ "\nSuccessfully processed: Order " + item + "\n");

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

		System.err.println("<<<< " +this.getName()
				+ " moechte auf den Puffer zugreifen!");
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
	 * Methoden fuer die Nutzerverwaltung
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
	 *            -> repraesentiert den Vornamen vom Kunden
	 * @param nachname
	 *            -> repraesentiert den Nachnamen vom Kunden
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
	 *            -> repraesentiert den Comparator
	 * @return ausgabe -> repraesentiert die Liste der Customer - sortiert
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
	 * @return ausgabe -> repraesentiert die Liste der Customer - unsortiert
	 */
	public String printListUnsorted() {
		String ausgabe = "";
		for (Customer customer : kundenListe) {
			ausgabe += customer.toString() + "\n";
		}
		return ausgabe;
	}
}

/*
	public static Customer customer1 = new Customer("vAnton", "nA");
	public static Customer customer2 = new Customer("vBjoern", "nB");
	public static Customer customer3 = new Customer("vClaus", "nC");
	public static Customer customer4 = new Customer("vDetlef", "nD");
	public static Customer customer5 = new Customer("vErwin", "nE");
	public static Customer customer6 = new Customer("vFriedrich", "nF");
	public static Customer customer7 = new Customer("vGregor", "nG");
	public static Customer customer8 = new Customer("vHubert", "nH");
	public static Customer customer9 = new Customer("vIver", "nI");
	public static Customer customer10 = new Customer("vJoern", "nJ");

	public static Product product1 = new Product("Seife", 1.2);
	public static Product product2 = new Product("Auto", 42000);
	public static Product product3 = new Product("Computer", 1250);
	public static Product product4 = new Product("Stueck Kaese", 5.99);
	public static Product product5 = new Product("Wurst", 3.99);
	public static Product product6 = new Product("Huhn", 12.49);
	public static Product product7 = new Product("Salat", 1.29);
	public static Product product8 = new Product("Broetchen", 3.25);
	public static Product product9 = new Product("Sixpack Bier", 7.99);
	public static Product product10 = new Product("Smartphone", 299.99);
*/
