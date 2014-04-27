package aufgabe3.bufferServer;

import java.util.*;

/**
 * Datenpuffer für Elemente vom Typ E mit Zugriffsmethoden enter und remove.
 * Stellt einen generischen Datenpuffer mit Zugriffsmethoden zur Verfügung
 */
public class BoundedBuffer<E> {

    /**
     * Maximale Puffergröße
     */
    private int bufferMaxSize;

    /**
     * Liste als Speicher
     */
    private LinkedList<E> buffer;

    /**
     * Konstruktor
     */
    public BoundedBuffer(int bufferSize) {
        bufferMaxSize = bufferSize;
        buffer = new LinkedList<E>();
    }

    /**
     * Producer (Erzeuger) rufen die Methode enter() auf
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
        item = buffer.removeFirst();
        System.err
                .println("          REMOVE: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt aus dem Puffer entnommen. Aktuelle Puffergroesse�ße: "
                        + buffer.size());
        this.notifyAll();
        return item;
    }
}
