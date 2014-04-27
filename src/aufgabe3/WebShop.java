/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 1
 * WebShop.java
 */

package aufgabe3;

import java.util.ArrayList;
import java.util.Collections;

import aufgabe2.SortingCriterion;
import aufgabe3.bufferServerWebShop.Order;

/**
 * Diese Klasse repräsentiert den Webshop und die Verwaltung der Customer (add,
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

	public final int MAX_IDLE_TIME = 100;
	BinaryTreeHelper treeHelper = new BinaryTreeHelper();

	/**
	 * Der WebShop wird mit Produktobjekten gefüllt
	 */
	

	@SuppressWarnings("static-access")
	public WebShop() {
		
		Product product1 = new Product("Seife", 1.2);
		Product product2 = new Product("Auto", 42000);
		Product product3 = new Product("Computer", 1337);
		Product product4 = new Product("Stueck Kaese", 4.99);
		
		try {
			tree.addKnoten(
					new BinaryNode<String, Product>(product1.getSchluessel(),
							product1), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Product>(product2.getSchluessel(),
							product2), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Product>(product3.getSchluessel(),
							product3), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Product>(product4.getSchluessel(),
							product4), tree.getWurzel());

			System.out
					.println("\n-------------- Ausgabe bei Produkten --------------");
			tree.ausgeben();

			System.out
					.println("\n-------------- Ausgabe Knotenanzahl + Tiefe bei Produkten --------------");
			System.out.println("Knotenanzahl: "
					+ treeHelper.countNodes(tree.getWurzel()));
			System.out.println("Knotentiefe: "
					+ treeHelper.treeDepth(tree.getWurzel()));
		}

		catch (NodeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

	private ArrayList<Customer> kundenListe = new ArrayList<Customer>();

	/**
	 * Methoden für die Nutzerverwaltung
	 */

	/**
	 * Fuegt ein neues Element zur ArrayList hinzu.
	 * 
	 * @param vorname
	 *            -> repräsentiert den Vornamen vom Kunden
	 * @param nachname
	 *            -> repräsentiert den Nachnamen vom Kunden
	 */
	public void addCustomer(String vorname, String nachname) {
		kundenListe.add(new Customer(vorname, nachname));
	}

	/**
	 * Entfernt alle passenden Elemente
	 * 
	 * @param vorname
	 *            -> repräsentiert den Vornamen vom Kunden
	 * @param nachname
	 *            -> repräsentiert den Nachnamen vom Kunden
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
	 *            -> repräsentiert den Comparator
	 * @return ausgabe -> repräsentiert die Liste der Customer - sortiert
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
	 * @return ausgabe -> repräsentiert die Liste der Customer - unsortiert
	 */
	public String printListUnsorted() {
		String ausgabe = "";
		for (Customer customer : kundenListe) {
			ausgabe += customer.toString() + "\n";
		}
		return ausgabe;
	}
}
