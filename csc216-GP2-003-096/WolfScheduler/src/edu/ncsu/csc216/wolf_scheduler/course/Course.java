package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course class which creates course and their instances. 
 * @author Diya Patel 
 */
public class Course extends Activity {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** minimum number for name length */
	private final int minNameLength = 5;
	/** maximum number for name length */
	private final int maxNameLength = 8;
	/** minimum number for letter count */
	private final int minLetterCount = 1;
	/** maximum number for letter count */
	private final int maxLetterCount = 4;
	/** minimum credit amount */
	private final int minCredits = 1;
	/** maximum credit amount */
	private final int maxCredits = 5;
	/** upper hour limit */
	private final int upperHour = 23;
	/** upper minute limit */
	private final int upperMinute = 59;
	
	/**
	 * Creates a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    the starting time of the Course
	 * @param endTime      the ending time of the Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() == 0 || name.length() < minNameLength || name.length() > maxNameLength) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check for pattern of L[LLL] NNN
		int numLetters = 0;
		int numDigits = 0;
		boolean spaceLoc = false;
		for (int i = 0; i < name.length(); i++) {
			if (!spaceLoc) {
				if (!Character.isDigit(name.charAt(i)) && name.charAt(i) >= 'A' && name.charAt(i) <= 'Z' ) {
					numLetters++;
				} else if (name.charAt(i) == ' ') {
					spaceLoc = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (spaceLoc) {
				if (Character.isDigit(name.charAt(i))) {
					numDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}

			}
		}

		// Check that the number of letters is correct
		if (numLetters < minLetterCount || numLetters > maxLetterCount) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		// Check that the number of digits is correct
		if (numDigits != 3) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course Section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course section.
	 * @param section the section to set
	 * @throw IllegalArgumentException with the message "Invalid section." if 
	 * section id null or the length is not 3 or if there is a character that is not a digit in the string
	 */
	public void setSection(String section) {
		// Checks that section is null or not three characters
		if (section == null || section.length() != 3) {
			throw new IllegalArgumentException("Invalid section.");

		}

		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course credits.
	 * @param credits the credits to set
	 * @throw IllegalArgumentException with the message "Invalid credits." if credits is less than the minimum
	 * number of credits or greater than the maximum number of credits
	 */
	public void setCredits(int credits) {
		if (credits < minCredits || credits > maxCredits) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor Id.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course instructor Id.
	 * @param instructorId the instructorId to set
	 * @throw IllegalArgumentException with the message "Invalid instructor id." if the param is null or empty. 
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * returns an array of length 4 contained the Course name, section, title, and meeting String. 
	 * @return shortDisplay the array containing the given fields above 
	 */
	@Override
	public String[] getShortDisplayArray() {
		String [] shortDisplay = new String[4]; 
		shortDisplay[0] = getName(); 
		shortDisplay[1] = getSection(); 
		shortDisplay[2] = getTitle(); 
		shortDisplay[3] = getMeetingString(); 
		
		return shortDisplay; 
	}

	/**
	 * returns an array of length 7 containing the Course name, section, title, credits, instructorId, meeting String, empty string
	 * @returns longDisplay an array containing the given fields above 
	 */
	@Override
	public String[] getLongDisplayArray() {
		String [] longDisplay = new String[7]; 
		longDisplay[0] = getName(); 
		longDisplay[1] = getSection(); 
		longDisplay[2] = getTitle(); 
		longDisplay[3] = String.valueOf(getCredits()); 
		longDisplay[4] = getInstructorId(); 
		longDisplay[5] = getMeetingString(); 
		longDisplay[6] = ""; 
		
		return longDisplay;
	}
	
	/**
	 * The method sets the meeting days and time for the course
	 * @param meetingDays the days where the course is taking place
	 * @param startTime the time the course starts
	 * @param endTime the time the course ends 
	 * @throw IllegalArgumentException if the meetingString is invalid or the meeting days and times are invalid
	 */
	@Override 
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(getMeetingString() == null || getMeetingString().length() == 0) {
			throw new IllegalArgumentException("Invalid meeting String."); 
		}
		
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);		
		} else { // not arranged
			int mon = 0;
			int tues = 0;
			int weds  = 0;
			int thurs = 0;
			int fri = 0;
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
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
	
			}
	
			if (mon > 1 || tues > 1 || weds > 1 || thurs > 1 || fri > 1) {
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
	
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime); 
	}

	/**
	 * returns a string of "DD Start-End"
	 * @return meetingString a string with the days being met and the time
	 */
	@Override 
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
		
		if(meetingString == null || meetingString.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting String."); 
		}
	
	
		return meetingString;
	
	}
	
	/**
	 * compares two courses to see if they are duplicates 
	 * @param activity the activity we are seeing if there are duplicates of
	 * @return true if the two activities have the same name 
	 * @throw IllegalArgumentException if the activity is not a Course 
	 */
	public boolean isDuplicate(Activity activity) {
		if(!(activity instanceof Course)){
			throw new IllegalArgumentException("Activity is not a Course.");
		}        
		String activityName = ((Course) activity).getName();
		return getName().equals(activityName); 
	}

	/**
	 * hashCode for events 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + maxLetterCount;
		result = prime * result + maxNameLength;
		result = prime * result + minCredits;
		result = prime * result + minLetterCount;
		result = prime * result + minNameLength;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + upperHour;
		result = prime * result + upperMinute;
		return result;
	}

	/**
	 * equals method for Course objects 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (maxLetterCount != other.maxLetterCount)
			return false;
		if (maxNameLength != other.maxNameLength)
			return false;
		if (minCredits != other.minCredits)
			return false;
		if (minLetterCount != other.minLetterCount)
			return false;
		if (minNameLength != other.minNameLength)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return !(upperHour == other.upperHour) || (upperMinute == other.upperMinute);
			
	}

}
