package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Wolf Scheduler reads in and stores as a list of all of the Course records stored in a file  
 * @author Diya Patel 
 */
public class WolfScheduler {
	/** Course catalog */ 
	ArrayList<Course> catalog;
	//Changed schedule to an Activity's ArrayList 
	/** Course schedule */ 
	ArrayList<Activity> schedule; 
	/** Course Title */ 
	String title; 
	

	/**
	 * Creates a catalog from courseFile 
	 * @param courseFile a file with all the courses to be read. 
	 * @throws IllegalArgumentException "Cannot find file." if file is not found
	 */
	public WolfScheduler(String courseFile) {
		
		try {
			catalog = CourseRecordIO.readCourseRecords(courseFile); 
			title = "My Schedule"; 
			schedule = new ArrayList<Activity>(); 
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file."); 
		}
	}

	/**
	 * returns an array with courses each element having its name, section, title, and meetingString.
	 * @return catalog an array with course elements. 
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][4]; 
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Returns an array of Scheduled Activities with their name, section, and title. 
	 * @return scheduleActivities an array where each element has a name,section, title, and meetingString.
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduleActivities = new String[schedule.size()][4]; 
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			scheduleActivities[i] = a.getShortDisplayArray();
		}
		return scheduleActivities;
	}
	/**
	 * Returns an array of Scheduled Activities with the long display elements  
	 * @return scheduleFullActivities an array using the long display method. 
	 */
	public String[][] getFullScheduledActivities() {
		String[][] scheduleFullActivities = new String[schedule.size()][6]; 
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			scheduleFullActivities[i] = a.getLongDisplayArray();
		}
		return scheduleFullActivities;
	}

	/**
	 * Sets the schedule title 
	 * @param title title to be set 
	 * @throws IllegalArgumentException if the title is null.
	 */
	public void setScheduleTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null."); 
		}
		this.title = title; 
		
	}
	
	/**
	 * Returns the title of the schedule 
	 * @return this.title the title of the schedule  
	 */
	public String getScheduleTitle() {
		return this.title; 
	}

	/**
	 * will try to find a match in the catalog depending on the name and section if it is not found 
	 * the method will return null 
	 * @param name the name of the course 
	 * @param section the section of the course
	 * @return null if he course with the given name and section does not exist in the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course course; 
		for(int i = 0; i < catalog.size(); i++) {
			course = catalog.get(i); 
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				return course; 
			}
		}
		return null;
	}

	/**
	 * The method will save the student schedule to a file. 
	 * @param fileName the name of the file where the student's schedule will be saved to 
	 * @throws IllegalArgumentException with the message "The file cannot be saved." if there is an 
	 * IO Exception thrown
	 */
	public void exportSchedule(String fileName) {
		try {
	        ActivityRecordIO.writeActivityRecords(fileName, schedule); 

		} catch(Exception e) {
			throw new IllegalArgumentException("The file cannot be saved."); 
		}
		
	}

	/**
	 * returns true if the given course is 
	 * 1) exists in the catalog 
	 * 2) course is successfully added to the student's schedule 
	 * @param name of the course to be added 
	 * @param section of the course to be added 
	 * @return false if either of the criteria is not fulfilled. 
	 * @throws IllegalArgumentException if the student is already enrolled in the course 
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Activity activity; 
		Course course;
		for(int i = 0; i < catalog.size(); i++) {
			course = catalog.get(i); 
			if(course.getName().equals(name)) {
				for(int j = 0; j < schedule.size(); j++) {
					activity = schedule.get(j); 
					if(activity instanceof Course && course.isDuplicate(activity)) {
						throw new IllegalArgumentException("You are already enrolled in " + name); 
					}
				}
				if(course.getSection().equals(section)) {
					schedule.add(course); 
					return true;
				} 
			}
		}
		return false; 
	}
	
	/**
	 * adds the event to schedule 
	 * @param eventTitle the title of the event 
	 * @param eventMeetingDays the days where the event is happening 
	 * @param eventStartTime the start time of the event 
	 * @param eventEndTime the end time of the event 
	 * @param eventDetails the details of the event 
	 * @throws IllegalArgumentException if the event is a dulpicate 
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {

		Activity activity; 
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails); 
		
		for(int j = 0; j < schedule.size(); j++) {
			activity = schedule.get(j); 
			if(activity instanceof Event && event.isDuplicate(activity)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle); 
			}
		}
		schedule.add(event); 
	}
	


	/**
	 * Will remove the course of the index 
	 * otherwise will return false 
	 * @param idx the index of the activity which will be removed 
	 * @return true if the index is valid and the activity is removed from schedule
	 */
	public boolean removeActivityFromSchedule(int idx) {
		if(schedule.size() == 0) {
			return false; 
		}
		if(idx < 0 || idx >= schedule.size()){
			return false; 
		}
		schedule.remove(idx); 
		return true; 
	} 

	/**
	 * Resets the values in schedule 
	 */
	public void resetSchedule() {
		
		schedule = new ArrayList<Activity>(); 
		
		
	}

	

}
