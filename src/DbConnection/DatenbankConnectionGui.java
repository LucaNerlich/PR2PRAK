/**
 * Test
 */
package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import javafx.scene.control.Button;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author r0xxta
 * 
 */
public class DatenbankConnectionGui extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {

		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		Button driver = new Button("Lade Treiber");
		Button con = new Button("Verbindung zu DB");
		Button select1 = new Button("Alle Ehemalige");
		VBox box = new VBox();

		/*
		 * Button Funktionen einfügen
		 */
		driver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("Oracle JDBC driver loaded ok.");

			}

		});
		
		select1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("Oracle JDBC driver loaded ok.");

			}

		});

		con.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				
				/* Benutzername und Passwort abfragen */
				String username = javax.swing.JOptionPane
						.showInputDialog("Enter Username");

				JPasswordField passwordField = new JPasswordField(10);
				passwordField.setEchoChar('#');
				JOptionPane.showMessageDialog(null, passwordField,
						"Enter password", JOptionPane.OK_OPTION);
				char[] pw = passwordField.getPassword();
				String passwort = String.valueOf(pw);

				/* Baue eine Verbindung auf */
				String url = "jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:inf09";
				try {
					Connection con = DriverManager.getConnection(url, username, passwort);
					System.out.println("Verbindung wurde aufgebaut");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());
				}

			}

		});


		box.getChildren().addAll(driver,con, select1);
		box.setSpacing(10); // Abstand Vertikal
		box.setPadding(new Insets(10, 10, 20, 10)); // Position
		pane.setCenter(box);
		pane.setStyle("-fx-background-color: grey"); // Hintergrund

		primaryStage.setTitle("INS-Aufgabe 12");
		primaryStage.setX(400);
		primaryStage.setY(100);
		primaryStage.setHeight(500);
		primaryStage.setWidth(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
