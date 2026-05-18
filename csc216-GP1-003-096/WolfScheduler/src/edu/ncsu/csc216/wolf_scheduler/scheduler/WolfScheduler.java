package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Wolf Scheduler reads in and stores as a list of all of the Course records stored in a file  
 * @author Diya Patel 
 */
public class WolfScheduler {
	/** Course catalog */ 
	ArrayList<Course> catalog; 
	/** Course schedule */ 
	ArrayList<Course> schedule; 
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
			schedule = new ArrayList<Course>(); 
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file."); 
		}
	}

	/**
	 * returns an array with courses each element having its name, section, and title.
	 * @return courses an array with course elements. 
	 */
	public String[][] getCourseCatalog() {
		String[][] courses = new String[catalog.size()][3]; 
		Course course; 
		for(int i = 0; i < catalog.size(); i++) {
			course = catalog.get(i); 
			courses[i][0] = course.getName(); 
			courses[i][1] = course.getSection(); 
			courses[i][2] = course.getTitle(); 
		}
		return courses;
	}

	/**
	 * Returns an array of Scheduled Courses with their name, section, and title. 
	 * @return scheduleCourse an array where each element has a name,section, and title.
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleCourse = new String[schedule.size()][3]; 
		Course course; 
		for(int i = 0; i < schedule.size(); i++) {
			course = schedule.get(i); 
			scheduleCourse[i][0] = course.getName(); 
			scheduleCourse[i][1] = course.getSection(); 
			scheduleCourse[i][2] = course.getTitle(); 
		}
		return scheduleCourse;
	}
	/**
	 * Returns an array of Scheduled Courses with their name, section, title, credits, and instructor id. 
	 * @return scheduleFullCourse an array where each element has a name,section, title, credits and instructor id.
	 */
	public String[][] getFullScheduledCourses() {
		String[][] scheduleFullCourse = new String[schedule.size()][6]; 
		Course course; 
		for(int i = 0; i < schedule.size(); i++) {
			course = schedule.get(i); 
			scheduleFullCourse[i][0] = course.getName(); 
			scheduleFullCourse[i][1] = course.getSection(); 
			scheduleFullCourse[i][2] = course.getTitle(); 
			scheduleFullCourse[i][3] = String.valueOf(course.getCredits()); 
			scheduleFullCourse[i][4] = course.getInstructorId(); 
			scheduleFullCourse[i][5] = course.getMeetingString();
		}
		return scheduleFullCourse;
	}

	/**
	 * Sets the schedule title 
	 * @param title title to be set 
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
	        CourseRecordIO.writeCourseRecords(fileName, schedule); 

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
		Course course; 
		for(int i = 0; i < schedule.size(); i++) {
			course = schedule.get(i); 
			if(course.getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name); 
			}
			
		}
		for(int i = 0; i < catalog.size(); i++) {
			course = catalog.get(i); 
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				
				schedule.add(course); 
				return true; 
			}
		}
		return false; 
	}

	/**
	 * Will remove the course if the name and section are matched and return true 
	 * otherwise will return false 
	 * @param name name of the course 
	 * @param section of the course
	 * @return false if the course is not found and cannot be removed 
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		Course course; 
		for(int i = 0; i < schedule.size(); i++) {
			course = schedule.get(i); 
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				schedule.remove(course); 
				return true; 
			}	
		}
		return false; 
	}

	/**
	 * Resets the values in schedule 
	 */
	public void resetSchedule() {
		
		schedule = new ArrayList<Course>(); 
		
		
	}

	

}
