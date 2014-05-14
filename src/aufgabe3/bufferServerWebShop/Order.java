/**
 * Praktikum WIPR2, SS 2014
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 3, Aufgabe 1
 * Order.java
 */
package aufgabe3.bufferServerWebShop;

import aufgabe3.Customer;
import aufgabe3.Product;

/**
 * Diese Klasse repraesentiert eine Bestellung. Jede Bestellung hat einen Kunden
 * und das zugehoerige Produkt. Der Objektzaehler wird verwendet um die
 * generierten Bestellungen zu zählen
 */
public class Order {

	private Customer customer;
	private Product product;
	public static int objektzaehler = 0;

	public Order(Customer customer, Product product) {
		objektzaehler++;
		this.customer = customer;
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAnzOrder() {
		return objektzaehler;
	}

	@Override
	public String toString() {
		return "( " + customer + ": " + product + ")";
	}
}
