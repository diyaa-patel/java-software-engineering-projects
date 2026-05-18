package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class which extends activity and creates an event which has 
 * a title, meeting days, start time, end time, and event details
 * implements getShortDisplay and getLongDisplay 
 * @author Diya Patel 
 */
public class Event extends Activity {
	
	/** The details for the event */ 
	private String eventDetails; 
	
	/**
	 * Event Class Constructor 
	 * @param title the title of the event 
	 * @param meetingDays the days the event takes place 
	 * @param startTime the start time of the event 
	 * @param endTime the end time of the event 
	 * @param eventDetails the details for the event 
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
        super(title, meetingDays, startTime, endTime);
        setEventDetails(eventDetails);
    }

	/**
	 * returns the short display 
	 * @return shortDisplay a array contains two empty cells and one with the title and another with the meetingString.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String [] shortDisplay = new String[4]; 
		shortDisplay[0] = ""; 
		shortDisplay[1] = ""; 
		shortDisplay[2] = getTitle(); 
		shortDisplay[3] = getMeetingString(); 
		
		return shortDisplay; 
	}

	/**
	 * returns the long display 
	 * @return longDisplay an array containing four empty cells 
	 * a title, meeting string, and event details 
	 */
	@Override
	public String[] getLongDisplayArray() {
		String [] longDisplay = new String[7]; 
		longDisplay[0] = ""; 
		longDisplay[1] = ""; 
		longDisplay[2] = getTitle(); 
		longDisplay[3] = ""; 
		longDisplay[4] = ""; 
		longDisplay[5] = getMeetingString(); 
		longDisplay[6] = getEventDetails(); 
		
		return longDisplay;
	}

	/**
	 * toString method called in super class s
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getEventDetails();
	}

	/**
	 * Gets the event details 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the event details 
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if eventDetails is null 
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details."); 
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * compares two courses to see if they are duplicates 
	 * @param activity the activity we are seeing if there are duplicates of
	 * @return true if the two activities have the same name 
	 * @throws IllegalArgumentException if the activity is not an event. 
	 */
	public boolean isDuplicate(Activity activity) {
		if(!(activity instanceof Event)){
			throw new IllegalArgumentException("Activity is not a Event.");
		}         
		return getTitle().equals(activity.getTitle());
	}


}
