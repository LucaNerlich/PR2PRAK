/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * GuiView.java
 */

package aufgabe4.versuch2MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

/**
 * View klasse zum Darstellen der einzelnen Komponenten.
 */
public class GuiView {

	public Scene scene;
	// public static Label progressLabel = new Label("Working ...");
	public static ProgressBarObserver progressBarNew = new ProgressBarObserver();
	public static TableView<Customers> customerTableView = new TableView();
	public static TableView<Products> productsTableView = new TableView();
	private final ConInt controllerInt;

	// Hiermit stellen wir sicher, dass die View die gewünschten Methoden vom Controller kennt
	public GuiView(final ConInt controllerInt) {
		this.controllerInt = controllerInt;

		ContentBuilder contBuilder = new ContentBuilder();
		BorderPane borderPane = new BorderPane(); //virtuelle Komponente die andere Elemente enthalten kann
		scene = new Scene(borderPane); //root ist borderpane

		GridPane gridpane = new GridPane();
		VBox vBoxLeft = new VBox(8);
		VBox vBoxRight = new VBox(8);

		borderPane.setRight(vBoxRight);
		borderPane.setLeft(vBoxLeft);

		// Create MENU
		borderPane.setTop(Controller.createMenu());
		borderPane.setCenter(gridpane);

		// Gridpane Layout
		gridpane.setPadding(new Insets(5)); // rand aussen
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		// zeigt die Linien innerhalb der Gridpane
		//gridpane.setGridLinesVisible(true);

		Text bgTitle = new Text("Aufgabe 4 - JavaFX");
		bgTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridpane.add(bgTitle, 0, 0, 2, 1);

		/**
		 * Umsetzung der Ereignisverarbeitung mit anonymer Innerer Klasse
		 */

		final Button closeButton = contBuilder.createButton("CLOSE");
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controllerInt.onClickExit();
			}
		});

		borderPane.setBottom(closeButton);

		vBoxLeft.getChildren().add(new Label("VBOX 8 LEFT"));
		vBoxRight.getChildren().add(new Label("VBOX 8 RIGHT"));

		gridpane.add(new Label("Customers"), 1, 1);
		gridpane.add(new Label("Products"), 2, 1);

		initCustomTable();
		initProduktTable();
		gridpane.add(GuiView.customerTableView, 1, 2);
		gridpane.add(GuiView.productsTableView, 2, 2);

		Button addCustomer = contBuilder.createButton("Add customer");
		gridpane.add(addCustomer, 1, 3);
		addCustomer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controllerInt.onAddCustomer();
			}
		});

		Button addProduct = contBuilder.createButton("Add product");
		gridpane.add(addProduct, 2, 3);
		addProduct.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controllerInt.onAddProduct();
			}
		});

		progressBarNew.setProgress(0);
		gridpane.add(progressBarNew, 2, 5);
		// gridpane.add(progressLabel, 2, 6);

		Button placeOrder = contBuilder.createButton("Place order");
		gridpane.add(placeOrder, 1, 5);

		ButtonPlaceOrderEventHandler buttonProgressHandler = new ButtonPlaceOrderEventHandler();
		buttonProgressHandler.addObserver(progressBarNew);
		placeOrder.setOnAction(buttonProgressHandler);

		Button removeCustomer = contBuilder
				.createButton("Remove selected customer");
		gridpane.add(removeCustomer, 1, 4);
		removeCustomer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controllerInt.onRemoveCustomer();
			}
		});

		Button removeProducts = contBuilder
				.createButton("Remove selected product");
		gridpane.add(removeProducts, 2, 4);
		removeProducts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				controllerInt.onRemoveProduct();
			}
		});

		// css laden
		ObservableList<String> cssList = loadCss("stylesheet.css");
		if (cssList != null) {
			scene.getStylesheets().addAll(cssList);
		} else {
			System.err.println("Failed to load CSS file.");
		}

	}

	public void show(Stage stage) {
		stage.setTitle("Aufgabe 4 - JavaFX");
		stage.setScene(scene);
		stage.show();
	}

	// laedt die CSS Datei
	private ObservableList<String> loadCss(String cssFileName) {
		ObservableList<String> cssStyle = FXCollections.observableArrayList();
		URL url = getClass().getResource(cssFileName);
		if (url == null) {
			return null;
		}
		cssStyle.addAll(url.toExternalForm());
		return cssStyle;
	}

	private void initCustomTable() {
		customerTableView.setPrefWidth(350);
		customerTableView.setPrefHeight(300);
		customerTableView.setItems(Customers.getCustomers());

		// Setup the first column: vName (erste Spalte erzeugen)
		// TableColumn<Customers, String> vNameCol = new TableColumn<>("vName");
		// // intellij kann keine <>
		TableColumn<Customers, String> vNameCol = new TableColumn("Vorname");
		vNameCol.setEditable(true);
		// Transformation zwischen Personenobjekt und Zeileninhalt.
		// Objekt = Customer, Typ für Tabellenzelle = String
		vNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>(
				"vName"));
		vNameCol.setPrefWidth(customerTableView.getPrefWidth() / 2);

		// Setup the second column: nName (zweite Spalte erzeugen)
		// TableColumn<Customers, String> nNameCol = new TableColumn<>("nName");
		// // intellij kann keine <>
		TableColumn<Customers, String> nNameCol = new TableColumn("Nachname");
		nNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>(
				"nName"));
		nNameCol.setPrefWidth(customerTableView.getPrefWidth() / 2);

		// Tabelle mit Spalten befuellen
		customerTableView.getColumns().add(vNameCol);
		customerTableView.getColumns().add(nNameCol);

		// LISTEN FOR CHANGES (eintrag markiert etc.)

	/*	customerTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Customers>() {
					@Override
					public void changed(
							ObservableValue<? extends Customers> observableValue,
							Customers customers, Customers customers2) {
					}
				}); */
	}

	private void initProduktTable() {
		productsTableView.setPrefWidth(350);
		productsTableView.setPrefHeight(300);
		productsTableView.setItems(Products.getProducts());

		// Setup the first column: Name
		// TableColumn<Products, String> NameCol = new TableColumn<>("Name"); //
		// intellij kann keine <>
		TableColumn<Products, String> NameCol = new TableColumn("Name");
		NameCol.setEditable(true);
		NameCol.setCellValueFactory(new PropertyValueFactory<Products, String>(
				"Name"));
		NameCol.setPrefWidth(productsTableView.getPrefWidth() / 2);

		// Setup the second colum: Price
		// TableColumn<Products, String> priceCol = new TableColumn<>("price");
		TableColumn<Products, String> priceCol = new TableColumn("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Products, String>(
				"Price"));
		priceCol.setPrefWidth(productsTableView.getPrefWidth() / 2);

		// Tabelle mit Spalten befuellen
		productsTableView.getColumns().add(NameCol);
		productsTableView.getColumns().add(priceCol);

		// LISTEN FOR CHANGES (eintrag markiert etc.)

		/**productsTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Products>() {
					@Override
					public void changed(
							ObservableValue<? extends Products> observableValue,
							Products products, Products products2) {
					}
				}); */
	}
}
