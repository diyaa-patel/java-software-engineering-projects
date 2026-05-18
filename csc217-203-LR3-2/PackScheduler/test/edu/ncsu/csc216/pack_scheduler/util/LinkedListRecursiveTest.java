package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests LinkedListRecursive
 * @author Diya Patel
 * @author Ramcharan Reddy
 * @author Preeti Joshi
 */
class LinkedListRecursiveTest {
	
	/**
	 * Test for LinkedListRecursive constructor 
	 */
	@Test
	public void testArrayList() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>(); 
		assertEquals(0, list.size()); 
	}

	/**
	 * Tests LinkedListRecursive.add()
	 */
	@Test 
	public void testAdd() {
		LinkedListRecursive<Object> list = new LinkedListRecursive<Object>(); 
		
		//Add to empty list 
		list.add(0, 47); 
		assertEquals(1, list.size()); 
		assertEquals(47, list.get(0)); 
		assertThrows(NullPointerException.class, () -> list.add(1, null)); 
		
		//Add to end of list 47, 38 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		assertEquals(47, list.get(0)); 
		assertEquals(38, list.get(1));  
		
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
	 * Tests LinkedListRecursive.remove()
	 */
	@Test 
	public void testRemove() {
		LinkedListRecursive<Object> list = new LinkedListRecursive<Object>(); 
		 
		//Add to empty list and then remove
		list.add(0, 47); 
		assertEquals(1, list.size()); 
		list.remove(0);
		assertEquals(0, list.size()); 
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1)); 
		
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
	 * Tests LinkedListRecursive.remove() 
	 */
	@Test 
	public void testRemoveElement() {
		LinkedListRecursive<Object> list = new LinkedListRecursive<Object>(); 
		assertEquals(0, list.size()); 
		//Add to empty list and then remove
		list.add(0, "orange"); 
		assertEquals(1, list.size()); 
		list.remove("orange");
		assertEquals(0, list.size()); 
		assertFalse(list.remove("yellow"));  
		
	}
	
	/**
	 * Tests LinkedListRecursive.set()
	 */
	@Test 
	public void testSet2() {
		LinkedListRecursive<Object> list = new LinkedListRecursive<Object>(); 
		
		list.add(0, 47); 
		list.add(1, 38);  
		assertEquals(2, list.size()); 
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 36)); 
		
		list.set(1, 52);
		assertEquals(47, list.get(0));
		assertEquals(52, list.get(1));
		assertThrows(NullPointerException.class, () -> list.set(1, null));
	}
	
	/**
	 * Tests LinkedListRecursive.get()
	 */
	@Test 
	public void testGet() {
		LinkedListRecursive<Object> list = new LinkedListRecursive<Object>(); 
		
		list.add(0, 47); 
		list.add(1, 38); 
		assertEquals(2, list.size()); 
		list.set(1, 52);
		assertThrows(IllegalArgumentException.class, () -> list.set(-1, 52)); 
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-5)); 
	}
	
	/**
	 * Test LinkedListRecursive.add() 
	 */
	@Test 
	public void testAddAtIndex() {
		Integer[] data = new Integer[5];
		data[0] = 47;
		data[1] = 38;
		data[2] = 45;
		data[3] = 36;
		data[4] = 33;
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>(); 
	
		for(int i = 0; i < data.length; i++) {
			list.add(i, data[i]);
		}
		
		for(int i = 0; i < data.length; i++) {
			assertEquals(data[i], list.get(i));
		}
		
		assertEquals(list.size(), 5); 
		
	}
	
	/**
	 * Test LinkedListRecursive.add() 
	 */
	@Test 
	public void testAddAtIndex2() {
		Integer[] data = new Integer[4];
		data[0] = 47;
		data[1] = 38;
		data[2] = 36;
		data[3] = 33;
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>(); 
	
		for(int i = 0; i < data.length; i++) {
			list.add(i, data[i]);
		}
		
		list.add(2, 45);
		data = new Integer[5];
		data[0] = 47;
		data[1] = 38;
		data[2] = 45;
		data[3] = 36;
		data[4] = 33;
		
		for(int i = 0; i < data.length; i++) {
			assertEquals(data[i], list.get(i));
		}
		
		assertEquals(list.size(), 5); 
		
	}
	
	/**
	 * Test LinkedListRecursive.set() 
	 */
	@Test 
	public void testSet() {
		Integer[] data = new Integer[4];
		data[0] = 47;
		data[1] = 38;
		data[2] = 36;
		data[3] = 33;
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>(); 
	
		for(int i = 0; i < data.length; i++) {
			list.add(i, data[i]);
		}
		
		list.set(2, 45);
		data[2] = 45;
		for(int i = 0; i < data.length; i++) {
			assertEquals(data[i], list.get(i));
		}
		
		assertEquals(list.size(), 4); 
		
	}
	
	
	
  /**
	* Test LinkedListRecursive.add() 
	*/
	@Test 
	public void testAddElement() {
	Integer[] data = new Integer[5];
	data[0] = 47;
	data[1] = 38;
	data[2] = 45;
	data[3] = 36;
	data[4] = 33;
	LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>(); 
	for(int i = 0; i < data.length; i++) {
		list.add(data[i]);
	}
	
	for(int i = 0; i < data.length; i++) {
		assertEquals(data[i], list.get(i));
	}
	
	
	}
	
	/**
	 * Test removeInt() 
	 */
	@Test 
	public void testRemoveInt() {
		String[] data = new String[4];
		data[0] = "orange";
		data[1] = "banana";
		data[2] = "apple";
		data[3] = "kiwi";
		LinkedListRecursive<String> list = new LinkedListRecursive<String>(); 
		for(int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
		
		assertEquals(list.remove(1), "banana"); 
		
	}

}
