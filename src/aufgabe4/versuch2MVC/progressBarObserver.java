package aufgabe4.versuch2MVC;

import javafx.scene.control.ProgressBar;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by lnerlich on 02.06.14.
 */
public class ProgressBarObserver extends ProgressBar implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.err.println("update");
        if (o instanceof ButtonPlaceOrderEventHandler){
            setProgress(((ButtonPlaceOrderEventHandler)o).getProgressValue());
        }
    }
}
