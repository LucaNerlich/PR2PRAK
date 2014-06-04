/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * Customers.java
 */

package aufgabe4.versuch2MVC;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Observable;

/**
 * @author Philipp Jenke angepasst und erweitert von Luca Nerlich & Daniel
 *         Sommerlig
 */
public class Customers {

	private StringProperty vName;
	private StringProperty nName;
	private float counter = 0.0f;

    /**
     * Die Liste wird in Java FXCollections verwaltet, sie konserviert die
     * Property-Eigenschaften. Property stellen Ver�nderungen dar.
     */
    private static ObservableList<Customers> customers = FXCollections
            .<Customers> observableArrayList();

    public Customers(String vName, String nName) {
        setVName(vName);
        setNName(nName);
    }

    public final void setVName(String value) {
        vNameProperty().set(value);
    }

    public StringProperty vNameProperty() {
        if (vName == null) {
            vName = new SimpleStringProperty();
        }
        return vName;
    }

    public final void setNName(String value) {
        nNameProperty().set(value);
    }

    @Override
    public String toString() {
        return "Customers{" + "vName=" + vName + ", nName=" + nName + '}';
    }

    public StringProperty nNameProperty() {
        if (nName == null) {
            nName = new SimpleStringProperty();
        }
        return nName;
    }

    public final String getVName() {
        return vNameProperty().get();
    }

    public final String getNName() {
        return nNameProperty().get();
    }

    /**
     * Liefert die Oserverableliste customer
     */
    public static ObservableList<Customers> getCustomers() {
        // customers.add(new Customers("Luca", "Nerlich"));
        // customers.add(new Customers("Daniel", "Sommerlig"));
        return customers;
    }

    public static void addCustomer(String vName, String nName) {
        customers.add(new Customers(vName, nName));
    }
}
