package aufgabe4.versuch2MVC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Luca on 28.05.2014.
 */
public class GuiView implements Observer {

    public Scene scene;
    private static Label progressLabel = new Label("Working ...");
    public static ProgressBar progressBar = new ProgressBar(0);
    private float progressValue = 0.0f;

    public GuiView(){
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

    /*
        Button btn = contBuilder.createButton("test button");
        btn.setOnAction(new ButtonWithEventHandler());
    */

        final Button closeButton = contBuilder.createButton("CLOSE");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        borderPane.setBottom(closeButton);

        vBoxLeft.getChildren().add(new Label("VBOX 8 LEFT"));
        vBoxRight.getChildren().add(new Label("VBOX 8 RIGHT"));

        // label.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_MOVED, new MouseEventHandler());

        gridpane.add(new Label("Customers"), 1, 1);
        gridpane.add(new Label("Products"), 2, 1);


        gridpane.add(Controller.customerTableView, 1, 2);
        gridpane.add(Controller.productsTableView, 2, 2);

        Button addCustomer = contBuilder.createButton("Add customer");
        gridpane.add(addCustomer, 1, 3);

        addCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Stage stage = new Stage();
                    String vorname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Vornamen ein:", "VORNAME", "");
                    String nachname = Dialogs.showInputDialog(stage, "Bitte geben Sie den Nachname ein:", "NACHNAME", "");
                    if (!vorname.equals("") && !nachname.equals("")) {
                        try {
                            int vInt = Integer.parseInt(vorname);
                            int nInt = Integer.parseInt(nachname);
                        }
                        //exception wird geworfen, wenn der input string ist.
                        catch (NumberFormatException e) {
                            //TODO abfangen von ints
                            Customers.addCustomer(vorname, nachname);
                        }
                    }
                }
                catch(Exception e){

                }
            }
        });

        Button addProduct = contBuilder.createButton("Add product");
        gridpane.add(addProduct, 2, 3);

        addProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Per input zahl als string holen, dann string in int parsen und exception abfangen
                try {
                    Stage stage = new Stage();
                    String item = Dialogs.showInputDialog(stage, "Bitte geben Sie die Bezeichnung des Produkts ein:", "BEZEICHNUNG", "");
                    String preis = Dialogs.showInputDialog(stage, "Bitte geben Sie den Preis ein:", "PREIS", "");
                    if (!item.equals("") && !preis.equals("")) {
                        try {
                            int preisInt = Integer.parseInt(preis);
                            Products.addProduct(item, preisInt);
                        } catch (NumberFormatException e) {
                            System.out.println("Bitte geben sie einen Integer Wert ein!");
                        }
                    }
                }
                catch(Exception e){

                }
            }
        });

        // Progress Bar
        gridpane.add(progressBar, 2, 5);
        gridpane.add(progressLabel, 2, 6);

        Button placeOrder = contBuilder.createButton("Place order");
        //TODO KNOPF VERHALTEN EXTRA KLASSE, diese observen und damit die progressbar verknüpfen
        gridpane.add(placeOrder, 1, 5);
        placeOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              //TODO nullstring abfangen
                String customer = Controller.customerTableView.getSelectionModel().getSelectedItems().toString();
                String product = Controller.productsTableView.getSelectionModel().getSelectedItems().toString();

                if(!(customer.equals("") || product.equals(""))){
                    System.out.println(customer);
                    System.out.println(product);
                }

                for (float i = progressValue; i <= 1.05f; i = i + 0.05f){
                    /*try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {

                    } */
                    progressBar.setProgress(i);

                    System.err.println(i);
                }
            }
        });


        Button removeCustomer = contBuilder.createButton("Remove selected customer");
        gridpane.add(removeCustomer, 1, 4);
        removeCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int selectedIndex = Controller.customerTableView.getSelectionModel().getSelectedIndex();
                try {
                    Controller.customerTableView.getItems().remove(selectedIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Stage stage = new Stage();
                    Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
                }
            }
        });

        Button removeProducts = contBuilder.createButton("Remove selected product");
        gridpane.add(removeProducts, 2, 4);
        removeProducts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int selectedIndex = Controller.productsTableView.getSelectionModel().getSelectedIndex();
                try {
                    Controller.productsTableView.getItems().remove(selectedIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Stage stage = new Stage();
                    Dialogs.showInformationDialog(stage, "Bitte waehlen Sie zuerst einen Eintrag aus!", "Warning");
                }
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

    @Override
    public void update(Observable o, Object arg) {
        System.err.println("update");
    }
}
