package aufgabe4.jenkeCode;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 * A simple task which performs an action, which takes time. The task is the
 * background operation.
 *
 * @author Philipp Jenke
 */
public class SampleTask extends Task<Boolean> {

    /**
     * Reference to the label in the scene graph
     */
    private final Label label;

    /**
     * Constructor.
     */
    public SampleTask(Label label) {
        this.label = label;
    }

    @Override
    protected Boolean call() throws Exception {
        // Update progress
        int numberOfSteps = 20;
        updateProgress(0, numberOfSteps - 1);
        for (int i = 0; i < numberOfSteps; i++) {
            // Sleep some time
            Thread.sleep(200);
            updateProgress(i, numberOfSteps - 1);
        }

        // Update the label
        updateLabel();

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
}
