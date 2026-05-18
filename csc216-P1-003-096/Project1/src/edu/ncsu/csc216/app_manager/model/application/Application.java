package edu.ncsu.csc216.app_manager.model.application;

import java.util.ArrayList;

import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;

/**
 * Application Class is a concrete class representing the State Pattern context. 
 * An Application keeps track of all application information including the current State. 
 * @author Diya Patel 
 */
public class Application {
	
	/** New Application */
	final public static String A_NEW = "New";
	/** Old Application */
	final public static String A_OLD = "Old"; 
	/** Hired */
	final public static String A_HIRED = "Hired"; 
	/** Application id */ 
	private int appId; 
	/** Application Summary */ 
	private String summary; 
	/** Application reviewer */ 
	private String reviewer; 
	/** Paperwork for Processing */ 
	private boolean processPaperwork; 
	/** List of notes */ 
	private ArrayList<String> notes;  
	/** state */ 
	private AppState state; 
	/** app type */ 
	private String appType; 
	/** resolution */ 
	private String resolution;  
	/** review state */ 
	final private AppState reviewState = new ReviewState(); 
	/** interview state */
	final private AppState interviewState = new InterviewState();
	/** waitlist state */ 
	final private AppState waitlistState = new WaitlistState(); 
	/** refchk state */ 
	final private AppState refchkState = new RefChkState(); 
	/** offer state */ 
	final private AppState offerState = new OfferState(); 
	/** closed state */ 
	final private AppState closedState = new ClosedState(); 
	
	/** Review Name */ 
	final public static String REVIEW_NAME = "Review"; 
	/** Interview Name */ 
	final public static String INTERVIEW_NAME = "Interview"; 
	/** RefCheck Name */ 
	final public static String REFCHK_NAME = "RefCheck"; 
	/** Offer Name */ 
	final public static String OFFER_NAME = "Offer"; 
	/** Waitlist Name */ 
	final public static String WAITLIST_NAME = "Waitlist"; 
	/** Closed Name */ 
	final public static String CLOSED_NAME = "Closed"; 
	
	/**
	 * Constructor for the Application object that consists of appId, type, summary, and reviewer. 
	 * @param id the application id 
	 * @param appType the type of application 
	 * @param summary the summary of the application 
	 * @param note the application note 
	 * @throws IllegalArgumentException if note is null or empty 
	 * @throws UnsupportedOperationException with message Invalid information if information is invalid for app type 
	 */
	public Application(int id, AppType appType, String summary, String note) {
		
		if(note == null || note.length() == 0) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		this.setAppId(id);
		if(appType == null) {
			this.setAppType(null);
		}
		switch(appType) {
			case AppType.HIRED: 
				this.setAppType(A_HIRED);
				break; 
			case AppType.OLD: 
				this.setAppType(A_OLD);
				break;
			case AppType.NEW: 
				this.setAppType(A_NEW);
				break;
			default: 
				throw new UnsupportedOperationException("Invalid information."); 
		}
		this.state = reviewState; 
		this.setSummary(summary);
		this.notes = new ArrayList<String>();
		addNote(note);  
		
	}
	 
	/**
	 * Constructor for the Application object that consists of: 
	 * @param id application id 
	 * @param state application state
	 * @param appType the application type  
	 * @param summary application summary 
	 * @param reviewer application reviewer 
	 * @param confirmed whether the application is processed or not 
	 * @param resolution the application resolution 
	 * @param notes a list of notes 
	 */
	public Application(int id, String state, String appType, String summary, String reviewer, boolean confirmed, String resolution, ArrayList<String> notes) {
		this.setAppId(id);
		this.setAppType(appType); 
		this.setSummary(summary);
		this.setNotes(notes);
		this.setReviewer(reviewer);
		this.state = reviewState; 
		this.setProcessPaperwork(confirmed);
		this.setResolution(resolution);
		this.setState(state); 
	}
	
	/**
	 * an enumeration contained in the Application class. Represents the three possible Application types of new, old and hired.
	 */
	public enum AppType { NEW, OLD, HIRED } 
	
	/**
	 * gets the application id 
	 * @return appId the current app Id
	 */
	public int getAppId() {
		return appId; 
	}

	/**
	 * sets the application id 
	 * @param appId the application id to be set to 
	 * @throws IllegalArgumentException if appId is less than 1  
	 */
	private void setAppId(int appId) {
		if(appId < 1) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		this.appId = appId;
	}
	
