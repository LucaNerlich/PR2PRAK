package aufgabe4.versuch2MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Created by Luca on 28.05.2014.
 */
public class Controller implements ConInt{

    public static TableView<Customers> customerTableView = new TableView();
    public static TableView<Products> productsTableView = new TableView();
    private static Model model;
    private GuiView view;

    public Controller(Model model){

    //jetzt alle items initialisieren
    this.model = model;
    this.view = new GuiView(this);
        initCustomTable();
        initProduktTable();
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

    @Override
      public void onAddCustomer() {
        Stage stage = new Stage();
        String vorname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Vornamen ein:", "VORNAME", "");
        String nachname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Nachname ein:", "NACHNAME", "");
        if (!vorname.equals("") && !nachname.equals("")) {
            try {
                int vInt = Integer.parseInt(vorname);
                int nInt = Integer.parseInt(nachname);
            }
            //exception wird geworfen, wenn der input string ist.
            catch (NumberFormatException e) {
                //TODO abfangen von ints
                Customers.addCustomer(vorname, nachname);
            }
        }
    }

    @Override
    public void onAddProduct() {
        //Per input zahl als string holen, dann string in int parsen und exception abfangen
        try {
            Stage stage = new Stage();
            String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
            String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
            if (!item.equals("") && !preis.equals("")) {
                try {
                    int preisInt = Integer.parseInt(preis);
                    Products.addProduct(item, preisInt);
                } catch (NumberFormatException e) {
                    System.out.println("Bitte geben sie einen Integer Wert ein!");
                }
            }
        }
        catch(Exception e){
        }
    }

    @Override
    public void onRemoveCustomer() {
        int selectedIndex = Controller.customerTableView.getSelectionModel().getSelectedIndex();
        try {
            Controller.customerTableView.getItems().remove(selectedIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            Stage stage = new Stage();
            Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
        }
    }

    @Override
    public void onRemoveProduct() {
        int selectedIndex = Controller.productsTableView.getSelectionModel().getSelectedIndex();
        try {
            Controller.productsTableView.getItems().remove(selectedIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            Stage stage = new Stage();
            Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
        }
    }
}
