package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

/**
 * JUnit Test Class for ArrayStack 
 * @author Diya Patel 
 * @author Preeti Joshi 
 * @author Ram 
 */
class ArrayStackTest {

	/**
	 * Test for ArrayStack constructor 
	 */
	@Test
	public void testArrayStackandSetCapacity() {
		ArrayStack<String> stack = new ArrayStack<String>(10); 
		assertEquals(0, stack.size()); 
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1)); 
	}
	
	/**
	 * Test for ArrayStack.push() and ArrayStack.pop()
	 */
	@Test
	public void testPushandPop() {
		ArrayStack<String> stack = new ArrayStack<String>(5); 
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
