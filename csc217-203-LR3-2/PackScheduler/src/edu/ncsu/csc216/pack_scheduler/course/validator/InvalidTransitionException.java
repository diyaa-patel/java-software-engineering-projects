package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checked Exception (Invalid Transition Exception)
 * if the transition in FSM for checking Course Name is invalid 
 */
 public class InvalidTransitionException extends Exception {

   /**
    * Default_Message static field
    * 
    */
   private static final String DEFAULT_MESSAGE = "Invalid FSM Transition.";
    
    /** ID used for serialization */
    private static final long serialVersionUID = 1L;

    /**
     * Default message for exception 
     */
    public InvalidTransitionException() {
        super(DEFAULT_MESSAGE);
    }

    
    /**
     * message with exception 
     * @param message the message to print when exception is thrown 
     */
    public InvalidTransitionException(String message) {
        super(message);
    }
}