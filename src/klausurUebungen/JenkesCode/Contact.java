/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

/**
 * Representation of a contact in a contact list.
 * 
 * @author Philipp Jenke
 * 
 */
public class Contact {

    /**
     * Phone number.
     */
    private String number;

    /**
     * Name of the contact
     */
    private String name;

    /**
     * Constructor.
     */
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     */
    public String getNumber() {
        return number;
    }

}
