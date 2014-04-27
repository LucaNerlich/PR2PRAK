package aufgabe3.bufferServerWebShop;

import java.util.*;

/**
 * Code der Verbraucher-Threads für ein Erzeuger/Verbrauchersystem
 * 
 * @author Philipp Jenke
 * 
 */
public class Consumer extends Thread {

    /**
     * Max. Pause zwischen den Pufferzugriffen in ms
     */
    public final int MAX_IDLE_TIME = 100;

    private BoundedBuffer<Order> currentBuffer;
    private Order item;

    /**
     * Konstruktor mit Uebergabe des Puffers
     */
    public Consumer(BoundedBuffer<Order> buffer) {
        currentBuffer = buffer;
    }

    /**
     * Entnimm ein Date-Objekt aus dem Puffer. Nach jeder Entnahme fuer eine
     * Zufallszeit anhalten.
     */
    public void run() {

        while (!isInterrupted()) {
            statusmeldungZugriffswunsch();
            // Date-Objekt dem Puffer entnehmen, dazu Puffer-Zugriffsmethode
            // aufrufen --> Synchronisation ueber den Puffer!
            // Hier sollte dann etwas mit dem item-Objekt getan werden ...
            item = currentBuffer.remove();
            System.out.println("Item removed: " + item);

            if (!isInterrupted()) {
                // Fuer unbestimmte Zeit anhalten
                pause();
            }
        }
    }

    /**
     * Gib einen Zugriffswunsch auf der Konsole aus.
     */
    public void statusmeldungZugriffswunsch() {

        System.err.println("                                           "
                + this.getName() + " moechte auf den Puffer zugreifen!");
    }

    /**
     * Verbraucher benutzen diese Methode, um fuer eine Zufallszeit untaetig zu
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