	/**
	 * gets the summary of the current application 
	 * @return summary the summary of the application 
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * sets the summary of the current application 
	 * @param summary the summary to set to the current application 
	 * @throws IllegalArgumentException if summary is null or empty 
	 */
	private void setSummary(String summary) {
		if(summary == null || summary.length() == 0) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		this.summary = summary;
	}

	/**
	 * gets the reviewer of the current application 
	 * @return reviewer the reviewer of the application 
	 */
	public String getReviewer() {
		return reviewer;
	}

	/**
	 * sets the reviewer to the current application 
	 * @param reviewer the reviewer to set to the current application 
	 */
	private void setReviewer(String reviewer) {
		if("".equals(reviewer)) {
			 this.reviewer = null; 
		}
		else {
			this.reviewer = reviewer;
		} 
	}

	/**
	 * shows if the application is processed or not 
	 * @return processPaperwork , which is true or false depending on if the application is not processed 
	 */
	public boolean isProcessed() {
		return processPaperwork;     
	}

	/**
	 * sets the proccessed paperwork 
	 * @param confirmed when the paperwork is confirmed 
	 */
	private void setProcessPaperwork(boolean confirmed) {
		this.processPaperwork = confirmed;
	}

	/**
	 * gets the list of notes for the current application 
	 * @return this.notes the list of current notes 
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * sets the list of notes for the current application 
	 * @param notes the notes to set
	 * @throws IllegalArgumentException if notes is null 
	 */
	private void setNotes(ArrayList<String> notes) {
		if(notes == null) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		this.notes = notes;
	}
	
	/**
	 * gets the resolution of the current application 
	 * @return resolution the resolution of the application 
	 */
	public String getResolution() {
		return resolution; 
	}
	
	/**
	 * sets the resolution of the current application 
	 * @param resolution the resolution to be set 
	 * @throws IllegalArgumentException if there is an invalid resolution for the specific application  
	 */
	private void setResolution(String resolution) {
		if(resolution == null && (this.getStateName().equals(CLOSED_NAME) || this.getStateName().equals(WAITLIST_NAME)) || this.getAppType().equals(A_NEW) && resolution != null && resolution.equals(Command.R_INTCOMPLETED)) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}

