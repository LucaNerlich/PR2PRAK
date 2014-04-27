package aufgabe3.bufferServer;

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

    private BoundedBuffer<Date> currentBuffer;
    private Date item;

    /**
     * Konstruktor mit Übergabe des Puffers
     */
    public Consumer(BoundedBuffer<Date> buffer) {
        currentBuffer = buffer;
    }

    /**
     * Entnimm ein Date-Objekt aus dem Puffer. Nach jeder Entnahme für eine
     * Zufallszeit anhalten.
     */
    public void run() {

        while (!isInterrupted()) {
            statusmeldungZugriffswunsch();
            // Date-Objekt dem Puffer entnehmen, dazu Puffer-Zugriffsmethode
            // aufrufen --> Synchronisation über den Puffer!
            // Hier sollte dann etwas mit dem item-Objekt getan werden ...
            item = currentBuffer.remove();
            System.out.println("Item removed: " + item);

            if (!isInterrupted()) {
                // Für unbestimmte Zeit anhalten
                pause();
            }
        }
    }

    /**
     * Gib einen Zugriffswunsch auf der Konsole aus.
     */
    public void statusmeldungZugriffswunsch() {

        System.err.println("                                           "
                + this.getName() + " möchte auf den Puffer zugreifen!");
    }

    /**
     * Verbraucher benutzen diese Methode, um für eine Zufallszeit untätig zu
     * sein
     */
    public void pause() {
        int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
        try {
            // Thread blockieren
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            // Erneutes Setzen des Interrupt-Flags für den eigenen Thread
            this.interrupt();
        }
    }
}
