package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException Class which creates a new Exception called ConflictException  
 * has two constructors one which will have a default message for the exception and other has a string message passed through 
 * @author Diya Patel 
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterized constructor with a String specifying a message for the Exception object. 
	 * The message is passed to the parent constructor. 
	 * @param message the message that will be passed to the parent constructor 
	 */
	public ConflictException(String message) {
		super(message); 
	}
	
	/**
	 * Calls the other constructor with the default message 
	 */
	public ConflictException() {
		 this("Schedule conflict.");           
	}
 

}
