package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student Class represents a student records. 
 * each student has a first and last name, id, email, password, max amount of credits 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
public class Student extends User implements Comparable<Student> {
	
	/** Student's max credits */ 
	private int maxCredits; 
	/** Amount of max credits Student is allowed to have */ 
	public static final int MAX_CREDITS = 18;
	 /** Student's schedule */
    private Schedule schedule;
	
	/**
	 * Constructs a Student object with values for all fields 
	 * @param firstName the first name of the student 
	 * @param lastName the last name of the student 
	 * @param id the student id 
	 * @param email the student email 
	 * @param password the student password 
	 * @param maxCredits the max amount of credits allowed 
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);   
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.id = id;
//		this.email = email;
//		this.password = password;
		this.maxCredits = maxCredits; 
		this.schedule = new Schedule();
		setFirstName(firstName); 
		setLastName(lastName); 
		setId(id); 
		setEmail(email); 
		setPassword(password); 
		setMaxCredits(maxCredits);
	} 
	
	/**
	 * Constructs a Student object with values for all fields 
	 * @param firstName the first name of the student 
	 * @param lastName the last name of the student 
	 * @param id the student id 
	 * @param email the student email 
	 * @param password the student password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS); 
	} 
	
	
	/**
	 * Returns Student's max credits. 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	/**
	 * Sets Student's max credits. 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if max credits is less than 3 or greater than 18.
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > MAX_CREDITS)
		{
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	/**
     * Returns the schedule of the student.
     * @return the schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }


    /**
     * Attempts to add a course to the student's schedule.
     * @param course the course to add
     * @return true if added successfully, false otherwise
     */
    public boolean addCourseToSchedule(Course course) {
        return schedule.addCourseToSchedule(course);
    }

    /**
     * Removes a course from the student's schedule.
     * @param course the course to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeCourseFromSchedule(Course course) {
        return schedule.removeCourseFromSchedule(course);
    }

    
    /**
     * Resets the student's schedule.
     */
    public void resetSchedule() {
        schedule.resetSchedule();
    }
    

	/**
	 * compares 
	 */
	@Override
	public int compareTo(Student s) {
		if(this.getLastName().compareTo(s.getLastName()) < 0)
		{
			return -1;
		}
		if(this.getLastName().compareTo(s.getLastName()) > 0)
		{
			return 1;
		}
		if(this.getFirstName().compareTo(s.getFirstName()) < 0)
		{
			return -1;
		}
		if(this.getFirstName().compareTo(s.getFirstName()) > 0)
		{
			return 1;
		}
		if(this.getId().compareTo(s.getId()) < 0)
		{
			return -1;
		}
		if(this.getId().compareTo(s.getId()) > 0)
		{
			return 1;
		}
		return 0;
	}

	/**
	 * hashCode method for Student objects 
	 * @return result the hashCode equivalent 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares the student objects to one another 
	 * @param obj the object to compare to 
	 * @return true if the objects are equal 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

	/**
	 * Returns a comma separated value String f all Student fields.
	 * @return String representation of Student  
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail()
				+ "," + getPassword() + "," + maxCredits;
	}
	
	/**
	 * see course can be added to student schedule 
	 * @param course the course to add 
	 * @return true if course is not null, there is no duplicate, does not 
	 * create conflict and there is room for credits 
	 */
	public boolean canAdd(Course course) {
		if(!this.schedule.canAdd(course)){
			return false; 
		}
		else if((course.getCredits() + this.schedule.getScheduleCredits()) > this.maxCredits) {
			return false; 
		}
		return true; 
	}

	
}
