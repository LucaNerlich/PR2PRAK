/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4, Aufgabe 1
 * ConInt.java
 *
 * orientiert an: http://blog.axxg.de/model-view-controller-mit-javafx/
 */

package aufgabe4.versuch2MVC;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * speichert die Stage und kuemmert sich um das menu
 */
public class Model {
    private Stage primaryStage = null;

    public Model(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        final Menu menuClose = new Menu("Close");
        menuBar.getMenus().add(menu);
        menuBar.getMenus().add(menuClose);

        menu.getItems().add(new MenuItem("New"));
        menu.getItems().add(new MenuItem("Load"));
        menu.getItems().add(new MenuItem("Save"));
        menu.getItems().add(new MenuItem("Properties"));
        menu.getItems().add(new MenuItem("Print"));

        return menuBar;
    }
}
