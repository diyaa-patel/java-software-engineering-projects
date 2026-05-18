package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;


/**
 * Schedule for courses which has a title 
 * can add/remove courses to schedule and get the scheudled courses and amount of credits 
 * can also reset schedule and set the title 
 */
public class Schedule {
	/** schedule of courses */ 
	ArrayList<Course> schedule; 
	/** title for schedule */ 
	String title; 

	/**
	 * Constructor for schedule that intializes title as "My Schedule"
	 * and creates an empty ArrayList of Courses 
	 */
	public Schedule() {
		title = "My Schedule"; 
		schedule = new ArrayList<Course>(); 
	}
	
	/**
	 * Returns an array of Scheduled Courses with their name, section, and title. 
	 * @return scheudleCourses an array where each element has a name,section, title, and meetingString.
	 */
	public String[][] getScheduledCourses() {
		String[][] scheudleCourses = new String[schedule.size()][4]; 
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			scheudleCourses[i] = c.getShortDisplayArray();
		}
		return scheudleCourses;
	} 
	
	/**
	 * returns true if the given course if 
	 * course is successfully added to the student's schedule 
	 * @param course the course to add to the schedule 
	 * @return true if criteria is fufilled. 
	 * @throws IllegalArgumentException if the student is already enrolled in the course of if there is a conflict. 
	 */
	public boolean addCourseToSchedule(Course course) {
		Course course1;
		for(int j = 0; j < schedule.size(); j++) {
			course1 = schedule.get(j); 
			if(course.isDuplicate(course1)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName()); 
			}
			else {
				try {
					course1.checkConflict(course);
				} catch(ConflictException e) {    
					throw new IllegalArgumentException("The course cannot be added due to a conflict."); 
				}   
			}  
		}
			
		schedule.add(course);
		return true; 
	}
	
	/**
	 * Will remove the course of the index 
	 * otherwise will return false 
	 * @param course the course to be removed 
	 * @return true if the index is valid and the course is removed from schedule
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if(schedule.size() == 0) {
			return false; 
		}
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i) == course) {
				schedule.remove(course); 
				return true; 
			}
		}
		return false; 
	} 
	
	/**
	 * gets the schedule title 
	 * @return title the title of the schedule 
	 */
	public String getTitle() {
		return this.title; 
	}
	
	/**
	 * Sets the schedule title 
	 * @param title title to be set 
	 * @throws IllegalArgumentException if the title is null.
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null."); 
		}
		this.title = title; 
		
	}
	
	/**
	 * Resets the values in schedule 
	 */
	public void resetSchedule() {
		
		schedule = new ArrayList<Course>(); 
	}
	
	/**
	 * gets the total amount of credits in schedule 
	 * @return sum the total credits in schedule 
	 */
	public int getScheduleCredits() {
		int sum = 0; 
		for(int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits(); 
		}
		return sum; 
	}
	
	/**
	 * checks to see if course can be added 
	 * @param course the course to add 
	 * @return true if course is not null, not a duplicate, and does not create a conflict 
	 */
	public boolean canAdd(Course course) {
		if(course == null) {
			return false; 
		}
		
		Course course1;
		for(int j = 0; j < schedule.size(); j++) {
			course1 = schedule.get(j); 
			if(course.isDuplicate(course1)) {
				return false; 
			}
			else {
				try {
					course1.checkConflict(course);
				} catch(ConflictException e) {    
					return false; 
				}   
			}
		}
		
		return true; 
	
	}
}
