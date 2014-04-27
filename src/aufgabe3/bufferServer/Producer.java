package aufgabe3.bufferServer;

import java.util.*;

/**
 * Code der Erzeuger-Threads für ein Erzeuger/Verbrauchersystem
 * 
 * @author Philipp Jenke
 */
public class Producer extends Thread {
    /**
     * Max. Pause zwischen den Pufferzugriffen in ms
     */
    public final int MAX_IDLE_TIME = 100;

    private BoundedBuffer<Date> currentBuffer;
    private Date item;

    /**
     * Konstruktor mit Übergabe des Puffers
     * */
    public Producer(BoundedBuffer<Date> buffer) {
        currentBuffer = buffer;
    }

    /**
     * Erzeuge Date-Objekte und lege sie in den Puffer. Halte nach jeder Ablage
     * für eine Zufallszeit an.
     */
    public void run() {

        while (!isInterrupted()) {
            /**
             * Date-Objekt erzeugen
             **/
            item = new Date();
            statusmeldungZugriffswunsch();

            // Puffer-Zugriffsmethode aufrufen --> Synchronisation über den
            // Puffer!
            currentBuffer.enter(item);

            if (!isInterrupted()) {
                // Für unbestimmte Zeit anhalten
                pause();
            }
        }
    }

    /**
     * Gib einen Zugriffswunsch auf der Konsole aus
     */
    public void statusmeldungZugriffswunsch() {

        System.err.println("                                           "
                + this.getName() + " möchte auf den Puffer zugreifen!");
    }

    /*
     * Erzeuger benutzen diese Methode, um für eine Zufallszeit untätig zu sein
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
