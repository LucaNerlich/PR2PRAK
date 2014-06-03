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
	 * Verbraucher benutzen diese Methode, um fuer eine Zeit  von 1sek untaetig zu
	 * sein
	 */
	public void pause() {

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