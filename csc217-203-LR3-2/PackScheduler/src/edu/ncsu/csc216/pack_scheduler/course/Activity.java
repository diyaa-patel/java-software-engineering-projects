package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Activity Class which is the super class of Course and Event and 
 * checks conflicts and can get and set the title of an course, meeting days and time of a course, and meeting string 
 * it also has abstract methods for short and long displays as well as if the activity is a dulpicate
 * it also has the equals method to test if two activities are equal.   
 * @author Diya Patel 
 */
public abstract class Activity implements Conflict {

	/**
	 * checks conflict for activities 
	 * @throws ConflictException if there is a conflict with any of the activities  
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameDay = false; 
		String meeting1 = this.getMeetingDays(); 
 		String meeting2 = possibleConflictingActivity.getMeetingDays(); 
			for(int i = 0; i < meeting1.length(); i++) {
				for(int j = 0; j < meeting2.length(); j++) {
					if(meeting2.charAt(j) == meeting1.charAt(i)) {
						sameDay = true; 
					}	
				}
			}
		
		if(sameDay && !"A".equals(this.getMeetingDays()) && !"A".equals(possibleConflictingActivity.getMeetingDays())){
			if(this.startTime == possibleConflictingActivity.getEndTime() || this.endTime == possibleConflictingActivity.getStartTime() || this.startTime == possibleConflictingActivity.getStartTime() ||  this.endTime == possibleConflictingActivity.getEndTime()) {
				throw new ConflictException();             
			}
			//if startTime is within the time 
			if(this.startTime < possibleConflictingActivity.getStartTime() && this.endTime > possibleConflictingActivity.getStartTime()) {
				throw new ConflictException();  
			}
			//if endTime is within the time
			if(this.endTime > possibleConflictingActivity.getEndTime() && this.startTime < possibleConflictingActivity.getEndTime()){
				throw new ConflictException();
			}
			
			//if this StartTime is between the other time 
			if(this.startTime > possibleConflictingActivity.getStartTime() && this.startTime < possibleConflictingActivity.getEndTime()){
				throw new ConflictException();
			}
			//if this endTime is between the other time 
			if(this.endTime > possibleConflictingActivity.getStartTime() && this.endTime < possibleConflictingActivity.getEndTime()){
				throw new ConflictException();
			}
		}
	}

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** upper hour limit */
	private final int upperHour = 23;
	/** upper minute limit */
	private final int upperMinute = 59;

	/**
	 * Activity Constructor which contains title, meetingDays, startTime, and endTime
	 * @param title the title of the activity 
	 * @param meetingDays the days the activity takes place 
	 * @param startTime the start time for the activity 
	 * @param endTime the end time for the activity 
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super(); 
		setTitle(title); 
		setMeetingDaysAndTime(meetingDays, startTime, endTime); 
	}

	/**
	 * Returns the Course title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course title.
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty 
	 */
	public void setTitle(String title) {
		// Conditional 1:
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
	
		// Conditional 2:
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * The method sets the meeting days and time for the course
	 * @param meetingDays the days where the course is taking place
	 * @param startTime the time the course starts
	 * @param endTime the time the course ends 
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime is null. 
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0 ) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		} else { // not arranged
			int mon = 0;
			int tues = 0;
			int weds  = 0;
			int thurs = 0;
			int fri = 0;
			int sat = 0; 
			int sun = 0; 
			for (char c : meetingDays.toCharArray()) {
				if (c == 'M') {
					mon++;
				} else if (c == 'T') {
					tues++;
				} else if (c == 'W') {
					weds++;
				} else if (c == 'H') {
					thurs++;
				} else if (c == 'F') {
					fri++;
				} else if(c == 'S') {
					sat++; 
				} else if(c == 'U') {
					sun++; 
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
	
			}
	
			if (mon > 1 || tues > 1 || weds > 1 || thurs > 1 || fri > 1 || sat > 1 || sun > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
	
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		int startHour = startTime / 100;
		int startMin = startTime - startHour * 100;
		int endHour = endTime / 100;
		int endMin = endTime - endHour * 100;
	
		if (startMin >= 60 || endMin >= 60) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//Checking if hour is valid --> within the 24 hr system 
		if(startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (!(startHour >= 0 && startHour <= upperHour)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (!(startMin >= 0 && startMin <= upperMinute)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (!(endHour >= 0 && endHour <= upperHour)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (!(endMin >= 0 && endMin <= upperMinute)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * returns a string of "DD Start-End"
	 * @return meetingString a string with the days being met and the time
	 */
	public String getMeetingString() {
	
		int startHour = getStartTime() / 100;
		int startMin = getStartTime() - startHour * 100;
		int endHour = getEndTime() / 100;
		int endMin = getEndTime() - endHour * 100;
		String meetingString = getMeetingDays();
	
		if (startHour >= 12) {
			if (startHour > 12) {
				startHour = startHour - 12;
			}
			if (startMin < 10) {
				meetingString = meetingString + " " + String.valueOf(startHour) + ":0" + String.valueOf(startMin)
						+ "PM-";
			} else {
				meetingString = meetingString + " " + String.valueOf(startHour) + ":" + String.valueOf(startMin)
						+ "PM-";
			}
		} else {
			if (startMin < 10) {
				meetingString = meetingString + " " + String.valueOf(startHour) + ":0" + String.valueOf(startMin)
						+ "AM-";
			} else {
				meetingString = meetingString + " " + String.valueOf(startHour) + ":" + String.valueOf(startMin)
						+ "AM-";
			}
		}
	
		if (endHour >= 12) {
			if (endHour > 12) {
				endHour = endHour - 12;
			}
			if (endMin < 10) {
				meetingString = meetingString + String.valueOf(endHour) + ":0" + String.valueOf(endMin) + "PM";
			} else {
				meetingString = meetingString + String.valueOf(endHour) + ":" + String.valueOf(endMin) + "PM";
			}
		} else {
			if (endMin < 10) {
				meetingString = meetingString + String.valueOf(endHour) + ":0" + String.valueOf(endMin) + "AM";
			} else {
				meetingString = meetingString + String.valueOf(endHour) + ":" + String.valueOf(endMin) + "AM";
			}
		}
	
		if ("A".equals(getMeetingDays())) {
			meetingString = "Arranged";
		}
	
	
		return meetingString;
	
	}
	
	/**
	 * returns the shortDisplay of information 
	 * @return shortDisplay an array with length 4
	 */
    public abstract String[] getShortDisplayArray();
    
    /**
     *  returns the longDisplay of information 
     * @return longDisplay an array with length of 7
     */
	public abstract String[] getLongDisplayArray();

	/**
	 * Finds if there is a duplicate of an acitivity. 
	 * @param activity the activity we are finding to see if there is a duplicate of 
	 * @return true if the activity is a duplicate in either course or event 
	 */
	public abstract boolean isDuplicate(Activity activity); 
	
	/**
	 * Generates hashcodefor object and returns result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + upperHour;
		result = prime * result + upperMinute;
		return result;
	}

	/**
	 * equals method between two objects 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return !(upperHour == other.upperHour) 
				|| upperMinute == other.upperMinute;
	}

}