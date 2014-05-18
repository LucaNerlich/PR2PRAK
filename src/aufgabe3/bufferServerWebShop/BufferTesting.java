/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * BufferTesting.java
 */
package aufgabe3.bufferServerWebShop;

import aufgabe3.WebShop;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Timer;

import static org.junit.Assert.assertTrue;

/**
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */
public class BufferTesting {

	public BoundedBuffer<Order> testServer = new BoundedBuffer<Order>(3);

	@Test
	public void test() {

		new BufferTesting().startSimulation();

	}

	public void startSimulation() {
		// Starte und beende Threads

		System.err.println("-------------------- START -------------------");
		// Fuegt die Customer und Produkte (aus dem Webshop) in die Arraylist
		// ein.
		// OrderGenerator.addToList();
		AnwendungBuffer anwendungBuffer = new AnwendungBuffer(testServer);

		// Erzeuger(produziert Bestellungen) - Threads erzeugen

		// Den Generator fuer Bestellungen erzeugen mit einem Puffer der
		// groesse 3
		OrderGenerator current = new OrderGenerator(testServer);
		current.setName("Erzeuger");

		current.start();

		try {
			Thread.sleep(3000); // Bevor die naechste Bestellung erzeugt wird
			// 3sek warten. Zum testen Objekte in Buffer
			// schieben - damit ein paar in den buffer geladen werden
		} catch (InterruptedException e) {
		}

		// Verbraucher (entnimmt Bestellungen) - Threads erzeugen

		WebShop current2 = new WebShop(testServer);
		current2.setName("Verbraucher");

		current2.start();

		Timer timer = new Timer();
		timer.schedule(new AbortOrderTimerTask(), 0, 1200);

		// TESTING
		boolean erzeuger = current.isAlive();
		boolean verbraucher = current2.isAlive();

		assertTrue(erzeuger);
		assertTrue(verbraucher);
		
		// TESTING ENDE

		// Laufzeit abwarten. Die anderen Threads laufen waehrenddessen weiter
		try {
			Thread.sleep(10500);
			
			
			// COUNTER TEST
			int result = testServer.getCounter();
			System.err.println(result);
			assertTrue(result == 10);

			// Erzeuger - Threads stoppen
			current.interrupt();			

			// Verbraucher - Threads stoppen
			current2.interrupt();	
			
			//Stelle sicher, dass Erzeuger und Verbraucher fertig sind
			current.join();
			current2.join();

			//Abfrage ob "alive"
			erzeuger = current.isAlive();
			verbraucher = current2.isAlive();

			assertTrue(!erzeuger);
			assertTrue(!verbraucher);
			
			// Stoppt den Timer
			timer.cancel();
			
			// Checkt ob der Timer durchgelaufen ist
			assertTrue(AbortOrderTimerTask.isDone);
			
			System.err
					.println("-------------------- Simulation done -------------------");

		} catch (InterruptedException e) {
		}		
	}
}
