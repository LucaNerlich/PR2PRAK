/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * BoundedBuffer.java
 */
package aufgabe3.bufferServerWebShop;

import java.util.*;

/**
 * Datenpuffer fuer Elemente vom Typ E mit Zugriffsmethoden enter und remove.
 * Stellt einen generischen Datenpuffer mit Zugriffsmethoden zur Verfuegung
 */
public class BoundedBuffer<E> {

	/**
	 * Maximale Puffergröße
	 */
	private int bufferMaxSize;
	private int counter;

	/**
	 * Liste als Speicher
	 */
	private LinkedList<E> buffer;

	/**
	 * Konstruktor
     * Setzt die Groesse des Buffers
	 */
	public BoundedBuffer(int bufferSize) {
		bufferMaxSize = bufferSize;
		buffer = new LinkedList<E>();
	}

	/**
	 * Producer (Erzeuger) rufen die Methode enter() auf
     * Diese legt das item in den Puffer mit der add() Methode
	 **/
	public synchronized void enter(E item) {
		while (buffer.size() >= bufferMaxSize) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
        //Mehtode von LinkedList
		buffer.add(item);
		System.err
				.println("          ENTER: "
						+ Thread.currentThread().getName()
						+ " hat ein Objekt in den Puffer gelegt. Aktuelle Puffergroesse: "
						+ buffer.size());
		this.notifyAll();
	}

	/**
	 * Consumer (Verbraucher) rufen die Methode REMOVE auf
     * Entfernt das erste Element der Liste / Buffer
	 **/
	public synchronized E remove() {
		E item;
		while (buffer.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
				return null;
			}
		}
        //Methode von LinkedList
		item = buffer.removeFirst();
		System.err
				.println("          REMOVE: "
						+ Thread.currentThread().getName()
						+ " hat ein Objekt aus dem Puffer entnommen. Aktuelle Puffergroesse: "
						+ buffer.size());
        //informiert alle wartenden Threads
		this.notifyAll();
		return item;
	}

	public synchronized void counter() {
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}
