/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * AbortOrderTimerTask.java
 */
package aufgabe3.bufferServerWebShop;

import aufgabe3.WebShop;

import java.util.TimerTask;

/**
 * Bildet die Grundlage des Timers. Wird regelmaessig durch den timer
 * aufgerufen.
 */
public class AbortOrderTimerTask extends TimerTask {

	/**
	 * Agiert wie die Anderen Verbraucher / Consumer. Entnimmt das erste Objekt
	 * des Buffers.
	 */

	public static boolean isDone = false;
	
	@Override
	public void run() {
		WebShop.item = WebShop.currentBuffer.remove();
		System.err.println("\n### Achtung ###\n"
				+ "Benutzer hat die Bestellung abgebrochen! \n");
		isDone = true;
	}
}
