package aufgabe2;
/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * NodeException.java
 */

/**
 * Standard Klasse zur reprsaentation der Produkte
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 *
 */
public class Product {

		private double preis;
		private String name;
		
		public Product(String name, double preis){
			this.preis = preis;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Product [preis=" + preis + ", name=" + name + "]";
		}

		public double getWert() {
			return preis;
		}

		public void setWert(double preis) {
			this.preis = preis;
		}

		public String getSchluessel() {
			return name;
		}

		public void setSchluessel(String name) {
			this.name = name;
		}
		
}
