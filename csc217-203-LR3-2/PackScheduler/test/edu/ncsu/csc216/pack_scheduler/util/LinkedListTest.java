package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ListIterator;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedList Class
 * @author Diya Patel 
 * @author Preeti Joshi 
 * @author Ram 
 */
class LinkedListTest {

	/**
	 * Test for LinkedList constructor 
	 */
	@Test
	public void testArrayList() {
		LinkedList<String> list = new LinkedList<String>(); 
		assertEquals(0, list.size()); 
	}

	/**
	 * Tests LinkedList.add()
	 */
	@Test 
	public void testAdd() {
		LinkedList<Object> list = new LinkedList<Object>(); 
		
		//Add to empty list 
		list.add(0, 47); 
		assertEquals(1, list.size()); 
		assertEquals(47, list.get(0)); 
		try
		{
			list.add(1, null);
		}
		catch(NullPointerException e)
		{
			System.out.println("Element cannot be null");
		}
		
		//Add to end of list 47, 38 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		assertEquals(47, list.get(0)); 
		assertEquals(38, list.get(1)); 
		try
		{
			list.add(5, 32);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Index out of bounds");
		}
		
		//Add to the middle of the list 47, 26, 38  
		list.add(1, 26); 
		assertEquals(3, list.size()); 
		assertEquals(47, list.get(0)); 
		assertEquals(26, list.get(1));
		assertEquals(38, list.get(2)); 
		
		//Add to front of list 14, 47, 26, 38 
		list.add(0, 14); 
		assertEquals(4, list.size()); 
		assertEquals(14, list.get(0));
		assertEquals(47, list.get(1)); 
		assertEquals(26, list.get(2));
		assertEquals(38, list.get(3)); 
	}
	
	/**
	 * Tests LinkedList.remove()
	 */
	@Test 
	public void testRemove() {
		LinkedList<Object> list = new LinkedList<Object>(); 
		 
		//Add to empty list and then remove
		list.add(0, 47); 
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
		list.add(0, 47); 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		assertEquals(47, list.get(0)); 
		assertEquals(38, list.get(1));
		list.remove(1);
		assertEquals(1, list.size()); 
		assertEquals(47, list.get(0)); 
		list.add(1, 38); 
		
		//Add to the middle of the list 47, 26, 38 and remove
		list.add(1, 26); 
		assertEquals(3, list.size()); 
		assertEquals(47, list.get(0)); 
		assertEquals(26, list.get(1));
		assertEquals(38, list.get(2)); 
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals(47, list.get(0)); 
		assertEquals(38, list.get(1));
		
		
		//Add to front of list 14, 47, 26, 38 and remove
		list.add(1, 26); 
		list.add(0, 14); 
		assertEquals(4, list.size()); 
		assertEquals(14, list.get(0));
		assertEquals(47, list.get(1)); 
		assertEquals(26, list.get(2));
		assertEquals(38, list.get(3)); 
		list.remove(2);
		assertEquals(3, list.size()); 
		assertEquals(14, list.get(0));
		assertEquals(47, list.get(1)); 
		assertEquals(38, list.get(2));
	}
	
	/**
	 * Tests LinkedList.set()
	 */
	@Test 
	public void testSet() {
		LinkedList<Object> list = new LinkedList<Object>(); 
		
		list.add(0, 47); 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		try
		{
			list.set(-1, list);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println();
		}
		list.set(1, 52);
		assertEquals(47, list.get(0));
		assertEquals(52, list.get(1));
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
	 * Tests LinkedList.get()
	 */
	@Test 
	public void testGet() {
		ArrayList<Object> list = new ArrayList<Object>(); 
		
		list.add(0, 47); 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		list.set(1, 52);
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
	
	/**
	 * Test LinkedList.growArray() 
	 */
	@Test 
	public void testGrowArray() {
		LinkedList<Object> list = new LinkedList<Object>(); 
		
		list.add(0, 47); 
		list.add(1, 38); 
		list.add(2, 45); 
		list.add(3, 36); 
		list.add(4, 41); 
		list.add(5, 33); 
		list.add(6, 12); 
		list.add(7, 32); 
		list.add(8, 40); 
		list.add(9, 88); 
		list.add(10, 49); 
		assertEquals(list.size(), 11); 
		
	}
	
  /**
	* Test LinkedList.listIterator() 
	*/
	@Test 
	public void testIteration() {
	Integer[] data = new Integer[5];
	data[0] = 47;
	data[1] = 38;
	data[2] = 45;
	data[3] = 36;
	data[4] = 33;
	LinkedList<Integer> list = new LinkedList<Integer>(); 
	for(int i = 0; i < data.length; i++) {
		list.add(data[i]);
	}
//	list.add(47); 
//	list.add(38); 
//	list.add(45); 
//	list.add(36); 
//	list.add(41); 
//	list.add(33); 
	int i = 0;
	ListIterator<Integer> ite = list.listIterator(0);
	while(ite.hasNext()) {
	     // System.out.println(ite.next());
	      assertEquals(ite.next(), data[i]); 
	      i++;
	      	      
	    }
	i = 3; 
	ListIterator<Integer> itepre = list.listIterator(4);
	while(itepre.hasPrevious()) {
		  i--; 
		 assertEquals(itepre.previous(), data[i]); 
	    }
	}
	
	/**
	 * Test lastIndexOf() 
	 */
	@Test 
	public void testLastIndexOf() {
		String[] data = new String[4];
		data[0] = "orange";
		data[1] = "banana";
		data[2] = "apple";
		data[3] = "kiwi";
		LinkedList<String> list = new LinkedList<String>(); 
		for(int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
		
		assertEquals(list.lastIndexOf("orange"), 0); 
		assertEquals(list.indexOf("orange"), 0); 
		
	}

}
