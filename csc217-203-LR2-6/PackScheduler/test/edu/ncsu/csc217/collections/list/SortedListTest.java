package edu.ncsu.csc217.collections.list;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * Sorted List Test class which tests the sorted list class 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
public class SortedListTest {

	/**
	 * Tests the sorted list 
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("apple"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("apple"));
		
		list.add("banana"); 
		assertEquals(2, list.size());
		assertTrue(list.contains("banana"));
		
		list.add("kiwi"); 
		assertEquals(3, list.size());
		assertTrue(list.contains("kiwi"));
		
		list.add("mango"); 
		assertEquals(4, list.size());
		assertTrue(list.contains("mango"));
		
		list.add("pineapple"); 
		assertEquals(5, list.size());
		assertTrue(list.contains("pineapple"));
		
		list.add("grapes"); 
		assertEquals(6, list.size());
		assertTrue(list.contains("grapes"));
		
		list.add("blueberry"); 
		assertEquals(7, list.size());
		assertTrue(list.contains("blueberry"));
		
		list.add("raspberry"); 
		assertEquals(8, list.size());
		assertTrue(list.contains("raspberry"));
		
		list.add("passionfruit"); 
		assertEquals(9, list.size());
		assertTrue(list.contains("passionfruit"));
		
		list.add("guava"); 
		assertEquals(10, list.size());
		assertTrue(list.contains("guava"));
		
		list.add("dragonfruit"); 
		assertEquals(11, list.size());
		assertTrue(list.contains("dragonfruit"));
		
		list.add("orange"); 
		assertEquals(12, list.size());
		assertTrue(list.contains("orange"));
		
		//Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		
	}

	/**
	 * Tests the add method 
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		
		//Test adding to the front, middle and back of the list
		//Test adding to the front of the list 
		list.add("apple"); 
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0)); 
		
		//Test adding to the last of the list 
		list.add("pineapple"); 
	    assertEquals(3, list.size());
		assertEquals("pineapple", list.get(2));
		
		//adds to the middle of the list 
		list.add("guava"); 
		assertEquals(4, list.size());
		assertEquals("guava", list.get(2)); 
		
		//Test adding a null element
		Exception e3 = assertThrows(NullPointerException.class,
				() -> list.add(null));
				assertEquals(null, e3.getMessage());
				
		//Test adding a duplicate element
	    Exception e4 = assertThrows(IllegalArgumentException.class,
						() -> list.add("banana"));
				   assertEquals("Element already in list.", e4.getMessage());
    
	}
	
	/**
	 * Tests the get method 
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		Exception e2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
				assertEquals(null, e2.getMessage());
		
		//Add some elements to the list
		list.add("banana"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("banana"));
		
		list.add("kiwi"); 
		assertEquals(2, list.size());
		assertTrue(list.contains("kiwi"));
		
		list.add("mango"); 
		assertEquals(3, list.size());
		assertTrue(list.contains("mango"));
		
		//Test getting an element at an index < 0
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
				assertEquals(null, e1.getMessage());
		
		//Test getting an element at size
		Exception e3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
				assertEquals(null, e3.getMessage());

	}
	
	/**
	 * Tests the remove method 
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TTest removing from an empty list
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
				assertEquals(null, e1.getMessage());
		
		//Add some elements to the list - at least 4
		list.add("banana"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("banana"));
		
		list.add("kiwi"); 
		assertEquals(2, list.size());
		assertTrue(list.contains("kiwi"));
		
		list.add("mango"); 
		assertEquals(3, list.size());
		assertTrue(list.contains("mango"));
		
		list.add("pineapple"); 
		assertEquals(4, list.size());
		assertTrue(list.contains("pineapple"));
		
		//Test removing an element at an index < 0
		
		Exception e2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
				assertEquals(null, e2.getMessage());
		
		//Test removing an element at size
		Exception e3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
				assertEquals(null, e3.getMessage());

		
		//Test removing a middle element
		assertEquals("mango", list.remove(list.size() / 2)); 
		assertEquals(3, list.size());
		
		//Test removing the last element
		assertEquals("pineapple", list.remove(list.size() - 1)); 
		assertEquals(2, list.size());
		
		//Test removing the first element
		assertEquals("banana", list.remove(0)); 
		assertEquals(1, list.size());
		
		//Test removing the last element
		assertEquals("kiwi", list.remove(0));
		assertEquals(0, list.size());
	}
	
	/**
	 * tests the index of method 
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(0, list.size()); 
		
		Exception e3 = assertThrows(NullPointerException.class,
				() -> assertEquals(0, list.indexOf(null))); 
				assertEquals(null, e3.getMessage());
		
		 
		
		
		//Add some elements
		list.add("banana"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("banana"));
		
		list.add("kiwi"); 
		assertEquals(2, list.size());
		assertTrue(list.contains("kiwi"));
		
		list.add("mango"); 
		assertEquals(3, list.size());
		assertTrue(list.contains("mango"));
		
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		
		assertEquals(0, list.indexOf("banana")); 
		assertEquals(1, list.indexOf("kiwi")); 
		assertEquals(2, list.indexOf("mango")); 
		
		assertNotEquals(0, list.indexOf("pineapple")); 
				
		assertNotEquals(1, list.indexOf("grapes")); 
				


		
		//Test checking the index of null
		Exception e2 = assertThrows(NullPointerException.class,
				() -> assertEquals(2, list.indexOf(null))); 
				assertEquals(null, e2.getMessage());
		
	}
	
	/**
	 * Tests clear method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("banana"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("banana"));
		
		list.add("kiwi"); 
		assertEquals(2, list.size());
		assertTrue(list.contains("kiwi"));
		
		list.add("mango"); 
		assertEquals(3, list.size());
		assertTrue(list.contains("mango"));
		
		list.add("pineapple"); 
		assertEquals(4, list.size());
		assertTrue(list.contains("pineapple"));
		
		//TClear the list
		list.clear();
		
		//Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests the isEmpty() method 
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertEquals(0, list.size());
		
		//Add at least one element
		list.add("pineapple"); 
		assertEquals(1, list.size());
		assertTrue(list.contains("pineapple"));

		
		//check that the list is no longer empty
		assertNotEquals(0, list.size()); 
		
	}

	/**
	 * Tests contains() method 
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TTest the empty list case
		assertEquals(0, list.size());
		
		//Add some elements
		list.add("banana"); 
		assertEquals(1, list.size());
		
		list.add("kiwi"); 
		assertEquals(2, list.size());
		
		list.add("mango"); 
		assertEquals(3, list.size());
		
		list.add("pineapple"); 
		assertEquals(4, list.size());
		
		//Test some true and false cases
		assertTrue(list.contains("pineapple"));
		assertTrue(list.contains("mango"));
		assertTrue(list.contains("kiwi"));
		assertTrue(list.contains("banana")); 
		assertNotEquals(2, list.indexOf("pineapple"));
		assertNotEquals(2, list.indexOf("kiwi"));
		assertNotEquals(2, list.indexOf("banana"));
		assertEquals(3, list.indexOf("pineapple"));
		assertEquals(2, list.indexOf("mango"));
		assertEquals(0, list.indexOf("banana"));
		assertFalse(list.contains("blueberry"));
		assertFalse(list.contains("dragon fruit"));
		assertFalse(list.contains("passionfruit"));
	}
	
	/**
	 * Tests the Equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("mango"); 
		list1.add("banana"); 
		list1.add("kiwi"); 
		list1.add("orange");
		
		list2.add("mango"); 
		list2.add("banana"); 
		list2.add("kiwi"); 
		list2.add("orange"); 
		
		list3.add("passionfruit"); 
		list3.add("raspberry"); 
		list3.add("apple"); 
		list3.add("lemon");
		
		//Test for equality and non-equality
		assertEquals(list2, list1);
		assertNotEquals(list3, list1);
		assertNotEquals(list3, list1); 
		
	}
	
	/**
	 * Tests hash code 
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("mango"); 
		list1.add("banana"); 
		list1.add("kiwi"); 
		list1.add("orange"); 
		
		list2.add("mango"); 
		list2.add("banana"); 
		list2.add("kiwi"); 
		list2.add("orange"); 
		
		list3.add("passionfruit"); 
		list3.add("raspberry"); 
		list3.add("apple"); 
		list3.add("lemon");
		
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 