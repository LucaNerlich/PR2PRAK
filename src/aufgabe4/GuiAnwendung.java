package aufgabe4;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

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
		primaryStage.setTitle("first Frame");

		// init Gridpane
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5)); // rand auﬂen
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		ToggleButton tBtn1 = contBuilder.createToggleButton("Toggle Button");
		ComboBox comboBox1 = contBuilder.createComboBox("Luca", "Fabian",
				"Laura");
		CheckBox ckBox1 = contBuilder.createCheckBox("el babo");
		TextField tField = contBuilder.createTextField("");
		Button btn = contBuilder.createButton("Button");

		// erstellt die Flaeche des Fensters
		// StackPane root = new StackPane();
		Group root = new Group();

		// Table:
		Label label = new Label("Person");
		gridpane.add(label, 0, 1);
		GridPane.setHalignment(label, HPos.CENTER);

		root.getChildren().add(gridpane);
		// fuege Button zur Gridpane hinzu
		gridpane.add(tBtn1, 0, 3);
		gridpane.add(comboBox1, 0, 2);
		gridpane.add(ckBox1, 1, 1);
		gridpane.add(tField, 2, 0);
		gridpane.add(btn, 2, 1);
		gridpane.add(createMenu(), 0, 0);
		gridpane.add(createTable(), 0, 5);

		// steckt die Flache in das Fenster
		primaryStage.setScene(new Scene(root, 1024, 720, Color.WHITE));
		primaryStage.show();
	}

	/**
	 * returned eine Menubar
	 * 
	 * @return Menubar
	 */
	private MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		menuBar.getMenus().add(menu);

		menu.getItems().add(new MenuItem("New"));
		menu.getItems().add(new MenuItem("Load"));
		menu.getItems().add(new MenuItem("Save"));
		menu.getItems().add(new MenuItem("Properties"));
		menu.getItems().add(new MenuItem("Print"));
		menu.getItems().add(new MenuItem("Close"));

		return menuBar;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TableView createTable() {
		final TableView<Person> personTableView = new TableView();
		personTableView.setPrefWidth(350);
		personTableView.setPrefHeight(300);
		personTableView.setItems(Person.getPeople());

		// Setup the first column: vName
		TableColumn<Person, String> vNameCol = new TableColumn<>("vName");
		vNameCol.setEditable(true);
		vNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"vName"));
		vNameCol.setPrefWidth(personTableView.getPrefWidth() / 3);

		// Setup the second column: nName
		TableColumn<Person, String> nNameCol = new TableColumn<>("nName");
		nNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"nName"));
		nNameCol.setPrefWidth(personTableView.getPrefWidth() / 3);

		// Setup the third colum: alter
		TableColumn<Person, String> lastNameCol = new TableColumn<>("alter");
		lastNameCol
				.setCellValueFactory(new PropertyValueFactory<Person, String>(
						"alter"));
		lastNameCol.setPrefWidth(personTableView.getPrefWidth() / 3);

		// Assemble table from the columns
		personTableView.getColumns().add(vNameCol);
		personTableView.getColumns().add(nNameCol);
		personTableView.getColumns().add(lastNameCol);

		return personTableView;
	}

}
