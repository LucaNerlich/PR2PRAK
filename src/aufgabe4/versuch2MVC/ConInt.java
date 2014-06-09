/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 4, Aufgabe 1
 * ConInt.java
 */

package aufgabe4.versuch2MVC;

/**
 * Interface das die Methoden fuer die View bereitstellt.
 */
public interface ConInt {

    void onAddCustomer();
    void onAddProduct();
    void onRemoveCustomer();
    void onRemoveProduct();
    void onClickExit();

}
