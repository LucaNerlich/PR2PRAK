package aufgabe2;

/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * Anwendung.java
 */

/**
 * Anwendung zum testen und starten des Programms
 * @author (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */

import java.lang.Math.*;

public class Anwendung {

	public static void main(String[] args) {

		try {
			@SuppressWarnings("rawtypes")
			StringComparator stringComparator = new StringComparator();

			Customer customer12 = new Customer("Hans", "C");
			Customer customer11 = new Customer("Hans", "B");
			Customer customer = new Customer("Hans", "A");
			Customer customer2 = new Customer("Hans", "D");
			Customer customer3 = new Customer("Franz", "E");
			Customer customer4 = new Customer("Tjorben", "F");
			// Customer customer5 = new Customer("Tjorben", "D");

			/*
			 * BinaryNode<String,Customer> customerNode = new
			 * BinaryNode<String,Customer>(customer.getSchluessel(), customer);
			 */

			BinaryTree<String, Customer> tree = new BinaryTree<String, Customer>(
					stringComparator);
			BinaryTreeHelper treeHelper = new BinaryTreeHelper();

			tree.addKnoten(
					new BinaryNode<String, Customer>(
							customer12.getSchluessel(), customer12), tree
							.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Customer>(
							customer11.getSchluessel(), customer11), tree
							.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Customer>(customer.getSchluessel(),
							customer), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Customer>(customer2.getSchluessel(),
							customer2), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Customer>(customer3.getSchluessel(),
							customer3), tree.getWurzel());
			tree.addKnoten(
					new BinaryNode<String, Customer>(customer4.getSchluessel(),
							customer4), tree.getWurzel());
			// tree.addKnoten(new
			// BinaryNode<String,Customer>(customer5.getSchluessel(),
			// customer5), tree.getWurzel());

			tree.ausgeben();

			System.out.println(tree.findKey("F, Tjorben"));

			System.out.println(treeHelper.countNodes(tree.getWurzel()));
			System.out.println(treeHelper.treeDepth(tree.getWurzel()));

			// tree.addKnoten(new
			// BinaryNode<String,Customer>(customer5.getSchluessel(),
			// customer5), tree.getWurzel());
			
			WebShop webShop = new WebShop();
		} catch (NodeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}