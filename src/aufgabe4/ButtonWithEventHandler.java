package aufgabe4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * Schreibt etwas auf der Konsole.
 * Created by lnerlich on 12.05.14.
 */
public class ButtonWithEventHandler implements EventHandler<ActionEvent> {

       public void handle(ActionEvent event){

           System.out.println(GuiAnwendung.customerTableView.getSelectionModel().getSelectedItems().toString());
           System.out.println(GuiAnwendung.productsTableView.getSelectionModel().getSelectedItems().toString());

        // GuiAnwendung.progressBarStart();
           progressBarStart();
    }

    public void progressBarStart(){
        Thread worker = new Thread(GuiAnwendung.progressTask);
        worker.start();
    }
}
