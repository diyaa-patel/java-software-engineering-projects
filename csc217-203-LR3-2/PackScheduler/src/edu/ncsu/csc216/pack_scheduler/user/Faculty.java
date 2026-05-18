/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty Class represents an individual Faculty record
 * @author Diya Patel
 * @author Preeti Joshi
 * @author Ramcharan reddy
 */
public class Faculty extends User {
	
	/** maximum courses **/
	private int maxCourses;
	/** Minimum courses **/
	public static final int MIN_COURSES = 1;
	/** Maximum courses **/
	public static final int MAX_COURSES = 3;
	/** schedule for faculty */
	private FacultySchedule schedule;
	
	/**
	 * Faculty Constructor
	 * @param firstName First Name of Faculty
	 * @param lastName Last Name of Faculty
	 * @param id ID of Faculty
	 * @param email email of Faculty
	 * @param password password of Faculty
	 * @param maxCourses the max amount of classes for faculty 
	 * @throws IllegalArgumentException if there is an invalid parameter 
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses)
	{
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses); 
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * returns the faculty schedule
	 * @return schedule the faculty schedule
	 */
	public FacultySchedule getSchedule()
	{
		return schedule;
	}
	
	/**
	 * returns true if the number of scheduled courses is greater than the Faculty’s maxCourses
	 * @return true if number of scheduled courses is greater than the Faculty’s maxCourses
	 */
	public boolean isOverloaded()
	{
		return schedule.getNumScheduledCourses() > maxCourses; 
	}
	
	
	/**
	 * Sets Maximum number of courses
	 * @param maxCourses maximum course
	 * @throws IllegalArgumentException if maxCourses is below 1 or greater than 3
	 */
	public void setMaxCourses(int maxCourses)
	{
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES)
		{
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Returns the maximum number of courses
	 * @return maxCourses maximum courses
	 */
	public int getMaxCourses()
	{
		return maxCourses;
		
	}
	
	/**
	 * hashCode method for Faculty objects 
	 * @return result the hashCode equivalent 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	
	/**
	 * Compares the faculty objects to one another 
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
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns a comma separated value String f all Faculty fields.
	 * @return String representation of Faculty  
	 */
	public String toString()
	{
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail()
		+ "," + getPassword() + "," + getMaxCourses();
	}
}
