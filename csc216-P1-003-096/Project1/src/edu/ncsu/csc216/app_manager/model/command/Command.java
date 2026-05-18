package edu.ncsu.csc216.app_manager.model.command;
/**
 * Command class which has two enumerated classes 
 * CommandValue and Resolutions 
 * Encapsulates the informater about a user command that would lead to a transition. 
 * @author Diya Patel 
 */
public class Command {
	
	/** Review Completed */ 
	static public final String R_REVCOMPLETED = "ReviewCompleted";
	/** Interview Completed */
	static public final String R_INTCOMPLETED = "InterviewCompleted"; 
	/** Reference Check Completed */
	static public final String R_REFCHKCOMPLETED = "ReferenceCheckCompleted"; 
	/** Offer Completed */
	static public final String R_OFFERCOMPLETED = "OfferCompleted"; 
	/** reviewer id */ 
	private String reviewerId; 
	/** note */
	private String note; 
	/** Command Value */ 
	private CommandValue command; 
	/** Resolution */ 
	private Resolution resolution; 
	
	/**
	 * Represents one of the four possible commands that a user can make for the Application Manager FSM.
	 */
	public enum CommandValue { ACCEPT, REJECT, STANDBY, REOPEN }
	
	/** 
	 * Represents one of the four possible ways a user can resolve an application. 
	 */
	public enum Resolution { REVCOMPLETED, INTCOMPLETED, REFCHKCOMPLETED, OFFERCOMPLETED }
	
	/**
	 * Command Constructor which passes the following: 
	 * @param command the command to do 
	 * @param reviewerId the reviewer id 
	 * @param resolution the application resolution 
	 * @param note the application note 
	 * @throws IllegalArgumentException if note is null or empty, command is null 
	 * command is ACCEPT and there is a empty or null reviwer id 
	 * or if command is STANDBY/REJECT and there is a null resolution 
	 */
	public Command(CommandValue command, String reviewerId, Resolution resolution, String note) {
		
		if(note == null || note.length() == 0 || command == null) {
			throw new IllegalArgumentException("Invalid information."); 
		}
		else if(command == CommandValue.ACCEPT && (reviewerId == null || reviewerId.length() == 0)) {
			throw new IllegalArgumentException("Invalid information."); 
		}
		else if((command == CommandValue.REJECT || command == CommandValue.STANDBY) && resolution == null) {
			throw new IllegalArgumentException("Invalid information."); 
		}
		
		this.note = note; 
		this.reviewerId = reviewerId; 
		this.resolution = resolution; 
		this.command = command; 
	}
	
	/**
	 * returns the current command 
	 * @return command the current command 
	 */
	public CommandValue getCommand(){
		return command; 
	}
	
	/**
	 * returns the current reviwer id. 
	 * @return reviwerId the current reviewerId 
	 */
	public String getReviewerId() {
		return reviewerId; 
	}
	
	/**
	 * returns the resolution 
	 * @return resolution the current resolution 
	 */
	public Resolution getResolution(){
		return resolution; 
	}
	
	/**
	 * returns the note 
	 * @return note the current note 
	 */
	public String getNote() {
		return note; 
	}
	
	
	
}