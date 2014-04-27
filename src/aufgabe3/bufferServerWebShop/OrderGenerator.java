package aufgabe3.bufferServerWebShop;

import java.util.ArrayList;

import aufgabe3.Customer;
import aufgabe3.Product;

public class OrderGenerator extends Thread {

	public final int MAX_IDLE_TIME = 100;

	private BoundedBuffer<Order> currentBuffer;
	private Order item;

	public OrderGenerator(BoundedBuffer<Order> buffer) {
		currentBuffer = buffer;
	}

	Customer customer1 = new Customer("vA", "nA");
	Customer customer2 = new Customer("vB", "nB");
	Customer customer3 = new Customer("vC", "nC");
	Customer customer4 = new Customer("vD", "nD");
	Customer customer5 = new Customer("vE", "nE");
	Customer customer6 = new Customer("vF", "nF");
	Customer customer7 = new Customer("vG", "nG");
	Customer customer8 = new Customer("vH", "nH");
	Customer customer9 = new Customer("vI", "nI");
	Customer customer10 = new Customer("vJ", "nJ");

	Product product1 = new Product("Seife", 1.2);
	Product product2 = new Product("Auto", 42000);
	Product product3 = new Product("Computer", 1337);
	Product product4 = new Product("Stueck Kaese", 4.99);
	Product product5 = new Product("Wurst", 4.99);
	Product product6 = new Product("Huhn", 12.99);
	Product product7 = new Product("Salat", 0.89);
	Product product8 = new Product("Broetchen", 3.25);
	Product product9 = new Product("Sixpack Bier", 6.99);
	Product product10 = new Product("Schnapps", 9.99);

	ArrayList<Customer> customerListe = new ArrayList<Customer>();
	ArrayList<Product> produktListe = new ArrayList<Product>();

	public void run() {
		customerListe.add(customer1);
		customerListe.add(customer2);
		customerListe.add(customer3);
		customerListe.add(customer4);
		customerListe.add(customer5);
		customerListe.add(customer6);
		customerListe.add(customer7);
		customerListe.add(customer8);
		customerListe.add(customer9);
		customerListe.add(customer10);

		produktListe.add(product1);
		produktListe.add(product2);
		produktListe.add(product3);
		produktListe.add(product4);
		produktListe.add(product5);
		produktListe.add(product6);
		produktListe.add(product7);
		produktListe.add(product8);
		produktListe.add(product9);
		produktListe.add(product10);

		while (!isInterrupted()) {

			/**
			 * Order-Objekt erzeugen
			 **/
			item = getContent();
			statusmeldungZugriffswunsch();

			// Puffer-Zugriffsmethode aufrufen --> Synchronisation ueber den
			// Puffer!
			currentBuffer.enter(item);

			if (!isInterrupted()) {
				// Fuer unbestimmte Zeit anhalten
				pause();
			}
		}
	}

	/**
	 * Helfermethode zur zufaelligen Generierung einer Bestellung
	 * @return order Objekt
	 */
	private Order getContent() {
		int customerRndm = (int) (Math.random() * 10);
		int prductRndm = (int) (Math.random() * 10);

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
		Order order = new Order(customerCache, productCache);
		
		System.err.println(order.toString());
		return order;
	}

	/**
	 * Gib einen Zugriffswunsch auf der Konsole aus
	 */
	public void statusmeldungZugriffswunsch() {

		System.err.println("                                           "
				+ this.getName() + " moechte auf den Puffer zugreifen!");
	}

	/*
	 * Erzeuger benutzen diese Methode, um fuer eine Zufallszeit untaetig zu
	 * sein
	 */
	public void pause() {
		int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
		try {
			// Thread blockieren
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags fuer den eigenen Thread
			this.interrupt();
		}
	}
}
