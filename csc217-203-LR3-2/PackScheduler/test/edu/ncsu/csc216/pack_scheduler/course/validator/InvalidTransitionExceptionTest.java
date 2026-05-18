/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the InvalidTransitionException Class 
 * @author Diya Patel 
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * sets up throw exception 
	 * @throws java.lang.Exception thrown 
	 */   
	@BeforeEach
	void setUp() throws Exception {
		//set up before tests    
	}

	/**
	 * Test method for InvalidTransitionException(String message) which passed the message to the parent class. 
	 */
	@Test
	public void testInvalidTransitionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for InvalidTransitionException() which will call the other constructor. 
	 */
	@Test
	void testInvalidTransititionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

}

