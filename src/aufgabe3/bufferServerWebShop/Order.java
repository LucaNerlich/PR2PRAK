package aufgabe3.bufferServerWebShop;

import aufgabe3.Customer;
import aufgabe3.Product;

/**
 * Diese Klasse repraesentiert eine Bestellung.
 * Jede Bestellung hat einen Kunden und das zugehoerige Produkt.
 * @author Luca
 *
 */
public class Order {

	private Customer customer;
	private Product product;
	public static int objektzaehler = 0;	
	
	public Order(Customer customer, Product product){	
		objektzaehler++;
		this.customer = customer;
		this.product = product;		
		System.err.println(objektzaehler);		
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
	
	public int getAnzOrder(){
		return objektzaehler;
	}

	@Override
	public String toString() {
		return "TOSTRING: Order [customer=" + customer + ", product=" + product + "]";
	}	
}
