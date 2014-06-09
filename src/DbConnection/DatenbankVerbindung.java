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

			/* Füge Ehemalige hinzu mit Prepared Statement */
            String ehemalige = "INSERT INTO Ehemalige (EhemaligenID, Vorname, Name, Geburtsname,Geburtsdatum, Email, Telefonnummer, Geschlecht) Values (?,?,?,?,?,?,?,?)";			
			PreparedStatement updStatement = con.prepareStatement (ehemalige);

			updStatement.setInt (1, 1);
			updStatement.setString (2, "Daniel");
			updStatement.setString (3,  "Sommerlig");
			updStatement.setString (4, "none");
			updStatement.setDate (5,  new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			updStatement.setString (6, "Daniel.Sommer@Sommer.de");
			updStatement.setInt (7, 545142131);
			updStatement.setString (8, "M");
			updStatement.executeUpdate();
			
			updStatement.setInt (1, 2);
			updStatement.setString (2, "Paul");
			updStatement.setString (3,  "Richter");
			updStatement.setString (4, "none");
			updStatement.setDate (5,  new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			updStatement.setString (6, "Paul.Richt@richter.de");
			updStatement.setInt (7, 133142131);
			updStatement.setString (8, "M");
			updStatement.executeUpdate();

			updStatement.setInt (1, 3);
			updStatement.setString (2, "Luca");
			updStatement.setString (3,  "Nerlich");
			updStatement.setString (4, "none");
			updStatement.setDate (5,  new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			updStatement.setString (6, "Luca.Nerlich@Nerlich.de");
			updStatement.setInt (7, 676534131);
			updStatement.setString (8, "M");		
			updStatement.executeUpdate();
			//updStatement.close();
			
			/* Generierung von Ehemaligen */			
			for (int i = 3; i <= 15; i++){
			int ehemaligenID = 1+i;
			String vorname = "Ehemaliger" + i;
			String name = "Maliger"+i;
			String geburtsname = "none"+i;
			Date geburtsdatum = new java.sql.Date((new Date(System.currentTimeMillis())).getTime());
			String email = "Ehemaliger"+i+"@web.de";
			int telefonnummer = 42143242+i;
			String geschlecht = "M";
				
		    updStatement.setInt (1, ehemaligenID);
			updStatement.setString (2, vorname);
			updStatement.setString (3,  name);
			updStatement.setString (4, geburtsname);
			updStatement.setDate (5,  geburtsdatum);
			updStatement.setString (6, email);
			updStatement.setInt (7, telefonnummer);
			updStatement.setString (8, geschlecht);
			updStatement.executeUpdate();
			}
			
			
			/* Füge Klasse hinzu mit normalem Statement */
			Statement updStatement2 = con.createStatement();
			String befehl = "INSERT INTO Klasse" +
			"(KlassenID,Bezeichnung,Anzahl,Jahrgang)" + "VALUES" +
			 "(1,'test',20,2014)";
			 updStatement2.executeUpdate(befehl);
			 updStatement2.close();
			 
			/* Füge Klasse hinzu mit Prepared Statement */
			String klasse = "INSERT INTO Klasse(KlassenID,Bezeichnung,Anzahl,Jahrgang) Values (?,?,?,?)";
			PreparedStatement updStatement3 = con.prepareStatement (klasse);
				
			updStatement3.setInt (1, 2);
			updStatement3.setString (2, "WI13");
			updStatement3.setInt (3,  60);
			updStatement3.setInt (4, 2013);
			
		    updStatement3.executeUpdate();
			updStatement3.close(); 

			/* Erstelle die Anweisung und uebergebe an das DBS */
			Statement statement = con.createStatement();
			String anfrage = "Select Vorname, Name From Ehemalige";
			// String anfrage = "SELECT titel, preis FROM Buch";
			ResultSet ergebnis = statement.executeQuery(anfrage);

			/* Verarbeite das Ergebnis */
			while (ergebnis.next()) {
				String Vorname = ergebnis.getString(1);
				if (ergebnis.wasNull()) {
					System.out.println("SQL-NULL");
				}// NULL behandeln
				String Name = ergebnis.getString(2);
				if (ergebnis.wasNull()) {
					System.out.println("SQL-NULL");
				}// NULL behandeln
				System.out.println("Tupel: " + "Vorname: " + Vorname + "Nachname:" + Name);
			}
			 ergebnis.close();
			 statement.close();
			 
			 /* Verbindung zur DB beenden */
			 con.close();
			 
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
