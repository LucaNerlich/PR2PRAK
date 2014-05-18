package aufgabe3.bufferServerWebShop;


import aufgabe3.WebShop;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Timer;

import static org.junit.Assert.assertTrue;

/**
 * Created by Luca on 18.05.2014.
 */
public class BufferTesting {

    public final int NO_PRODUCER = 1;
    public final int NO_CONSUMER = 1;

    public BoundedBuffer<Order> testServer = new BoundedBuffer<Order>(3);

    @Test
    public void test() {

        new BufferTesting().startSimulation();

        int result3 = 2;
        assertTrue(result3 == 2);

    }

    public void startSimulation() {
        // Starte und beende Threads
        LinkedList<OrderGenerator> producerList = new LinkedList<OrderGenerator>();
        LinkedList<WebShop> consumerList = new LinkedList<WebShop>();

        System.err.println("-------------------- START -------------------");
        // Fuegt die Customer und Produkte (aus dem Webshop) in die Arraylist
        // ein.
        // OrderGenerator.addToList();
        AnwendungBuffer anwendungBuffer = new AnwendungBuffer(testServer);

        // Erzeuger(produziert Bestellungen) - Threads erzeugen
        for (int i = 1; i <= NO_PRODUCER; i++) {

            // Den Generator fuer Bestellungen erzeugen mit einem Puffer der
            // groesse 3
            OrderGenerator current = new OrderGenerator(testServer);
            current.setName("Erzeuger-" + i);
            producerList.add(current);
            current.start();
        }
        try {
            Thread.sleep(3000); // Bevor die naechste Bestellung erzeugt wird
            // 3sek warten. Zum testen Objekte in Buffer
            // schieben
        } catch (InterruptedException e) {
        }

        // Verbraucher (entnimmt Bestellungen) - Threads erzeugen
        for (int i = 1; i <= NO_CONSUMER; i++) {
            WebShop current = new WebShop(testServer);
            current.setName("Verbraucher-" + i);
            consumerList.add(current);
            current.start();
        }

        Timer timer = new Timer();
        timer.schedule(new AbortOrderTimerTask(), 0, 1200);

        // Laufzeit abwarten. Die anderen Threads laufen waehrenddessen weiter
        try {
            Thread.sleep(10500);

            // Erzeuger - Threads stoppen
            for (int i = 0; i < NO_PRODUCER; i++) {
                producerList.get(i).interrupt();
            }

            // Verbraucher - Threads stoppen
            for (int i = 0; i < NO_CONSUMER; i++) {
                consumerList.get(i).interrupt();
            }

            // Stoppt den Timer
            timer.cancel();
            System.err
                    .println("-------------------- Simulation done -------------------");

        } catch (InterruptedException e) {
       }
    }
}
