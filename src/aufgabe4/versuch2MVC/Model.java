package aufgabe4.versuch2MVC;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Created by Luca on 28.05.2014.
 * orientiert an: http://blog.axxg.de/model-view-controller-mit-javafx/
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
