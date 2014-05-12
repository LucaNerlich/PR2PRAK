
package aufgabe4.jenkeCode;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Demonstrate a progress bar with a background worker thread.
 */
public class progress extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup stage
        primaryStage.setTitle("Worker threads!");
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);

        // Create a progress bar
        ProgressBar progressBar = new ProgressBar();
        root.getChildren().add(progressBar);

        // Create a label
        Label label = new Label("Working ...");
        root.getChildren().add(label);

        // Create a task (Runnable in the FX world)
        SampleTask sampleTask = new SampleTask(label);

        // Bind the progress bar to the task property
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(sampleTask.progressProperty());

        // Start the task as a thread
        Thread worker = new Thread(sampleTask);
        worker.start();

        // Finalize the stage
        primaryStage.setScene(new Scene(root, 120, 100));
        primaryStage.show();
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        Application.launch();
    }

}
