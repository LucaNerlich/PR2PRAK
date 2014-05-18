/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * OrderGenerator.java
 */

package aufgabe3.bufferServerWebShop;

/**
 * Diese Klasse repraesentiert unseren Generator fuer Bestellungen, die
 * Bestellung wird zufaellig aus Product/Customer erzeugt dient als Erzeuger
 */
public class OrderGenerator extends Thread {

	public final int MAX_IDLE_TIME = 100;

	private BoundedBuffer<Order> currentBuffer;
	private Order item;

	/**
	 * ArrayListen zum Speichern der Kunden und Produkte. Hiermit wird dann die
	 * Bestellung generiert.
	 */
	// static ArrayList<Customer> customerListe = new ArrayList<Customer>();
	// static ArrayList<Product> produktListe = new ArrayList<Product>();

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
			item = AnwendungBuffer.getContent();
			statusmeldungZugriffswunsch();

			// Puffer-Zugriffsmethode aufrufen --> Synchronisation ueber den
			// Puffer! Fuegt unsere Bestellung Customer + Product in den Puffer
			currentBuffer.enter(item);
            currentBuffer.counter();
            System.err
                    .println("\nBestellungsnummer: " + currentBuffer.getCounter()
                            +"\nKunde: " + item.toString()
                            + " im Warenkorb.\n----------------------------------------------"+
                            "\n");

			if (!isInterrupted()) {
				pause();
			}
		}
	}

	/**
	 * Helfermethode zur zufaelligen Generierung einer Bestellung
	 * 
	 * @return order Objekt
	 */

    /*
	private Order getContent() {
		// +1 da er nach einem Kommawert nur von 0 bis 9 gehen wuerde
		int customerRndm = (int) (Math.random() * 10 + 1);
		int prductRndm = (int) (Math.random() * 10 + 1);

		// System.err.println("CUSTOMERrndm "+ customerRndm);
		// System.err.println("PRODUCTrndm "+ prductRndm);

		Customer customerCache = null;
		Product productCache = null;

		// Den zufaellig gewaehlten Customer mit Hilfe der ID zuordnen
		for (Customer customer : customerListe) {
			if (customer.getId() == customerRndm) {
				customerCache = customer;
			}
		}
		// Das zufaellig gewaehlte product mit Hilfe der ID zuordnen
		for (Product product : produktListe) {
			if (product.getId() == prductRndm) {
				productCache = product;
			}
		}

		// sychro Methode zum Zaehlen der Bestellvorgaenge
		currentBuffer.counter();
		Order order = new Order(customerCache, productCache);

		System.err
				.println("\nBestellungsnummer: " + currentBuffer.getCounter()
						+"\nKunde: " + order.toString()
						+ " im Warenkorb.\n----------------------------------------------"+
						"\n");
		return order;
	}

	*/

	/**
	 * Fuegt die Customer und Produkte (aus dem Webshop) in die Arraylist ein.
	 */

    /*
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
	*/

	/**
	 * Gib einen Zugriffswunsch auf der Konsole aus
	 */
	public void statusmeldungZugriffswunsch() {

		System.err.println(">>>> " +this.getName() + " moechte auf den Puffer zugreifen!");
	}

	/**
	 * schlaeft fuer die angebene Zeit.
	 */
	public void pause() {
		// int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
		try {
			// haelt den Thread fuer 500ms an (blockieren)
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Erneutes Setzen des Interrupt-Flags fuer den eigenen Thread
			// noetig, da Catch-Aufruft das Flag auf false zuruecksetzt
			this.interrupt();
		}
	}
}
