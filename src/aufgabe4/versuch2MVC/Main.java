package aufgabe4.versuch2MVC;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Luca on 28.05.2014.
 */
public class Main extends Application {

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch(Exception e){

        }
    }

    public void start(Stage primaryStage){
        //HauptModel der gesamten Anwendung
        Model model = new Model(primaryStage);

        //nimmt die stage aus Model und initialisiert im Controller die VIEW
        Controller controller = new Controller(model);
        controller.show();
    }
}
