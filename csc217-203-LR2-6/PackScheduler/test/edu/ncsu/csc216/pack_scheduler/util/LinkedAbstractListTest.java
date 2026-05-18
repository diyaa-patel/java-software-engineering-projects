package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for ArrayList Class methods 
 * @author Diya Patel 
 * @author Preeti Joshi 
 * @author Sreyan 
 */
public class LinkedAbstractListTest {

	
	/**
	 * Test for ArrayList constructor 
	 */
	@Test
	public void testArrayList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<>(10); 
		assertEquals(0, list.size()); 
	}

	/**
	 * Tests ArrayList.add()
	 */
	@Test 
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<>(10); 
		
		//Add to empty list 
		list.add(0, "47"); 
		assertEquals(1, list.size()); 
		assertEquals("47", list.get(0)); 
		try
		{
			list.add(1, null);
		}
		catch(NullPointerException e)
		{
			System.out.println("Element cannot be null");
		}
		
		//Add to end of list 47, 38 
		list.add(1, "38"); 
		assertEquals(2, list.size()); 
		assertEquals("47", list.get(0)); 
		assertEquals("38", list.get(1)); 
		try
		{
			list.add(5, "38");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Index out of bounds");
		}
		
		//Add to the middle of the list 47, 26, 38  
		list.add(1, "26"); 
		assertEquals(3, list.size()); 
		assertEquals("47", list.get(0)); 
		assertEquals("26", list.get(1));
		assertEquals("38", list.get(2)); 
		
		//Add to front of list 14, 47, 26, 38 
		list.add(0, "14"); 
		assertEquals(4, list.size()); 
		assertEquals("14", list.get(0));
		assertEquals("47", list.get(1)); 
		assertEquals("26", list.get(2));
		assertEquals("38", list.get(3)); 
	}
	
	/**
	 * Tests ArrayList.remove()
	 */
	@Test 
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<>(10); 
		 
		//Add to empty list and then remove
		list.add(0, "47"); 
		assertEquals(1, list.size()); 
		list.remove(0);
		assertEquals(0, list.size()); 
		try
		{
			list.remove(-1);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println();
		}
		
		
		//Add to end of list 47, 38 and remove
		list.add(0, "47"); 
		list.add(1, "38"); 
		assertEquals(2, list.size()); 
		assertEquals("47", list.get(0)); 
		assertEquals("38", list.get(1));
		list.remove(1);
		assertEquals(1, list.size()); 
		assertEquals("47", list.get(0)); 
		list.add(1, "38"); 
		
		//Add to the middle of the list 47, 26, 38 and remove
		list.add(1, "26"); 
		assertEquals(3, list.size()); 
		assertEquals("47", list.get(0)); 
		assertEquals("26", list.get(1));
		assertEquals("38", list.get(2)); 
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("47", list.get(0)); 
		assertEquals("38", list.get(1));
		
		
		//Add to front of list 14, 47, 26, 38 and remove
		list.add(1, "26"); 
		list.add(0, "14"); 
		assertEquals(4, list.size()); 
		assertEquals("14", list.get(0));
		assertEquals("47", list.get(1)); 
		assertEquals("26", list.get(2));
		assertEquals("38", list.get(3)); 
		list.remove(2);
		assertEquals(3, list.size()); 
		assertEquals("14", list.get(0));
		assertEquals("47", list.get(1)); 
		assertEquals("38", list.get(2));
	}
	
	/**
	 * Tests ArrayList.set()
	 */
	@Test 
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<>(10); 
		
		list.add(0, "47"); 
		list.add(1, "38"); 
		list.add(2, "40"); 
		assertEquals(3, list.size()); 
		try
		{
			list.set(-1, "list");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println();
		}
		assertEquals("38", list.set(1, "52"));
		assertEquals("52", list.get(1));
		assertEquals("40", list.get(2));
		assertEquals("47", list.set(0, "10"));
		assertEquals("10", list.get(0));
		assertEquals("52", list.get(1));
		try
		{
			list.set(1, null);
		}
		catch(NullPointerException e)
		{
			System.out.println("Element cannot be null");
		}
	}
	
	/**
	 * Tests ArrayList.get()
	 */
	@Test 
	public void testGet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<>(10); 
		
		list.add(0, "47"); 
		list.add(1, "38"); 
		assertEquals(2, list.size()); 
		list.set(1, "52");
		try
		{
		list.get(2);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Index out of bounds");
		}
		try
		{
		list.get(-5);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Index out of bounds");
		}
	}
	 

}