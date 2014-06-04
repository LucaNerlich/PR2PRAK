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
 */
public class Controller implements ConInt{


    private static Model model;
    private GuiView view;

    public Controller(Model model){

    //jetzt alle items initialisieren
    this.model = model;
    this.view = new GuiView(this);

    }

    public void show(){
        view.show(model.getPrimaryStage());
    }

    public static MenuBar createMenu() {
       return model.createMenu();
    }



    @Override
    public void onAddCustomer() {
    	//TODO  input, dann cancel -> exception abfangen
        Stage stage = new Stage();
        String vorname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Vornamen ein:", "VORNAME", "");
        String nachname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Nachname ein:", "NACHNAME", "");
        if (!vorname.equals("") && !nachname.equals("")) {
            try {
                int vInt = Integer.parseInt(vorname);
                int nInt = Integer.parseInt(nachname);
            }
            //exception wird geworfen, wenn der input string ist.
            catch (NumberFormatException e) {
                //TODO abfangen von ints
                Customers.addCustomer(vorname, nachname);
            }
        }
    }

    @Override
    public void onAddProduct() {
        //Per input zahl als string holen, dann string in int parsen und exception abfangen
        try {
            Stage stage = new Stage();
            String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
            String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
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
    }

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

    public void onClickExit(){
        Platform.exit();
    }
}
