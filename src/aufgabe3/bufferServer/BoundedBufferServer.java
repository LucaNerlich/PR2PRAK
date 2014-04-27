package aufgabe3.bufferServer;

import java.util.*;

/**
 * Erzeugt eine Simulationsumgebung für ein Erzeuger/Verbrauchersystem
 * 
 * @author Philipp Jenke
 * 
 */
public class BoundedBufferServer {
    /**
     * Anzahl Erzeuger-Threads
     */
    public final int NO_PRODUCER = 3;

    /**
     * Anzahl Verbraucher-Threads
     */
    public final int NO_CONSUMER = 2;

    /**
     * Das Puffer-Objekt mit Elementtyp Date und vorgegebener Platzanzahl
     * (Größe)
     */
    public BoundedBuffer<Date> server = new BoundedBuffer<Date>(2);

    /**
     * Programeinstieg
     */
    public static void main(String[] args) {

        new BoundedBufferServer().startSimulation();
    }

    /**
     * Starte Simulation
     */
    public void startSimulation() {
        // Starte und beende Threads
        LinkedList<Producer> producerList = new LinkedList<Producer>();
        LinkedList<Consumer> consumerList = new LinkedList<Consumer>();

        System.err.println("-------------------- START -------------------");

        // Verbraucher - Threads erzeugen
        for (int i = 1; i <= NO_CONSUMER; i++) {
            Consumer current = new Consumer(server);
            current.setName("Verbraucher-" + i);
            consumerList.add(current);
            current.start();
        }

        // Erzeuger - Threads erzeugen
        for (int i = 1; i <= NO_PRODUCER; i++) {
            Producer current = new Producer(server);
            current.setName("Erzeuger-" + i);
            producerList.add(current);
            current.start();
        }

        // Laufzeit abwarten
        try {
            Thread.sleep(1000);

            System.err.println("-------------------- ENDE -------------------");

            // Verbraucher - Threads stoppen
            for (int i = 0; i < NO_CONSUMER; i++) {
                consumerList.get(i).interrupt();
            }

            // Erzeuger - Threads stoppen
            for (int i = 0; i < NO_PRODUCER; i++) {
                producerList.get(i).interrupt();
            }
        } catch (InterruptedException e) {
        }

    }
}
