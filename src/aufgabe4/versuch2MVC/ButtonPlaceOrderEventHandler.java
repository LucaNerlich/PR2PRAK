package aufgabe4.versuch2MVC;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Observable;

/**
 * Created by lnerlich on 02.06.14.
 * http://openbook.galileocomputing.de/javainsel/javainsel_07_005.html#dodtp9e02eb29-7c29-416b-bf6a-f22c19e1397a
 */
public class ButtonPlaceOrderEventHandler extends Observable implements EventHandler<ActionEvent> {

    private float progressValue = 0.0f;

    @Override
    public void handle(ActionEvent actionEvent) {

        System.err.println("START THREAD");
        System.out.println(Controller.customerTableView.getSelectionModel().getSelectedItems().toString());
        System.out.println(Controller.productsTableView.getSelectionModel().getSelectedItems().toString());

        //anonyme innere Klasse
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (float i = 0; i <= 1.05f; i = i + 0.05f){
                    progressValue = i;
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(i);
                }
            }
        }).start();
    }

    public float getProgressValue() {
        return progressValue;
    }
}
