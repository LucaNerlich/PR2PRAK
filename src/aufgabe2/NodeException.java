package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * NodeException.java
 */

/**
 * Exceptionsklasse fuer Fehler innerhalb des Binaerbaums
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 * 
 */
@SuppressWarnings("serial")
public class NodeException extends Exception {

	NodeException() {
		super();
	}

	NodeException(String message) {
		super(message);
	}

}
