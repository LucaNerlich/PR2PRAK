/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * Controller.java
 */

package aufgabe4.versuch2MVC;

import javafx.application.Platform;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

 /**
 * Verwaltet die Logik der Gui-Komponenten
  * Implementiert ein Interface das die Logikmethoden der View vorgibt.
 */
public class Controller implements ConInt{


    private static Model model;
    private GuiView view;

     /**
      * referenziert das Model / PStage und erstellt eine neue View
      * @param model
      */
    public Controller(Model model){
    //jetzt alle items initialisieren
    this.model = model;
    this.view = new GuiView(this);
    }

     /**
      * zeigt die View des uebergebenen Models oder Grundlage an
      */
    public void show(){
        view.show(model.getPrimaryStage());
    }

     /**
      * Hilfsmethode zur Erstellung der Menubar.
      * @return MenuBar -> zieht die Menubar des Models
      */
    public static MenuBar createMenu() {
       return model.createMenu();
    }


     /**
      * InputDialog zum Hinzufuegen eines neuen "Customers"
      * erwartet zwei Strings
      */
    @Override
    public void onAddCustomer() {
    	//TODO  input, dann cancel -> exception abfangen
        Stage stage = new Stage();
        String vorname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Vornamen ein:", "VORNAME", "");
        String nachname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Nachname ein:", "NACHNAME", "");
        String vornameConfirmed = null;
        String nachnameConfirmed = null;

        try {
            int vInt = Integer.parseInt(vorname);
        }
        //exception wird geworfen, wenn der input string ist.
        catch (NumberFormatException e) {
            vornameConfirmed = vorname;

        }
        try {
            int nInt = Integer.parseInt(nachname);
        }
        catch (NumberFormatException e) {
            nachnameConfirmed = nachname;
        }
        if(vornameConfirmed != null && nachnameConfirmed != null) {
            Customers.addCustomer(vornameConfirmed, nachnameConfirmed);
        }
        else {
            Stage stageInputError = new Stage();
            Dialogs.showInformationDialog(stageInputError, "Bitte nutzen sie nur Buchstaben!", "Warning");
        }
    }

     /**
      * InputDialog zum Hinzufuegen eines neuen "Products"
      * erwartet einen String - Name und einen Integer fuer den Preis
      */
    @Override
    public void onAddProduct() {
        //Per input zahl als string holen, dann string in int parsen und exception abfangen
        /*   try {
             Stage stage = new Stage();
             String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
             String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
             String itemConfirmed = null;
             String preisConfirmed = null;

             if (!item.equals("") && !preis.equals("")) {
                 try {
                     int preisInt = Integer.parseInt(preis);
                     Products.addProduct(item, preisInt);
                } catch (NumberFormatException e) {
                    System.out.println("Bitte geben sie einen Integer Wert ein!");
                }
            }
        }
        catch(Exception e){
        }
         */
         Stage stage = new Stage();
         String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
         String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
         String itemConfirmed = null;
         int preisConfirmed = 0;

         try {
             int vInt = Integer.parseInt(item);
         }
         //exception wird geworfen, wenn der input string ist.
         catch (NumberFormatException e) {
             itemConfirmed = item;
         }
         try {
             int preisInt = Integer.parseInt(preis);
             preisConfirmed = preisInt;
         }
         catch (NumberFormatException e) {
         }
         if(itemConfirmed != null && preisConfirmed != 0) {
             Products.addProduct(itemConfirmed, preisConfirmed);
         }
         else {
             Stage stageInputError = new Stage();
             Dialogs.showInformationDialog(stageInputError, "Bitte nutzen Sie Buchstaben fuer den Namen und Ziffern fuer den Preis!", "Warning");
         }

    }

     /**
      * Loescht die selektierte Zeile der Tabelle
      */
    @Override
    public void onRemoveCustomer() {
        int selectedIndex = GuiView.customerTableView.getSelectionModel().getSelectedIndex();
        try {
            GuiView.customerTableView.getItems().remove(selectedIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            Stage stage = new Stage();
            Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
        }
    }

     /**
      * Loescht die selektierte Zeile der Tabelle
      */
    @Override
    public void onRemoveProduct() {
        int selectedIndex = GuiView.productsTableView.getSelectionModel().getSelectedIndex();
        try {
            GuiView.productsTableView.getItems().remove(selectedIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            Stage stage = new Stage();
            Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
        }
    }

     /**
      * Schliesst die Anwendung
      */
    public void onClickExit(){
        Platform.exit();
    }
}
