/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * OrderGenerator.java
 */

package aufgabe3.bufferServerWebShop;

import aufgabe3.Customer;
import aufgabe3.Product;
import aufgabe3.WebShop;

import java.util.ArrayList;

public class OrderGenerator extends Thread {

	public final int MAX_IDLE_TIME = 100;

	private BoundedBuffer<Order> currentBuffer;
	private Order item;


    /**
     * ArrayListen zum Speichern der Kunden und Produkte.
     * Hiermit wird dann die Bestellung generiert.
     */
	static ArrayList<Customer> customerListe = new ArrayList<Customer>();
	static ArrayList<Product> produktListe = new ArrayList<Product>();

	public OrderGenerator(BoundedBuffer<Order> buffer) {
		currentBuffer = buffer;
	}

    /**
     * Dieser Thread generiert zufaellig eine "Bestellung" (customer/product)
     */
	public void run() {
		while (!isInterrupted() && (currentBuffer.getCounter() <= 9)) {

			/**
			 * Order-Objekt erzeugen
			 **/
			item = getContent();
			statusmeldungZugriffswunsch();

			// Puffer-Zugriffsmethode aufrufen --> Synchronisation ueber den
			// Puffer!
			currentBuffer.enter(item);

			
			if (!isInterrupted()) {			
				pause();
			}  
		}
	}

	/**
	 * Helfermethode zur zufaelligen Generierung einer Bestellung	 *
	 * @return order Objekt
	 */
	private Order getContent() {
		// +1 ?
		int customerRndm = (int) (Math.random() * 10);
		int prductRndm = (int) (Math.random() * 10);

		// System.err.println("CUSTOMERrndm "+ customerRndm);
		// System.err.println("PRODUCTrndm "+ prductRndm);

		Customer customerCache = null;
		Product productCache = null;

		for (Customer customer : customerListe) {
			if (customer.getId() == customerRndm) {
				customerCache = customer;
			}
		}

		for (Product product : produktListe) {
			if (product.getId() == prductRndm) {
				productCache = product;
			}
		}

		// sychro Methode zum Zaehlen der Bestellvorgaenge
		currentBuffer.counter();

		System.err.println("COUNTER: " + currentBuffer.getCounter());
		Order order = new Order(customerCache, productCache);

		System.err.println(order.toString());
		return order;
	}

	/**
	 * Fuegt die Customer und Produkte (aus dem Webshop) in die Arraylist ein.
	 */
	public static void addToList() {
		customerListe.add(WebShop.customer1);
		customerListe.add(WebShop.customer2);
		customerListe.add(WebShop.customer3);
		customerListe.add(WebShop.customer4);
		customerListe.add(WebShop.customer5);
		customerListe.add(WebShop.customer6);
		customerListe.add(WebShop.customer7);
		customerListe.add(WebShop.customer8);
		customerListe.add(WebShop.customer9);
		customerListe.add(WebShop.customer10);

		produktListe.add(WebShop.product1);
		produktListe.add(WebShop.product2);
		produktListe.add(WebShop.product3);
		produktListe.add(WebShop.product4);
		produktListe.add(WebShop.product5);
		produktListe.add(WebShop.product6);
		produktListe.add(WebShop.product7);
		produktListe.add(WebShop.product8);
		produktListe.add(WebShop.product9);
		produktListe.add(WebShop.product10);
	}

	/**
	 * Gib einen Zugriffswunsch auf der Konsole aus
	 */
	public void statusmeldungZugriffswunsch() {

		System.err.println("                                           "
				+ this.getName() + " moechte auf den Puffer zugreifen! \n");
	}

    /**
     * schlaeft fuer die angebene Zeit.
     */
	public void pause() {
		// int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
		try {
			// Thread blockieren
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags fuer den eigenen Thread
			this.interrupt();
		}
	}
}
