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
