/**
 * Praktikum WIPR2, SS 2014  
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2, 
 * TestBinaryTree.java
 */

package aufgabe2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aufgabe2.Customer;

/**
 * Diese Klasse repräsentiert den JUnit-Test
 * @author  (Daniel.Sommerlig@haw-hamburg.de) & (Lucasteffen.Nerlich@haw-hamburg.de) 
 *
 */

public class TestBinaryTree {

	@Test
	public void test() throws NodeException{
		StringComparator stringComparatorTest = new StringComparator();
		
		Customer customer1 = new Customer("Alex", "Schmidt");
		Customer customer2 = new Customer("Bernd", "Hansen");
		Customer customer3 = new Customer("Dieter", "Vogtendbauer");
		Customer customer4 = new Customer("Mannfred", "Dacksen");	
		
		
		BinaryTree<String, Customer> treeTest = new BinaryTree<String, Customer>(
				stringComparatorTest);
		
		BinaryTreeHelper treeHelperTest = new BinaryTreeHelper();

				
		treeTest.addKnoten(
				new BinaryNode<String, Customer>(customer1.getSchluessel(),
						customer1), treeTest.getWurzel());
		treeTest.addKnoten(
				new BinaryNode<String, Customer>(customer2.getSchluessel(),
						customer2), treeTest.getWurzel());
		treeTest.addKnoten(
				new BinaryNode<String, Customer>(customer3.getSchluessel(),
						customer3), treeTest.getWurzel());
		treeTest.addKnoten(
				new BinaryNode<String, Customer>(customer4.getSchluessel(),
						customer4), treeTest.getWurzel());
		
		
		int result = treeHelperTest.countNodes(treeTest.getWurzel());
		assertTrue(result == 4);
		
		int result2 = treeHelperTest.treeDepth(treeTest.getWurzel());
		assertTrue(result2 == 3);
		
		 Customer result3 = treeTest.findKey("Hansen, Bernd");
		 assertTrue(result3.getId() == 2);
	}
	
}
