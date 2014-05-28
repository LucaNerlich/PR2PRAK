package aufgabe4.versuch2MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Luca on 28.05.2014.
 */
public class Controller {

    //hier psvm dann guianwendung starten
    // auch nen controller object erstellen auch in psvm

    public static TableView<Customers> customerTableView = new TableView();
    public static TableView<Products> productsTableView = new TableView();
    private ObservableList customerList;
    private  ObservableList productList;

    //in konst guianwendung + "starte alles"

    private static Model model;
    private GuiView view;

    public Controller(Model model){
      //  GuiAnwendung guiAnwendung = new GuiAnwendung();
        //jetzt alle items initialisieren

    this.model = model;
    this.view = new GuiView();
        initCustomTable();
        initProduktTable();

        this.customerList = Customers.getCustomers();
        this.productList = Products.getProducts();
    }

    public void show(){
        view.show(model.getPrimaryStage());
    }

    public static MenuBar createMenu() {
       return model.createMenu();
    }

    private void initCustomTable() {
        customerTableView.setPrefWidth(350);
        customerTableView.setPrefHeight(300);
        customerTableView.setItems(Customers.getCustomers());

        // Setup the first column: vName
        // TableColumn<Customers, String> vNameCol = new TableColumn<>("vName");  // intellij kann keine <>
        TableColumn<Customers, String> vNameCol = new TableColumn("Vorname");
        vNameCol.setEditable(true);
        vNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>(
                "vName"));
        vNameCol.setPrefWidth(customerTableView.getPrefWidth() / 2);

        // Setup the second column: nName
        // TableColumn<Customers, String> nNameCol = new TableColumn<>("nName"); // intellij kann keine <>
        TableColumn<Customers, String> nNameCol = new TableColumn("Nachname");
        nNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>(
                "nName"));
        nNameCol.setPrefWidth(customerTableView.getPrefWidth() / 2);

        // Tabelle mit Spalten befuellen
        customerTableView.getColumns().add(vNameCol);
        customerTableView.getColumns().add(nNameCol);

        //LISTEN FOR CHANGES (eintrag markiert etc.)

        customerTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customers>() {
            @Override
            public void changed(ObservableValue<? extends Customers> observableValue, Customers customers, Customers customers2) {
            }
        });
    }

    private void initProduktTable() {
        productsTableView.setPrefWidth(350);
        productsTableView.setPrefHeight(300);
        productsTableView.setItems(Products.getProducts());

        // Setup the first column: Name
        // 	TableColumn<Products, String> NameCol = new TableColumn<>("Name"); // intellij kann keine <>
        TableColumn<Products, String> NameCol = new TableColumn("Name");
        NameCol.setEditable(true);
        NameCol.setCellValueFactory(new PropertyValueFactory<Products, String>(
                "Name"));
        NameCol.setPrefWidth(productsTableView.getPrefWidth() / 2);

        // Setup the second colum: Price
        // TableColumn<Products, String> priceCol = new TableColumn<>("price");
        TableColumn<Products, String> priceCol = new TableColumn("Price");
        priceCol
                .setCellValueFactory(new PropertyValueFactory<Products, String>(
                        "Price"));
        priceCol.setPrefWidth(productsTableView.getPrefWidth() / 2);

        //  Tabelle mit Spalten befuellen
        productsTableView.getColumns().add(NameCol);
        productsTableView.getColumns().add(priceCol);

        //LISTEN FOR CHANGES (eintrag markiert etc.)

        productsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Products>() {
            @Override
            public void changed(ObservableValue<? extends Products> observableValue, Products products, Products products2) { }  });
    }
}
