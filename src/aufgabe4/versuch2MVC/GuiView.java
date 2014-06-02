package aufgabe4.versuch2MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Luca on 28.05.2014.
 */
public class GuiView {

    public Scene scene;
    public static Label progressLabel = new Label("Working ...");
    public static ProgressBarObserver progressBarNew = new ProgressBarObserver();
    private final ConInt controller;


    public GuiView(final ConInt controller){
        this.controller = controller;

        ContentBuilder contBuilder = new ContentBuilder();
        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane);

        GridPane gridpane = new GridPane();
        VBox vBoxLeft = new VBox(8);
        VBox vBoxRight = new VBox(8);

        borderPane.setRight(vBoxRight);
        borderPane.setLeft(vBoxLeft);

        // Create MENU
        borderPane.setTop(Controller.createMenu());
        borderPane.setCenter(gridpane);

        // Gridpane Layout
        gridpane.setPadding(new Insets(5)); // rand aussen
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        // zeigt die Linien innerhalb der Gridpane
        // gridpane.setGridLinesVisible(true);

        Text bgTitle = new Text("Aufgabe 4 - JavaFX");
        bgTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridpane.add(bgTitle, 0, 0, 2, 1);

        final Button closeButton = contBuilder.createButton("CLOSE");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.onClickExit();
            }
        });

        borderPane.setBottom(closeButton);

        vBoxLeft.getChildren().add(new Label("VBOX 8 LEFT"));
        vBoxRight.getChildren().add(new Label("VBOX 8 RIGHT"));

        gridpane.add(new Label("Customers"), 1, 1);
        gridpane.add(new Label("Products"), 2, 1);

        gridpane.add(Controller.customerTableView, 1, 2);
        gridpane.add(Controller.productsTableView, 2, 2);

        Button addCustomer = contBuilder.createButton("Add customer");
        gridpane.add(addCustomer, 1, 3);
        addCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.onAddCustomer();
            }
        });

        Button addProduct = contBuilder.createButton("Add product");
        gridpane.add(addProduct, 2, 3);
        addProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.onAddProduct();
            }
        });

        progressBarNew.setProgress(0);
        gridpane.add(progressBarNew, 2, 5);
        gridpane.add(progressLabel, 2, 6);




        Button placeOrder = contBuilder.createButton("Place order");
        gridpane.add(placeOrder, 1, 5);

        ButtonPlaceOrderEventHandler buttonProgressHandler = new ButtonPlaceOrderEventHandler();
        buttonProgressHandler.addObserver(progressBarNew);
        placeOrder.setOnAction(buttonProgressHandler);

        Button removeCustomer = contBuilder.createButton("Remove selected customer");
        gridpane.add(removeCustomer, 1, 4);
        removeCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             controller.onRemoveCustomer();
            }
        });

        Button removeProducts = contBuilder.createButton("Remove selected product");
        gridpane.add(removeProducts, 2, 4);
        removeProducts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              controller.onRemoveProduct();
            }
        });

        //css laden
        ObservableList<String> cssList = loadCss("stylesheet.css");
        if (cssList != null) {
            scene.getStylesheets().addAll(cssList);
        } else {
            System.err.println("Failed to load CSS file.");
        }

    }

    public void show(Stage stage){
        stage.setTitle("Aufgabe 4 - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    // laedt die CSS Datei
    private ObservableList<String> loadCss(String cssFileName) {
        ObservableList<String> cssStyle = FXCollections.observableArrayList();
        URL url = getClass().getResource(cssFileName);
        if (url == null) {
            return null;
        }
        cssStyle.addAll(url.toExternalForm());
        return cssStyle;
    }
}
