package aufgabe4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/**
 * Erzeugt Inhalte fuer die Gridpane etc
 * @author Luca
 *
 */
public class ContentBuilder {

	public ContentBuilder(){
		
	}
	
	/**
	 * returned einen ToggleButton
	 * @param String text -> Inhalt des Buttons
	 * @return ToggleButton
	 */
	public ToggleButton createToggleButton(String text){
		ToggleButton btn = new ToggleButton();
		btn.setText(text);
				
		return btn;
	}
	
	/**
	 * returned eine Combobox
	 * @param String vararg content -> "x1", "x2" ... "xn"
	 * @return ComboBox
	 */
	public ComboBox<String> createComboBox(String ... content ){
		ComboBox<String> comboBox = new ComboBox<String>(
				FXCollections.observableArrayList(content));
		comboBox.setValue(content[1]);
		
		return comboBox;
	}
	
	public CheckBox createCheckBox(String text){
		CheckBox ckBox = new CheckBox(text);
		ckBox.setSelected(false);
		
		return ckBox;
	}
}
