package videoStore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InventoryDecoratorTest 
{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.add(new Movie("Kal Ho Na Ho",10,10));
		inventory.add(new Movie("Dhoom",11,5));
		inventory.add(new Movie("Dhoom 2",12,12));		
		
		inventory.createMemento();
		
		// Adding movies
		inventory.add(new Movie("Dhoom 3",13,13));
		inventory.add(new Movie("koi mil gaya",14,14));
		inventory.add(new Movie("swades",15,16));
		
		// Selling a movie
		inventory.sell("Dhoom",4);
		
		
		// Adding copies
		inventory.addCopy("koi mil gaya", 10);
		
		// Change movie price
		inventory.changePrice("Kal Ho Na Ho", 100);
				
		inventory = null;
		System.gc();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() 
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		inventory.add(new Movie("Captain America",100,100));		
		assertEquals("test add : ",100, inventory.findPrice("Kal Ho Na Ho"));
		assertEquals("test add : ",100, inventory.findPrice("Captain America"));
	}
	
	@Test
	public void testSell()
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		assertEquals("test sell : ",1, inventory.findQuantity("Dhoom"));
		
		inventory.sell("Dhoom",1);		
		
		assertEquals("test sell : ",0, inventory.findQuantity("Dhoom"));
	}

	@Test
	public void testAddCopies()
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		assertEquals("test addCopies : ",24, inventory.findQuantity("koi mil gaya"));
		
		if(inventory.addCopy("koi mil gaya", 10))
			assertEquals("test addCopies : ",34, inventory.findQuantity("koi mil gaya"));
		else		
			assertEquals("test addCopies : ",24, inventory.findQuantity("koi mil gaya"));
	}
	
	@Test
	public void testChangePrice()
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		assertEquals("test changePrice : ",100, inventory.findPrice("Kal Ho Na Ho"));
		
		if(inventory.changePrice("Swades", 110))
			assertEquals("test changePrice : ",110, inventory.findPrice("Swades"));
	}
	
	@Test
	public void testFindPrice()
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		assertEquals("test findPrice : ",13, inventory.findPrice("Dhoom 3"));
	}
	
	@Test
	public void testFindQuantity()
	{
		InventoryDecorator inventory;
		inventory = new InventoryDecorator();
		inventory.restoreMemento();
		assertEquals("test findQuantity : ",13, inventory.findQuantity("Dhoom 3"));
	}
}
