package edu.ncsu.csc216.pack_scheduler.util;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedStackTest {

	/**
	 * set up for test cases
	 * @throws Exception if there is an exception to be thrown 
	 */
	@BeforeEach
	void setUp() throws Exception {
		//set up for test cases 
	}

	/**
	 * Test for LinkedStack constructor 
	 */
	@Test
	public void testArrayStackandSetCapacity() {
		LinkedStack<String> stack = new LinkedStack<String>(10); 
		assertEquals(0, stack.size()); 
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1)); 
	}
	
	/**
	 * Test for LinkedStack.push() and LinkedStack.pop()
	 */
	@Test
	public void testPushandPop() {
		LinkedStack<String> stack = new LinkedStack<String>(5); 
		assertEquals(0, stack.size()); 
		assertThrows(EmptyStackException.class, () -> stack.pop());
		stack.push("1"); 
		stack.push("2");
		stack.push("4"); 
		stack.push("3");
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(2)); 
		assertThrows(NullPointerException.class, () -> stack.push(null));
		stack.push("5");
		assertThrows(IllegalArgumentException.class, () -> stack.push("6")); 
		assertEquals(5, stack.size()); 
		assertEquals("5", stack.pop()); 
		assertEquals("3", stack.pop()); 
		assertEquals("4", stack.pop()); 
		assertEquals("2", stack.pop()); 
		assertEquals("1", stack.pop()); 
		assertEquals(0, stack.size()); 
		assertThrows(EmptyStackException.class, () -> stack.pop()); 
	}

}
