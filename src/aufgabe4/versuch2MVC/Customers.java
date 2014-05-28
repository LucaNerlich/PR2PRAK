package aufgabe4.versuch2MVC;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by lnerlich on 12.05.14.
 */

public class Customers {

    private StringProperty vName;
    private StringProperty nName;

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
        return "Customers{" +
                "vName=" + vName +
                ", nName=" + nName +
                '}';
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

    public static ObservableList<Customers> getCustomers() {
        customers.add(new Customers("Luca", "Nerlich"));
        customers.add(new Customers("Daniel", "Sommerlig"));
        return customers;
    }

    public static void addCustomer(String vName, String nName){
        customers.add(new Customers(vName, nName));
    }

}
