package aufgabe3.bufferServerWebShop;

import java.util.*;

/**
 * Code der Erzeuger-Threads fuer ein Erzeuger/Verbrauchersystem
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
     * Konstruktor mit Uebergabe des Puffers
     * */
    public Producer(BoundedBuffer<Date> buffer) {
        currentBuffer = buffer;
    }

    /**
     * Erzeuge Date-Objekte und lege sie in den Puffer. Halte nach jeder Ablage
     * fuer eine Zufallszeit an.
     */
    public void run() {

        while (!isInterrupted()) {
            /**
             * Date-Objekt erzeugen
             **/
            item = new Date();
            statusmeldungZugriffswunsch();

            // Puffer-Zugriffsmethode aufrufen --> Synchronisation ueber den
            // Puffer!
            currentBuffer.enter(item);

            if (!isInterrupted()) {
                // Fuer unbestimmte Zeit anhalten
                pause();
            }
        }
    }

    /**
     * Gib einen Zugriffswunsch auf der Konsole aus
     */
    public void statusmeldungZugriffswunsch() {

        System.err.println("                                           "
                + this.getName() + " moechte auf den Puffer zugreifen!");
    }

    /*
     * Erzeuger benutzen diese Methode, um fuer eine Zufallszeit untaetig zu sein
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
