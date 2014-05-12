/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * BoundedBufferServer.java
 */

package aufgabe3.bufferServerWebShop;

import aufgabe3.WebShop;

import java.util.LinkedList;
import java.util.Timer;

/**
 * Erzeugt eine Simulationsumgebung für ein Erzeuger/Verbrauchersystem
 * 
 * @author Philipp Jenke angepasst und erweitert von Luca Nerlich & Daniel Sommerlig
 * 
 */
public class BoundedBufferServer {
	/**
	 * Anzahl Erzeuger-Threads
	 */
	public final int NO_PRODUCER = 1;

	/**
	 * Anzahl Verbraucher-Threads
	 */
	public final int NO_CONSUMER = 1;

	/**
	 * Das Puffer-Objekt mit Elementtyp Order und vorgegebener Platzanzahl
	 * (Groesse)
	 */
	public BoundedBuffer<Order> server = new BoundedBuffer<Order>(3);

	/**
	 * Programeinstieg
	 */
	public static void main(String[] args) {

		new BoundedBufferServer().startSimulation();
	}

	/**
	 * Startet die Simulation
	 */
	public void startSimulation() {
		// Starte und beende Threads
		LinkedList<OrderGenerator> producerList = new LinkedList<OrderGenerator>();
		LinkedList<WebShop> consumerList = new LinkedList<WebShop>();

		System.err.println("-------------------- START -------------------");
		OrderGenerator.addToList();
		
		// Erzeuger(produziert Bestellungen) - Threads erzeugen
		for (int i = 1; i <= NO_PRODUCER; i++) {			
			OrderGenerator current = new OrderGenerator(server);
			current.setName("Erzeuger-" + i);
			producerList.add(current);
			current.start();
		}

		try {
			Thread.sleep(3000); // Zum testen Objekte in Buffer schieben
		} catch (InterruptedException e) {
		}
		// Verbraucher (entnimmt Bestellungen) - Threads erzeugen
		for (int i = 1; i <= NO_CONSUMER; i++) {
			WebShop current = new WebShop(server);
			current.setName("Verbraucher-" + i);
			consumerList.add(current);
			current.start();
		}

        /**
         * Timer - stellt die "abgebrochenen" Bestellungen da.
         * Entnimmt ebenfalls aus dem Buffer
         */
		Timer timer = new Timer();
		timer.schedule(new AbortOrderTimerTask(), 0, 1200);

		// Laufzeit abwarten. Die anderen Threads laufen waehrenddessen weiter
		try {
			Thread.sleep(10500);

			// Erzeuger - Threads stoppen
			for (int i = 0; i < NO_PRODUCER; i++) {
				producerList.get(i).interrupt();
			}

			// Verbraucher - Threads stoppen
			for (int i = 0; i < NO_CONSUMER; i++) {
				consumerList.get(i).interrupt();
			}

            //Stoppt den Timer
			timer.cancel();
			System.err.println("Simulation done");

		} catch (InterruptedException e) {
		}
	}
}
