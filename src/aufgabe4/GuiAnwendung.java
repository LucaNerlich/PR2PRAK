package aufgabe4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
		//erstellt das Fenster mit Titel
		ContentBuilder contBuilder = new ContentBuilder();
		primaryStage.setTitle("first Frame");
		
		//init Gridpane
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5)); //rand auﬂen
		gridpane.setHgap(5);//rand zwischen den Zeilen
		gridpane.setVgap(5);
		
		ToggleButton btn1 = contBuilder.createToggleButton("first Button");		
		ComboBox comboBox1 = contBuilder.createComboBox("Luca", "Fabian", "Laura");
		CheckBox ckBox1 = contBuilder.createCheckBox("el babo");
		/*
		ToggleButton btn = new ToggleButton();
		btn.setText("first Button");
		
		ToggleButton btn2 = new ToggleButton();
		btn2.setText("second Button");
		btn2.setOpacity(0.4);
		*/
		
		
		//fuege Button zur Gridpane hinzu
		gridpane.add(btn1 , 0 , 0);
		gridpane.add(comboBox1 , 0 , 1);
		gridpane.add(ckBox1 , 1 , 0);
		// gridpane.add(btn2 , 0 , 1);		
		
		//erstellt die Flaeche des Fensters
		StackPane root = new StackPane();
		root.getChildren().add(gridpane);
	
		//steckt die Flache in das Fenster
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

}
