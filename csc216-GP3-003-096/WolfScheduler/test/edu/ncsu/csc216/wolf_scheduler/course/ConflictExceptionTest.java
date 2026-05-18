/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the ConflictException Class 
 * @author Diya Patel 
 */
class ConflictExceptionTest {

	/**
	 * sets up throw exception 
	 * @throws java.lang.Exception thrown 
	 */   
	@BeforeEach
	void setUp() throws Exception {
		//set up before tests    
	}

	/**
	 * Test method for ConflictException(String message) which passed the message to the parent class. 
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for ConflictException() which will call the other constructor. 
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
