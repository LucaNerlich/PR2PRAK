package aufgabe4;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {

	private StringProperty vName;
	private StringProperty nName;
	private IntegerProperty alter;

	private static ObservableList<Person> people = FXCollections
			.<Person> observableArrayList();

	public Person(String vName, String nName, int alter) {
		setVName(vName);
		setNName(nName);
		setAlter(alter);
	}

	public final void setVName(String value) {
		vNameProperty().set(value);
	}

	public StringProperty vNameProperty() {
		if (vName == null) {
			vName = new SimpleStringProperty();
		}
		return vName;
	}

	public final void setNName(String value) {
		nNameProperty().set(value);
	}

	public StringProperty nNameProperty() {
		if (nName == null) {
			nName = new SimpleStringProperty();
		}
		return nName;
	}

	public final void setAlter(int value) {
		alterProperty().set(value);
	}

	public IntegerProperty alterProperty() {
		if (alter == null) {
			alter = new SimpleIntegerProperty();
		}
		return alter;
	}

	public final String getVName() {
		return vNameProperty().get();
	}

	public final String getNName() {
		return nNameProperty().get();
	}

	public final int getAlter() {
		return alterProperty().get();
	}

	public static ObservableList<Person> getPeople() {
		people.add(new Person("Luca", "Nerlich", 20));
		people.add(new Person("Luca2", "Nerlich2", 25));
		return people;
	}
}
