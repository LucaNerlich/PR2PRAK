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
        Model model = new Model(primaryStage);
        Controller controller = new Controller(model);
        controller.show();
    }
}
