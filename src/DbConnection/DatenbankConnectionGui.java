package DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javafx.scene.layout.VBox;

/**
 * Die Klasse DatenbankVerbindung repraesentiert die Schnittstelle zur
 * Ehemaligen-Datenbank
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */
public class DatenbankConnectionGui extends Application {
	Connection con = null;

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {

		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		Button connect = new Button("Verbindung zu DB");
		Button select1 = new Button("Alle Ehemalige");
		Button select2 = new Button("HAW-Hamburg Informatik Studs");
		Button delete = new Button("Lösche alle Ehemalige");
		Button delete2 = new Button("Lösche Ehemalige ab ID =2");
		Button insert1 = new Button("Füge drei Ehemalige hinzu");
		Button insert2 = new Button("Füge weitere Ehemalige hinzu");
		Button closeCon = new Button("Beende Verbindung zu DB");
		VBox box = new VBox();

		/*
		 * Button Funktionen einfügen
		 */
		connect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
				System.out.println("Oracle JDBC driver loaded ok.");
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
					con = DriverManager.getConnection(url, username, passwort);
					System.out.println("Verbindung wurde aufgebaut");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());
				}

			}

		});

		insert1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {
					/* Füge Ehemalige hinzu mit Prepared Statement */
					String ehemalige = "INSERT INTO Ehemalige (EhemaligenID, Vorname, Name, "
							+ "Geburtsname,Geburtsdatum, Email, Telefonnummer, Geschlecht) "
							+ "Values (?,?,?,?,?,?,?,?)";

					PreparedStatement updStatement = con
							.prepareStatement(ehemalige);

					updStatement.setInt(1, 1);
					updStatement.setString(2, "Daniel");
					updStatement.setString(3, "Sommerlig");
					updStatement.setString(4, "none");
					updStatement.setDate(
							5,
							new java.sql.Date((new Date(System
									.currentTimeMillis())).getTime()));
					updStatement.setString(6, "Daniel.Sommer@Sommer.de");
					updStatement.setInt(7, 545142131);
					updStatement.setString(8, "M");
					updStatement.executeUpdate();

					updStatement.setInt(1, 2);
					updStatement.setString(2, "Paul");
					updStatement.setString(3, "Richter");
					updStatement.setString(4, "none");
					updStatement.setDate(
							5,
							new java.sql.Date((new Date(System
									.currentTimeMillis())).getTime()));
					updStatement.setString(6, "Paul.Richt@richter.de");
					updStatement.setInt(7, 133142131);
					updStatement.setString(8, "M");
					updStatement.executeUpdate();

					updStatement.setInt(1, 3);
					updStatement.setString(2, "Luca");
					updStatement.setString(3, "Nerlich");
					updStatement.setString(4, "none");
					updStatement.setDate(
							5,
							new java.sql.Date((new Date(System
									.currentTimeMillis())).getTime()));
					updStatement.setString(6, "Luca.Nerlich@Nerlich.de");
					updStatement.setInt(7, 676534131);
					updStatement.setString(8, "M");
					updStatement.executeUpdate();
					updStatement.close();

					System.out
							.println("Daniel, Paul und Luca zur Datenbank hinzugefügt");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		insert2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {

					/* Füge Ehemalige hinzu mit Prepared Statement */
					String ehemalige = "INSERT INTO Ehemalige (EhemaligenID, Vorname, Name, "
							+ "Geburtsname,Geburtsdatum, Email, Telefonnummer, Geschlecht) "
							+ "Values (?,?,?,?,?,?,?,?)";

					PreparedStatement updStatement2 = con
							.prepareStatement(ehemalige);

					/* Generierung von Ehemaligen */
					for (int i = 3; i <= 15; i++) {
						int ehemaligenID = 1 + i;
						String vorname = "Ehemaliger" + i;
						String name = "Maliger" + i;
						String geburtsname = "none" + i;
						Date geburtsdatum = new java.sql.Date((new Date(System
								.currentTimeMillis())).getTime());
						String email = "Ehemaliger" + i + "@web.de";
						int telefonnummer = 42143242 + i;
						String geschlecht = "M";

						updStatement2.setInt(1, ehemaligenID);
						updStatement2.setString(2, vorname);
						updStatement2.setString(3, name);
						updStatement2.setString(4, geburtsname);
						updStatement2.setDate(5, geburtsdatum);
						updStatement2.setString(6, email);
						updStatement2.setInt(7, telefonnummer);
						updStatement2.setString(8, geschlecht);
						updStatement2.executeUpdate();
					}
					updStatement2.close();
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		select1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {
					Statement statement = con.createStatement();
					String anfrage = "Select Vorname, Name From Ehemalige";
					// String anfrage = "SELECT titel, preis FROM Buch";
					ResultSet ergebnis = statement.executeQuery(anfrage);

					/* Verarbeite das Ergebnis */
					System.out
							.println("\n################# Alle Ehemaligen ausgeben ####################");
					while (ergebnis.next()) {
						String Vorname = ergebnis.getString(1);
						if (ergebnis.wasNull()) {
							System.out.println("SQL-NULL");
						}// NULL behandeln
						String Name = ergebnis.getString(2);
						if (ergebnis.wasNull()) {
							System.out.println("SQL-NULL");
						}// NULL behandeln
						System.out.println("Vorname: " + Vorname
								+ " Nachname: " + Name);

					}
					ergebnis.close();
					statement.close();

				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		select2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {
					Statement statement = con.createStatement();
					String anfrage2 = "SELECT Vorname,Name FROM Ehemalige Where EhemaligenID IN "
							+ "(SELECT Ehemalige.EhemaligenID FROM Ehemalige, hatStudiert, Studiengang "
							+ "WHERE Ehemalige.EhemaligenID = hatstudiert.EhemaligenID AND "
							+ "hatstudiert.StudiengangID = Studiengang.StudiengangID AND Studiengang.StudiengangID "
							+ "IN (SELECT StudiengangID FROM Studiengang Where Bezeichnung = 'Informatik' "
							+ "AND Studiengang.StudiengangID IN  (SELECT Studiengang.StudiengangID FROM "
							+ "Studiengang,Professoren, HaeltVorlesung WHERE "
							+ "Studiengang.StudiengangID = HaeltVorlesung.StudiengangID AND Professoren.ProfID IN  "
							+ "(SELECT Professoren.ProfID FROM HaeltVorlesung WHERE HaeltVorlesung.ProfID IN  "
							+ "(SELECT ProfID FROM Professoren WHERE HochschulID IN (SELECT HochschulID "
							+ "FROM Hochschule WHERE Bezeichnung = 'HAW-Hamburg'))))))";
					ResultSet ergebnis2 = statement.executeQuery(anfrage2);

					/* Verarbeite das Ergebnis2 */
					System.out
							.println("\n################# Ehemaligen der HAW die Informatik studiert haben ####################");
					while (ergebnis2.next()) {
						String Vorname = ergebnis2.getString(1);
						if (ergebnis2.wasNull()) {
							System.out.println("SQL-NULL");
						}// NULL behandeln
						String Name = ergebnis2.getString(2);
						if (ergebnis2.wasNull()) {
							System.out.println("SQL-NULL");
						}// NULL behandeln

						System.out.println("Vorname: " + Vorname
								+ " Nachname: " + Name);
					}
					ergebnis2.close();
					statement.close();

				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {
					Statement statement3 = con.createStatement();
					statement3
							.execute("DELETE FROM Ehemalige WHERE EhemaligenID >= 0");

					statement3.close();

					System.out
							.println("Die Ehemaligen-Einträge wurde ab der ID = 0 gelöscht.");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		delete2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent AE) {
				/*
				 * Erstelle die Anweisung und uebergebe an das DBS
				 */
				try {
					Statement statement3 = con.createStatement();
					statement3
							.execute("DELETE FROM Ehemalige WHERE EhemaligenID > 2");

					statement3.close();

					System.out
							.println("Die Ehemaligen-Einträge wurde ab der ID = 3 gelöscht.");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());

				}

			}
		});

		closeCon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent AE) {

				/* Verbindung zur DB beenden */
				try {
					con.close();
					System.out.println("Vebindung zur Datenbank beendet.");
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());
				}

			}
		});

		box.getChildren().addAll(connect, insert1, insert2, select1, select2,
				delete, delete2, closeCon);
		box.setSpacing(10); // Abstand Vertikal
		box.setPadding(new Insets(10, 10, 20, 10)); // Position
		pane.setCenter(box);
		pane.setStyle("-fx-background-color: grey"); // Hintergrund

		primaryStage.setTitle("INS-Aufgabe 12");
		primaryStage.setX(400);
		primaryStage.setY(100);
		primaryStage.setHeight(400);
		primaryStage.setWidth(300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
