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
 * Erzeugt eine Simulationsumgebung fuer ein Erzeuger/Verbrauchersystem
 * 
 * @author Philipp Jenke angepasst und erweitert von Luca Nerlich & Daniel
 *         Sommerlig
 * 
 */

public class BoundedBufferServer {

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

		try {
			System.err
					.println("-------------------- START -------------------");
			// Fuegt die Customer und Produkte (aus dem Webshop) in die
			// Arraylist
			// ein.
			AnwendungBuffer anwendungBuffer = new AnwendungBuffer(server);

			/**
			 * Erzeuger(produziert Bestellungen) - Threads erzeugen
			 */

			OrderGenerator currentProd = new OrderGenerator(server);
			currentProd.setName("Erzeuger-1");

			currentProd.start();

			Thread.sleep(3000); // Bevor die naechste Bestellung erzeugt wird
								// 3sek warten. Zum testen Objekte in Buffer
								// schieben

			/*
			 * Verbraucher (entnimmt Bestellungen) - Threads erzeugen
			 */

			WebShop currentCons = new WebShop(server);
			currentCons.setName("Verbraucher-1");

			currentCons.start();

			/**
			 * Timer - stellt die "abgebrochenen" Bestellungen da. Entnimmt
			 * ebenfalls aus dem Buffer, bricht im Intervall von 1,2sek die
			 * Bestellung ab
			 */
			Timer timer = new Timer();
			timer.schedule(new AbortOrderTimerTask(), 0, 1200);

			// Laufzeit abwarten. Die anderen Threads laufen waehrenddessen
			// weiter

			Thread.sleep(10500);

			// Erzeuger - Threads stoppen
			currentProd.interrupt();

			// Verbraucher - Threads stoppen

			currentCons.interrupt();

			// Stoppt den Timer
			timer.cancel();
			System.err
					.println("-------------------- Simulation done -------------------");

		} catch (InterruptedException e) {
		}
	}
}
