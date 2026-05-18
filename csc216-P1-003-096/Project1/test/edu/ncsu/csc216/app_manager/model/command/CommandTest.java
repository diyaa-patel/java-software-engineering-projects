package edu.ncsu.csc216.app_manager.model.command;

 

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;


class CommandTest {
	
	/** note */ 
	private static final String NOTE = "note 1"; 
	/** reviewer Id */ 
	private static final String REVIEWERID = "203"; 
	/** resolution */ 
	private static final Resolution RESOLUTION = Resolution.REVCOMPLETED; 
	/** command */ 
	private static final CommandValue COMMAND = CommandValue.ACCEPT; 
	

	/**
	 * set up before the test cases 
	 * @throws Exception if there is an exception to throw 
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//to implement 
	}

	/**
	 * Tests the Command Constructor 
	 */
	@Test
	public void testCommand() {
		// Test a valid construction
				Command c = assertDoesNotThrow(
						() -> new Command(COMMAND, REVIEWERID, RESOLUTION, NOTE),
						"Should not throw exception"); 
				
				assertAll("Course", 
						() -> assertEquals(COMMAND, c.getCommand(), "incorrect command"), 
						() -> assertEquals(REVIEWERID, c.getReviewerId(), "incorrect reviewerId"),
						() -> assertEquals(RESOLUTION, c.getResolution(), "incorrect resolution"), 
						() -> assertEquals(NOTE, c.getNote(), "incorrect note"));
	}

	/**
	 * Tests Command.getCommand() 
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(COMMAND, REVIEWERID, RESOLUTION, NOTE); 
		assertEquals(CommandValue.ACCEPT, c.getCommand());
	}

	/**
	 * Tests Command.getReviwerId() 
	 */
	@Test
	public void testGetReviewerId() {
		Command c = new Command(COMMAND, REVIEWERID, RESOLUTION, NOTE);
		assertEquals("203", c.getReviewerId());
	}

	/**
	 * Tests Command.getResolution() 
	 */
	@Test
	public void testGetResolution() {
		Command c = new Command(COMMAND, REVIEWERID, RESOLUTION, NOTE); 
		assertEquals(Resolution.REVCOMPLETED, c.getResolution());
	}

	/**
	 * Tests Command.getNote() 
	 */
	@Test
	public void testGetNote() {
		Command c = new Command(COMMAND, REVIEWERID, RESOLUTION, NOTE); 
		assertEquals("note 1", c.getNote());
	}

}  