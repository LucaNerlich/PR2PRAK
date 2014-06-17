/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4, Aufgabe 1
 * public class ProgressBarObserver extends ProgressBar implements Observer{
 .java
 */

package aufgabe4.versuch2MVC;

import javafx.scene.control.ProgressBar;

import java.util.Observable;
import java.util.Observer;

/**
 * Eine um Observer(Beobachter) erweiterte Progressbar.update() wird durch den Eventhandler aufgerufen
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
