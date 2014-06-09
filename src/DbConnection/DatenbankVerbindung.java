package DbConnection;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Die Klasse DatenbankVerbindung repraesentiert die Schnittstelle zur
 * Ehemaligen-Datenbank
 * 
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */
public class DatenbankVerbindung {
	public static void main(String[] args) {
		Connection con = null;
		try {

			/* Lade den Treiber für das DBMS */
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
			con = DriverManager.getConnection(url, username, passwort);

			/* Erstelle die Anweisung und uebergebe an das DBS */
			Statement statement = con.createStatement();
			String anfrage = "Select Bezeichnung From Artikel";
			// String anfrage = "SELECT titel, preis FROM Buch";
			ResultSet ergebnis = statement.executeQuery(anfrage);

			/* Verarbeite das Ergebnis */
			while (ergebnis.next()) {
				String Name = ergebnis.getString(1);
				if (ergebnis.wasNull()) {
				}// NULL behandeln
					// double preis = ergebnis.getDouble (2);
				System.out.println("Tupel:" + Name);
			}
			// ergebnis.close();
			// statement.close();
			// con.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
