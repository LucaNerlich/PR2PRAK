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
            String befehl = "INSERT INTO Ehemalige (EhemaligenID, Vorname, Name, Geburtsname,Geburtsdatum, Email, Telefonnummer, Geschlecht) Values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement updStatement = con.prepareStatement (befehl);
			
			updStatement.setInt (1, 2);
			updStatement.setString (2, "Paul");
			updStatement.setString (3,  "Richter");
			updStatement.setString (4, "none");
			updStatement.setDate (5,  new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			updStatement.setString (6, "Paul.Richter@richter.de");
			updStatement.setInt (7, 133142131);
			updStatement.setString (8, "M");
			
			updStatement.executeUpdate( );
			updStatement.close();
			
			PreparedStatement updStatement2 = con.prepareStatement (befehl);
			
			updStatement.setInt (1, 2);
			updStatement.setString (2, "Paul");
			updStatement.setString (3,  "Richter");
			updStatement.setString (4, "none");
			updStatement.setDate (5,  new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			updStatement.setString (6, "Paul.Richter@richter.de");
			updStatement.setInt (7, 133142131);
			updStatement.setString (8, "M");
			
			updStatement.executeUpdate( );
			updStatement.close();
			
			// + " VALUES" +
			// "('1','Daniel','Sommerlig','none',TO_DATE('2003/07/09','yyyy/mm/dd')','d.s@pl.de',0405544343,'M')"+
			// + " VALUES" +
			// "('2','Luca','Nerlich','none',TO_DATE('1983/07/09','yyyy/mm/dd')','n.l@de.de',0405342343,'M')";

			/* Füge Klasse hinzu mit normalem Statement */
			// String befehl = "INSERT INTO Klasse" +
			// "(KlassenID,Bezeichnung,Anzahl,Jahrgang)" + "VALUES" +
			// "(1,'test',20,2014)";
			
			/* Füge Klasse hinzu mit Prepared Statement */
			String klasse = "INSERT INTO Klasse(KlassenID,Bezeichnung,Anzahl,Jahrgang) Values (?,?,?,?)";
			PreparedStatement updStatement3 = con.prepareStatement (klasse);
			
			updStatement2.setInt (1, 3);
			updStatement2.setString (2, "WI13");
			updStatement2.setInt (3,  60);
			updStatement2.setInt (4, 2013);
			
		    updStatement2.executeUpdate();
	
			updStatement2.close();

			/* Erstelle die Anweisung und uebergebe an das DBS */
			Statement statement = con.createStatement();
			String anfrage = "Select Anzahl From Klasse";
			// String anfrage = "SELECT titel, preis FROM Buch";
			ResultSet ergebnis = statement.executeQuery(anfrage);

			/* Verarbeite das Ergebnis */
			while (ergebnis.next()) {
				String Name = ergebnis.getString(1);
				if (ergebnis.wasNull()) {
					System.out.println("SQL-NULL");
				}// NULL behandeln
					// double preis = ergebnis.getDouble (2);
				System.out.println("Tupel:" + Name);
			}
			// ergebnis.close();
			// statement.close();
			 con.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
}
