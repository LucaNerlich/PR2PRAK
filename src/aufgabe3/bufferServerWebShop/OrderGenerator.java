package aufgabe3.bufferServerWebShop;

import java.util.ArrayList;

import aufgabe3.Customer;
import aufgabe3.Product;

public class OrderGenerator extends Thread {

	public final int MAX_IDLE_TIME = 100;

	private BoundedBuffer<Order> currentBuffer;
	private Order item;

	static Customer customer1 = new Customer("vA", "nA");
	static Customer customer2 = new Customer("vB", "nB");
	static Customer customer3 = new Customer("vC", "nC");
	static Customer customer4 = new Customer("vD", "nD");
	static Customer customer5 = new Customer("vE", "nE");
	static Customer customer6 = new Customer("vF", "nF");
	static Customer customer7 = new Customer("vG", "nG");
	static Customer customer8 = new Customer("vH", "nH");
	static Customer customer9 = new Customer("vI", "nI");
	static Customer customer10 = new Customer("vJ", "nJ");

	static Product product1 = new Product("Seife", 1.2);
	static Product product2 = new Product("Auto", 42000);
	static Product product3 = new Product("Computer", 1337);
	static Product product4 = new Product("Stueck Kaese", 4.99);
	static Product product5 = new Product("Wurst", 4.99);
	static Product product6 = new Product("Huhn", 12.99);
	static Product product7 = new Product("Salat", 0.89);
	static Product product8 = new Product("Broetchen", 3.25);
	static Product product9 = new Product("Sixpack Bier", 6.99);
	static Product product10 = new Product("Schnapps", 9.99);

	static ArrayList<Customer> customerListe = new ArrayList<Customer>();
	static ArrayList<Product> produktListe = new ArrayList<Product>();

	public OrderGenerator(BoundedBuffer<Order> buffer) {
		currentBuffer = buffer;
	}

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
				// Fuer unbestimmte Zeit anhalten
				pause();
			} 
		}
	}

	/**
	 * Helfermethode zur zufaelligen Generierung einer Bestellung
	 * 
	 * @return order Objekt
	 */
	private Order getContent() {
		int customerRndm = (int) (Math.random() * 10 + 1);
		int prductRndm = (int) (Math.random() * 10 + 1);

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
	 * Fuegt die Customer und Produkte in die Arraylist ein.
	 */
	public static void addToList() {
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
		// int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
		try {
			// Thread blockieren
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags fuer den eigenen Thread
			this.interrupt();
		}
	}
}
