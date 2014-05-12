package aufgabe4;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by lnerlich on 12.05.14.
 * Gibt die aktuelle Mauspostion auf der Konsole aus.
 */
public class MouseEventHandler implements EventHandler<MouseEvent> {

    public void handle(MouseEvent event){
        System.out.println(event.getSceneX() + ", " + event.getSceneY());
    }
}