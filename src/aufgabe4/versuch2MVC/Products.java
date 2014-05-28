package aufgabe4.versuch2MVC;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by lnerlich on 12.05.14.
 */
public class Products {

    private StringProperty name;
    private IntegerProperty price;

    private static ObservableList<Products> products = FXCollections
            .<Products> observableArrayList();

    public Products(String Name, int price) {
        setName(Name);
        setPrice(price);
    }

    public final void setName(String value) {
        NameProperty().set(value);
    }

    public StringProperty NameProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public final void setPrice(int value) {
        priceProperty().set(value);
    }

    public IntegerProperty priceProperty() {
        if (price == null) {
            price = new SimpleIntegerProperty();
        }
        return price;
    }

    public final String getName() {
        return NameProperty().get();
    }
    public final int getPrice() {
        return priceProperty().get();
    }

    public static ObservableList<Products> getProducts() {
        products.add(new Products("Haus", 20000));
        products.add(new Products("Fussball", 25));
        return products;
    }

    public static void addProduct(String name, int price){
        products.add(new Products(name, price));
    }

    @Override
    public String toString() {
        return "Products{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
