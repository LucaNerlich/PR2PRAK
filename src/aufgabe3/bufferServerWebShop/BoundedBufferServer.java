package aufgabe3.bufferServerWebShop;

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
    public final int NO_PRODUCER = 5;

    /**
     * Anzahl Verbraucher-Threads
     */
    public final int NO_CONSUMER = 5;

    /**
     * Das Puffer-Objekt mit Elementtyp Date und vorgegebener Platzanzahl
     * (Groesse)
     */
    public BoundedBuffer<Order> server = new BoundedBuffer<Order>(5);

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
        LinkedList<OrderGenerator> producerList = new LinkedList<OrderGenerator>();
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
        	OrderGenerator current = new OrderGenerator(server);
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
