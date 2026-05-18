package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course class which creates course and their instances. 
 * @author Diya Patel 
 */
public class Course {
	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
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
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);

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
	 * 
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
	 * 
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
	 * Returns the Course title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course title.
	 * 
	 * @param title the title to set
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
	 * Returns the Course Section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course section.
	 * 
	 * @param section the section to set
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
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course credits.
	 * 
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < minCredits || credits > maxCredits) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor Id.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course instructor Id.
	 * 
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns the Course meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course end time.
	 * 
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
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
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
	 * 
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
	 * Generates a hashCode for Course using all the fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given Course for equality to this Course on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

}
