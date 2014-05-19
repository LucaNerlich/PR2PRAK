package aufgabe4;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 * A simple task which performs an action, which takes time. The task is the
 * background operation.
 *
 * @author Philipp Jenke
 */
public class ProgressTask extends Task<Boolean> {

    /**
     * Reference to the label in the scene graph
     */
    public final Label label;

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
        int numberOfSteps = 20;
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
    }
}
