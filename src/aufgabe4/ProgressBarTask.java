package aufgabe4;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Created by lnerlich on 26.05.14.
 */
public class ProgressBarTask {

    Timeline task = null;

    private int  numberOfSteps = 20;

    public ProgressBarTask(){

        task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(GuiAnwendung.progressBarTimeLine.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(GuiAnwendung.progressBarTimeLine.progressProperty(), 1)
                )
        );
    }

}
