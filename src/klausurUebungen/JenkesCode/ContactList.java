/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package pr2.streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Philipp Jenke
 * 
 *         The contact list contains contacts.
 * 
 */
public class ContactList {

    /**
     * This list contains the contacts.
     */
    private ArrayList<Contact> list = new ArrayList<Contact>();

    /**
     * Write the contacts to a file.
     * 
     * @param File
     *            name of the target file.
     * @return True on success.
     */
    private boolean writeList(String filename) {
        boolean result = true;

        // Open stream in try in order to close it automatically in case of an
        // exception.
        try (ObjectOutputStream dateiStream = new ObjectOutputStream(
                new FileOutputStream(filename))) {

            // Deserialize list and save it.
            dateiStream.writeObject(list);
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    private boolean readList(String filename) {
        boolean result = true;
        try (ObjectInputStream fileStream = new ObjectInputStream(
                new FileInputStream(filename))) {
            // Read list from file/stream
            Object o = fileStream.readObject();
            @SuppressWarnings("unchecked")
            // Nasty, I know
            ArrayList<Contact> newList = (ArrayList<Contact>) o;
            // Append contact to the list.
            for (Contact contact : newList) {
                addContact(contact.getName(), contact.getNumber());
            }
        } catch (ClassNotFoundException e) {
            result = false;
        } catch (FileNotFoundException e1) {
            result = false;
            e1.printStackTrace();
        } catch (IOException e1) {
            result = false;
            e1.printStackTrace();
        }
        return result;
    }

    /**
     * Add a new contact to the list
     */
    private void addContact(String name, String number) {
        list.add(new Contact(name, number));

    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        ContactList contactList = new ContactList();
        contactList.writeList("contacts.cntcts");
        contactList.readList("contacts.cntcts");
    }

}
