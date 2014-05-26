package aufgabe4;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

/**
 * A simple task which performs an action, which takes time. The task is the
 * background operation.
 *
 * @author Philipp Jenke
 */
public class ProgressTask extends Task<Boolean> implements Observer{

    /**
     * Reference to the label in the scene graph
     */
    public final Label label;
    private int  numberOfSteps = 20;

    /**
     * Constructor.
     */
    public ProgressTask(Label label) {
        this.label = label;
    }

    @Override
    protected Boolean call() throws Exception {
        // Update progress
        System.err.println("START");

        updateProgress(0, numberOfSteps - 1);
        for (int i = 0; i < numberOfSteps; i++) {
            // Sleep some time
            Thread.sleep(200);
            updateProgress(i, numberOfSteps - 1);
        }

        // Update the label
        System.err.println("DONE");
        updateLabel();

        Thread.sleep(500);
        updateLabelReset();
        System.err.println("RESET");


        // Return success
        return true;
    }

    /**
     * Update the label after the simulated worker is done.
     */
    private void updateLabel() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText("Done.");
            }
        });
    }

    private void updateLabelReset() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText("Working ...");
            }
        });
        numberOfSteps = 0;

        for (int i = 2; i > numberOfSteps; i--) {
            updateProgress(i, numberOfSteps - 1);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
