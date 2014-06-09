/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4, Aufgabe 1
 * ButtonPlaceOrderEventHandler.java
 *
 *  http://openbook.galileocomputing.de/javainsel/javainsel_07_005.html#dodtp9e02eb29-7c29-416b-bf6a-f22c19e1397a
 */

package aufgabe4.versuch2MVC;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Observable;

/**
 * Kuemmert sich um die Werte der Progressbar - stellt diese Bereit.
 * "Waechter - update() muss machen was der Waechter sagt"
 */
public class ButtonPlaceOrderEventHandler extends Observable implements
		EventHandler<ActionEvent> {

	private float progressValue = 0.0f;

	@Override
    public void handle(ActionEvent actionEvent) {

        System.err.println("START THREAD");
        String customer = GuiView.customerTableView.getSelectionModel().getSelectedItems().toString();
        String product = GuiView.productsTableView.getSelectionModel().getSelectedItems().toString();
        System.out.println(customer);
        System.out.println(product);

        //Abfangen wenn man nichts ausgewaehlt hat
        if(GuiView.customerTableView.getSelectionModel().getSelectedItems().isEmpty() || GuiView.productsTableView.getSelectionModel().getSelectedItems().isEmpty()){
        System.err.println("'Bitte waehlen Sie zuerst einen Kunden und ein Produkt aus!");
        }
        
        else{
        //anonyme innere Klasse
        //erzeugt ein Runnable, uebergiebt dies dem Thread. Wir koennen nicht direkt einen Thread verwenden, da wir bereits von Observable erben.
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (float i = 0; i <= 1.05f; i = i + 0.05f){
                    progressValue = i;
                    setChanged();
                    //ruft den Observer (ProgressbarObserver) auf. Dieser muss die Anweisungen befolgen
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
    }

    /**
     * Getter fuer den Fortschrittswert. Wird fuer die Progressbar genutzt.
     * @return progressValue -> int
     */
	public float getProgressValue() {
		return progressValue;
	}
}
