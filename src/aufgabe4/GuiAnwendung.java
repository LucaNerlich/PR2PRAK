package aufgabe4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
		gridpane.setHgap(5);// rand zwischen den Zeilen
		gridpane.setVgap(5);

		ToggleButton tBtn1 = contBuilder.createToggleButton("Toggle Button");
		ComboBox comboBox1 = contBuilder.createComboBox("Luca", "Fabian",
				"Laura");
		CheckBox ckBox1 = contBuilder.createCheckBox("el babo");
		TextField tField = contBuilder.createTextField("");
		Button btn = contBuilder.createButton("Button");

		// erstellt die Flaeche des Fensters
		StackPane root = new StackPane();		

		// MenuBar hinzufuegen
		// root.getChildren().add(createMenu());
		
		root.getChildren().add(gridpane);

		// fuege Button zur Gridpane hinzu
		gridpane.add(tBtn1, 0, 2);
		gridpane.add(comboBox1, 0, 1);
		gridpane.add(ckBox1, 1, 1);
		gridpane.add(tField, 2, 0);
		gridpane.add(btn, 2, 1);	
		gridpane.add(createMenu(),0 , 0);

		// steckt die Flache in das Fenster
		primaryStage.setScene(new Scene(root, 1024, 720));
		primaryStage.show();
	}

	/**
	 * returned eine Menubar 
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

}
