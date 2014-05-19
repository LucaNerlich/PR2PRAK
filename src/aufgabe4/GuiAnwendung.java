package aufgabe4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class GuiAnwendung extends Application {

	public static void main(String[] args) {
		try {

			launch(args);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// erstellt das Fenster mit Titel
		ContentBuilder contBuilder = new ContentBuilder();
		primaryStage.setTitle("Aufgabe4 - JavaFX");

        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(borderPane);
        GridPane gridpane = new GridPane();
        VBox vBoxLeft = new VBox(8);
        VBox vBoxRight = new VBox(8);

        borderPane.setRight(vBoxRight);
        borderPane.setLeft(vBoxLeft);
        borderPane.setTop(createMenu());
        borderPane.setCenter(gridpane);


        // init Gridpane

		gridpane.setPadding(new Insets(5)); // rand aussen
		gridpane.setHgap(10);
		gridpane.setVgap(10);

        Text bgTitle = new Text("Aufgabe 4 - JavaFX");
        bgTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridpane.add(bgTitle, 0, 0, 2, 1);

		ToggleButton tBtn1 = contBuilder.createToggleButton("Toggle Button");
		ComboBox comboBox1 = contBuilder.createComboBox("Luca", "Fabian",
				"Laura", "Daniel");
		CheckBox ckBox1 = contBuilder.createCheckBox("el babo");
		TextField tField = contBuilder.createTextField("");
		Button btn = contBuilder.createButton("ADD PERSON");
        btn.setOnAction(new ButtonWithEventHandler());

       final Button btn2 = contBuilder.createButton("Button2");
       btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello from: " + actionEvent.getSource());
                System.out.println(" - ID: " + btn2.getId());
            }
        });

        final Button closeButton = contBuilder.createButton("CLOSE");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        borderPane.setBottom(closeButton);

		// erstellt die Flaeche des Fensters
		// StackPane root = new StackPane();
		// Group root = new Group();
        // Scene scene = new Scene(new VBox(8), 600, 400);

        // ((VBox) scene.getRoot()).getChildren().addAll(createMenu());
        // ((VBox) scene.getRoot()).getChildren().addAll(gridpane);

		Label label = new Label("Person_Table");
        Label label2 = new Label("VBOX 8 LEFT");
        Label label3 = new Label("VBOX 8 RIGHT");

        vBoxLeft.getChildren().add(label2);
        vBoxRight.getChildren().add(label3);

        label.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, new MouseEventHandler());
	// 	GridPane.setHalignment(label, HPos.CENTER);

        // Objekte an die Gridpane haengen
		// root.getChildren().add(gridpane);
		// fuege Button zur Gridpane hinzu
		// gridpane.add(tBtn1, 0, 3);
		// gridpane.add(comboBox1, 0, 2);
		// gridpane.add(ckBox1, 1, 1);
		// gridpane.add(tField, 2, 0);
        Label customersLabel = new Label("Customers");
        Label productsLabel = new Label("Products");

        gridpane.add(customersLabel,1, 1);
        gridpane.add(productsLabel,2, 1);

		 gridpane.add(btn, 2, 2);
        // gridpane.add(btn2, 2, 2);
	    // root.getChildren().add(createMenu());
		 // gridpane.add(createMenu(), 0, 0);
         gridpane.add(label, 1, 2);

        Button addCustomer = contBuilder.createButton("Add customer");
        gridpane.add(addCustomer, 1, 3);

        addCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               Stage stage = new Stage();
                String vorname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Vornamen ein:", "VORNAME", "");
                String nachname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Nachname ein:", "NACHNAME", "");
                 try{
                    int vornameInt = Integer.parseInt(vorname);
                    int nachnameInt = Integer.parseInt(nachname);
                }
                 //exception wird geworfen, wenn der input string ist.
                catch(NumberFormatException e){
                    Customers.addCustomer(vorname, nachname);
                }
            }
        });

        Button addProduct = contBuilder.createButton("Add product");
        gridpane.add(addProduct, 2, 3);

        addProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //todo user input dialog
                //Per input zahl als string holen, dann string in int parsen und exception abfangen

                Stage stage = new Stage();
                String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
                String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
                try{
                int preisInt = Integer.parseInt(preis);
                    Products.addProduct(item, preisInt);
                }
                catch(NumberFormatException e){
                    System.out.println("Bitte geben sie einen Integer Wert ein!");
                }
            }
        });

        Button removeCustomer = contBuilder.createButton("Remove selected customer");
        gridpane.add(removeCustomer, 1, 4);

        Button removeProducts = contBuilder.createButton("Remove selected product");
        gridpane.add(removeProducts, 2, 4);

        Button placeOrder = contBuilder.createButton("Place order");
        gridpane.add(placeOrder, 1, 5);

		gridpane.add(createCustomers(), 1, 2);
        gridpane.add(createProducts(),  2, 2);

        //css laden
        ObservableList<String> cssList = loadCss("stylesheet.css");
        if (cssList != null){
            scene.getStylesheets().addAll(cssList);
        }
        else{
            System.err.println("Failed to load CSS file.");
        }

        // steckt die Flaeche in das Fenster
        primaryStage.setScene(scene);

        // zeigt die Linien innerhalb der Gridpane
        // gridpane.setGridLinesVisible(true);
		 primaryStage.show();		 
		 
		// primaryStage.setScene(new Scene(root, 1024, 720, Color.WHITE));
		// primaryStage.show();
	}

	/**
	 * returned eine Menubar
	 * @return Menubar
	 */
	private MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
        final Menu menuClose = new Menu("Close");
		menuBar.getMenus().add(menu);
        menuBar.getMenus().add(menuClose);

		menu.getItems().add(new MenuItem("New"));
		menu.getItems().add(new MenuItem("Load"));
		menu.getItems().add(new MenuItem("Save"));
		menu.getItems().add(new MenuItem("Properties"));
		menu.getItems().add(new MenuItem("Print"));

		return menuBar;
	}

    private TableView createCustomers(){
        final TableView<Customers> customerTableView = new TableView();
        customerTableView.setPrefWidth(350);
        customerTableView.setPrefHeight(300);
        customerTableView.setItems(Customers.getCustomers());

        // Setup the first column: vName
        // 	TableColumn<Customers, String> vNameCol = new TableColumn<>("vName");  // intellij kann keine <>
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

        // Assemble table from the columns
        customerTableView.getColumns().add(vNameCol);
        customerTableView.getColumns().add(nNameCol);

        return customerTableView;
    }

    private TableView createProducts(){
        final TableView<Products> productsTableView = new TableView();

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

        // Assemble table from the columns
        productsTableView.getColumns().add(NameCol);
        productsTableView.getColumns().add(priceCol);

        return productsTableView;
    }

    private ObservableList<String> loadCss(String cssFileName){
        ObservableList<String> cssStyle = FXCollections.observableArrayList();
        URL url = getClass().getResource(cssFileName);
        if (url == null){
            return null;
        }
        cssStyle.addAll(url.toExternalForm());
        return cssStyle;
    }

}