		if(!this.getStateName().equals(REVIEW_NAME) && !this.appType.equals(A_OLD)) {
			if(isValidResolution(resolution)) {
				this.resolution = resolution; 
			}
			else {
				throw new IllegalArgumentException("Application cannot be constructed.");
			}
		}
		else {
			if("".equals(resolution)) {
				this.resolution = null; 
			}
			else {
				this.resolution = resolution; 
			}
		}
		
	}
	
	/**
	 * checks if resolution is a valid value 
	 * @param resolution to check if is within enum 
	 * @return false if not a valid resolution 
	 */
	private boolean isValidResolution(String resolution) {
		return resolution == null || resolution.equals(Command.R_INTCOMPLETED) || resolution.equals(Command.R_OFFERCOMPLETED) || resolution.equals(Command.R_REFCHKCOMPLETED) || resolution.equals(Command.R_REVCOMPLETED);
	
	}


	/**
	 * sets the type of the current application 
	 * @param type the application type to set to 
	 * @throws IllegalArgumentException if app type is null or empty or if it is not old, new, or hired
	 */
	private void setAppType(String type){
		
		if(type == null || type.length() == 0 || !(type.toUpperCase().equals(AppType.OLD.toString()) || type.toUpperCase().equals(AppType.NEW.toString()) || type.toUpperCase().equals(AppType.HIRED.toString()))) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		try {
			this.appType = type;       
		} catch(Exception e){
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		
		
	}
	
	/**
	 * gets the current application type 
	 * @return appType the application type 
	 */
	public String getAppType() {
		return appType; 
	}
	
	/**
	 * sets the state of the application 
	 * @param state the state to set the application to 
	 * @throws IllegalArgumentException if application is invalid and cannot be constructed  
	 */
	private void setState(String state) {
		if(state == null || state.length() == 0) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		if(!state.equals(REVIEW_NAME) && !state.equals(INTERVIEW_NAME) && !state.equals(OFFER_NAME) && !state.equals(WAITLIST_NAME) && !state.equals(REFCHK_NAME) && !state.equals(CLOSED_NAME) || this.getNotes().size() == 0) {
			throw new IllegalArgumentException("Application cannot be constructed."); 
		}
		if(state.equals(reviewState.getStateName())) {
			if(this.getAppType().equals(A_OLD) && this.getResolution() != null || this.processPaperwork || this.getNotes().size() == 0 ) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = reviewState; 
		}
		else if(state.equals(interviewState.getStateName())) {
			if(this.getResolution() != null || this.getReviewer() == null || this.getAppType().equals(A_NEW) || this.processPaperwork || this.getNotes().size() == 0) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = interviewState; 
		}
		else if(state.equals(refchkState.getStateName())) {
			if(this.getResolution() != null && this.getResolution().equals(Command.R_REFCHKCOMPLETED) || this.getReviewer() == null || this.getAppType().equals(A_NEW) || !this.isProcessed() || this.getNotes().size() == 0) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = refchkState; 
		}
		else if(state.equals(offerState.getStateName())) {
			if(this.getResolution() != null && this.getResolution().equals(Command.R_OFFERCOMPLETED) || this.getReviewer() == null || this.getAppType().equals(A_NEW) || this.getNotes().size() == 0) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = offerState; 
		}
		else if(state.equals(closedState.getStateName())) {
			if(this.isProcessed() && this.getAppType().equals(A_NEW) || this.getResolution() == null || this.getNotes().size() == 0) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = closedState; 
		}
		else if(state.equals(waitlistState.getStateName())) {
			if(this.getReviewer() == null && this.getAppType().equals(A_OLD) || this.isProcessed() && this.getAppType().equals(A_NEW) || this.getResolution() == null || !(this.getResolution().equals(Command.R_INTCOMPLETED) || this.getResolution().equals(Command.R_REVCOMPLETED))) {
				throw new IllegalArgumentException("Application cannot be constructed."); 
			}
			this.state = waitlistState; 
		}
		
		 
	}
	
	/**
	 * gets the state name of the current application 
	 * @return state the state name of the application 
	 */
	public String getStateName() {
		return state.getStateName(); 
	}
	
	/**
	 * gets the string for the notes 
	 * @return notesString the string of notes 
	 */
	public String getNotesString() {
		String notesString = ""; 
		for(int i = 0; i < notes.size(); i++) {
			notesString += "-" + notes.get(i) + "\n"; 
		}
		return notesString; 
	}
	
	/**
	 * adds the note to the list of notes 
	 * @param note the note to add 
	 * @throws IllegalArgumentException if note is null or empty 
	 */
	private void addNote(String note) {
		if(note == null || note.length() == 0) {
			throw new IllegalArgumentException("Unable to add note."); 
		}
		String newNote = "[" + getStateName() + "] " + note; 
		notes.add(newNote); 
	}
	
	/**
	 * return a string of values of the application 
	 * @return the string of values within the application 
	 */
	@Override
	public String toString() {
		String appResolution = getResolution();
		if(appResolution == null) {
			appResolution = ""; 
		}
		
		return "*" + getAppId() + "," + getStateName() + "," + getAppType() + "," + getSummary() + "," + getReviewer()  + "," + isProcessed() + "," + appResolution + "\n" + getNotesString();
	}

	/**
	 * updates the command given 
	 * @param command the command given
	 * @throws UnsupportedOperationException will be thrown if command is invalid state  
	 */
	public void update(Command command) {
		 state.updateState(command); 
	}

	
	/**
	 * Interface that describes behavior of concrete AppState classes for the Application Manager FSM.
	 */
	public interface AppState {
		
		/**
		 * updates the state of the application 
		 * @param command the command that will update the state 
		 * @throws UnsupportedOperationException will be thrown if command is invalid state 
		 */
		void updateState(Command command);
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		String getStateName();
	}
	
	
	
	/**
	 * Concrete class that represents the Review state of the Application Manager FSM.
	 */
	public class ReviewState implements AppState {
		/** setting state name as review state */ 
		String stateName = REVIEW_NAME;
		
		/**
		 * Review State Constructor 
		 */
		private ReviewState() {
			
		}
		
		/**
		 * updates the state of the application when in review state  
		 * @param command the command that will update the state  
		 * @throws UnsupportedOperationException will be thrown if command is invalid state 
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.ACCEPT && command.getReviewerId() != null) {
				state = interviewState;
				setReviewer(command.getReviewerId());
				setAppType(Application.A_OLD); 
			}
			else if (command.getCommand() == CommandValue.REJECT) {
				state = closedState;
				setResolution(Command.R_REVCOMPLETED);
				setReviewer(command.getReviewerId()); 
			}
			else if (command.getCommand() == CommandValue.STANDBY && command.getResolution() != null && !getAppType().equals(Application.A_OLD)) {
				state = waitlistState;
				setResolution(Command.R_REVCOMPLETED);
				setReviewer(command.getReviewerId());
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			}
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}



		
		
	}
	
	/**
	 * Concrete class that represents the Interview state of the Application Manager FSM.
	 */
	public class InterviewState implements AppState {
		/** interview state name */ 
		String stateName = INTERVIEW_NAME;
		
		/**
		 * Interview State Constructor 
		 */
		private InterviewState() {
			
		}
		
		/**
		 * updates the state of the application when in interview state  
		 * @param command the command that will update the state  
		 * @throws UnsupportedOperationException will be thrown if command is invalid state  
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.ACCEPT && command.getReviewerId() != null) {
				state = refchkState;
				setReviewer(command.getReviewerId());
				setProcessPaperwork(true);
			}
			else if (command.getCommand() == CommandValue.REJECT) {
				state = closedState;
				setResolution(Command.R_INTCOMPLETED);
				setReviewer(command.getReviewerId()); 
			}
			else if (command.getCommand() == CommandValue.STANDBY) {
				state = waitlistState;
				setReviewer(command.getReviewerId());
				setResolution(Command.R_INTCOMPLETED);
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			}
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}
		
	}
	
	/**
	 * Concrete class that represents the Waitlist state of the Application Manager FSM.
	 */
	public class WaitlistState implements AppState {
		/** waitlist state */ 
		String stateName = WAITLIST_NAME;
		/**
		 * Waitlist State Constructor 
		 */
		private WaitlistState() {
			
		}
		
		/**
		 * updates the state of the application when in waitlist state  
		 * @param command the command that will update the state 
		 * @throws UnsupportedOperationException will be thrown if command is invalid state   
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.REOPEN && getResolution().equals(Command.R_INTCOMPLETED)) {
				state = refchkState;
				setReviewer(command.getReviewerId());
				setProcessPaperwork(true);
				setResolution(null); 
			}
			else if (command.getCommand() == CommandValue.REOPEN && getResolution().equals(Command.R_REVCOMPLETED) && getAppType().equals(A_NEW)) {
				state = reviewState;
				setAppType(A_OLD);
				setResolution(null); 
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			}
			
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}
		
	}
	
	/**
	 * Concrete class that represents the Reference Check state of the Application Manager FSM.
	 */
	public class RefChkState implements AppState {
		/** state for refchk */ 
		String stateName = REFCHK_NAME;
		/**
		 * RefChk State Constructor 
		 */
		private RefChkState() {
			
		}
		
		/**
		 * updates the state of the application when in ref check state  
		 * @param command the command that will update the state  
		 * @throws UnsupportedOperationException will be thrown if command is invalid state  
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.ACCEPT) {
				state = offerState;
				setReviewer(command.getReviewerId());
				setProcessPaperwork(true);
			}
			else if (command.getCommand() == CommandValue.REJECT) {
				state = closedState;
				setReviewer(command.getReviewerId());
				setResolution(Command.R_REFCHKCOMPLETED); 
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			}
			
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}
		
	}
	
	/**
	 * Concrete class that represents the Offer state of the Application Manager FSM.
	 */
	public class OfferState implements AppState {
		/** offer state name */ 
		String stateName = OFFER_NAME;
		/**
		 * Offer State Constructor 
		 */
		private OfferState() {
			
		}
		
		/**
		 * updates the state of the application when in offer state  
		 * @param command the command that will update the state 
		 * @throws UnsupportedOperationException will be thrown if command is invalid state  
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.ACCEPT) {
				state = closedState;
				setResolution(Command.R_OFFERCOMPLETED); 
				setProcessPaperwork(true);
				setReviewer(command.getReviewerId());
				setAppType(A_HIRED); 
			}
			else if (command.getCommand() == CommandValue.REJECT) {
				state = closedState;
				setResolution(Command.R_OFFERCOMPLETED); 
				setReviewer(command.getReviewerId());
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			} 
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return null the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}
		
	}
	
	/**
	 * Concrete class that represents the Closed state of the Application Manager FSM.
	 */
	public class ClosedState implements AppState {
		/** closed state */ 
		String stateName = CLOSED_NAME;
		/**
		 * Closed State Constructor 
		 */
		private ClosedState() { 
			
		}
		
		/**
		 * updates the state of the application when in closed state  
		 * @param command the command that will update the state 
		 * @throws UnsupportedOperationException will be thrown if command is invalid state   
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.REOPEN && getResolution().equals(Command.R_REVCOMPLETED) && appType.equals(A_NEW)) {
				setAppType(A_OLD); 
				state = reviewState; 
				setResolution(null); 
			}
			else {
				throw new UnsupportedOperationException("Invalid information.");
			} 
			addNote(command.getNote());
		}
		
		/**
		 * gets the state name of the application 
		 * @return stateName the state of the application 
		 */
		public String getStateName() {
			return stateName; 
		}
		
	}
	

}