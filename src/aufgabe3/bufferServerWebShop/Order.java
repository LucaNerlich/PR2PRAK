package aufgabe3.bufferServerWebShop;

import aufgabe3.Customer;
import aufgabe3.Product;
import aufgabe3.WebShop;

/**
 * Diese Klasse repraesentiert eine Bestellung.
 * Jede Bestellung hat einen Kunden und das zugehoerige Produkt.
 * @author Luca
 *
 */
public class Order {

	private Customer customer;
	private Product product;
	
	public Order(Customer customer, Product product){	
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

	@Override
	public String toString() {
		return "Order [customer=" + customer + ", product=" + product + "]";
	}
	
	
	
}
