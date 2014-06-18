/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4, Aufgabe 1
 * Main.java
 */

package aufgabe4.versuch2MVC;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Anwendungsklasse erbt von Application
 * Gibt die Stage dem Model weiter und initiailisert den Controller.
 * Controller.show zeigt das GUI an.
 */
public class Main extends Application {

    public static void main(String[] args) {
        try {
            launch(args); //wird zum Starten der Anwendung verwendet
        }
        catch(Exception e){

        }
    }

    /**
     * wird durch lauch(x) der main aufgerufen
     * @param primaryStage -> wird durch Application bereitgestellt
     */
    public void start(Stage primaryStage){
        //HauptModel der gesamten Anwendung
        ProvideStage model = new ProvideStage(primaryStage);

        //nimmt die stage aus Model und initialisiert im Controller die VIEW
        Controller controller = new Controller(model);
        controller.show(); //Der Controller zeigt die GUI an
    }
}
