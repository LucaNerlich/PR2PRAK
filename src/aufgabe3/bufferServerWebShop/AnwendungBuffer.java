/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * AnwendungBuffer.java
 */

package aufgabe3.bufferServerWebShop;

import aufgabe3.Customer;
import aufgabe3.Product;
import java.util.ArrayList;

/**
 * Stellt die Objekte fuer "Order" bereit.
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */
public class AnwendungBuffer {

    static ArrayList<Customer> customerListe = new ArrayList<Customer>();
    static ArrayList<Product> produktListe = new ArrayList<Product>();

    private static BoundedBuffer<Order> currentBuffer;

    public AnwendungBuffer(BoundedBuffer<Order> buffer){
        currentBuffer = buffer;
        addToList();
    }

    /**
     * Helfermethode zur zufaelligen Generierung einer Bestellung
     *
     * @return order Objekt
     */
    public static Order getContent() {
        // +1 da er nach einem Kommawert nur von 0 bis 9 gehen wuerde
        int customerRndm = (int) (Math.random() * 10 + 1);
        int prductRndm = (int) (Math.random() * 10 + 1);

        // System.err.println("CUSTOMERrndm "+ customerRndm);
        // System.err.println("PRODUCTrndm "+ prductRndm);

        Customer customerCache = null;
        Product productCache = null;

        // Den zufaellig gewaehlten Customer mit Hilfe der ID zuordnen
        for (Customer customer : customerListe) {
            if (customer.getId() == customerRndm) {
                customerCache = customer;
            }
        }
        // Das zufaellig gewaehlte product mit Hilfe der ID zuordnen
        for (Product product : produktListe) {
            if (product.getId() == prductRndm) {
                productCache = product;
            }
        }

        Order order = new Order(customerCache, productCache);
        return order;
    }

    /**
     * Initialisiert die Customer und Produkte fuer unsere Simulation
     */
    private static void addToList() {
        customerListe.add(new Customer("vAnton", "nA"));
        customerListe.add(new Customer("vBjoern", "nB"));
        customerListe.add(new Customer("vClaus", "nC"));
        customerListe.add(new Customer("vDetlef", "nD"));
        customerListe.add(new Customer("vErwin", "nE"));
        customerListe.add(new Customer("vFriedrich", "nF"));
        customerListe.add(new Customer("vGregor", "nG"));
        customerListe.add(new Customer("vHubert", "nH"));
        customerListe.add(new Customer("vIver", "nI"));
        customerListe.add(new Customer("vJoern", "nJ"));

        produktListe.add(new Product("Seife", 1.2));
        produktListe.add(new Product("Auto", 42000));
        produktListe.add(new Product("Computer", 1250));
        produktListe.add(new Product("Stueck Kaese", 5.99));
        produktListe.add(new Product("Wurst", 3.99));
        produktListe.add(new Product("Huhn", 12.49));
        produktListe.add(new Product("Salat", 1.29));
        produktListe.add(new Product("Broetchen", 3.25));
        produktListe.add(new Product("Sixpack Bier", 7.99));
        produktListe.add(new Product("Smartphone", 299.99));
    }


}
