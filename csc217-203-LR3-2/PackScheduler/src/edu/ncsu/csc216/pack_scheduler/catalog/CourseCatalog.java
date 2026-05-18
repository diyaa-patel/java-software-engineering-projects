/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Course Catalog class which can create an empty catalog, load courses from file, add course to catalog, remove courses from catalog, 
 * and get courses from catalog, and save courses to a catalog 
 */
public class CourseCatalog {
	
	/** Course catalog */ 
	private SortedList<Course> catalog;
	
	/**
	 * will make catalog into an empty SortedList 
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();  
	}
	
	/**
	 * Creates a new catalog 
	 */
	public void newCourseCatalog() {
	    catalog = new SortedList<Course>(); 
		
	}
	
	/**
	 * will load the course from the file given 
	 * @param fileName the file which it will be loaded from      
	 * @throws IllegalArgumentException if file is unable to be read 
	 */
	public void loadCoursesFromFile(String fileName) {  
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
		
	}
	
	/**
	 * Will add the course to the catalog if it does not already exist 
	 * @param name name of the course to be added 
	 * @param title title of the course to be added 
	 * @param section section of the course to be added 
	 * @param credits credits of the course to be added 
	 * @param instructorId the id of the instructor of the course
	 * @param enrollmentCap the maximum number of students that can enroll 
	 * @param meetingDays the day of the course when it happens 
	 * @param startTime the startime of the course 
	 * @param endTime the endtime of the course 
	 * @return true if the the course is added to the catalog 
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime); 
		Course existingCourse; 
		for(int i = 0; i < catalog.size(); i++) {
			existingCourse = catalog.get(i); 
			if(existingCourse.getName().equals(name) && existingCourse.getSection().equals(section) && existingCourse.getTitle().equals(title) && existingCourse.getCredits() == credits && existingCourse.getInstructorId().equals(instructorId) && existingCourse.getStartTime() == startTime && existingCourse.getEndTime() == endTime ) {
				
				return false; 
			}
		}
		catalog.add(course);  
		return true; 
	}
	
	/**
	 * Will removed the course of the name and section from the catalog 
	 * @param name the name of the course to be removed 
	 * @param section the section of the course to be removed 
	 * @return false if the catalog was unable to remove the course 
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++)
		{
			Course c = catalog.get(i);
			if(name.equals(c.getName()) && section.equals(c.getSection()))
			{
				catalog.remove(i);
				return true;
			}
		}
		return false;
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
	 * returns an array with courses each element having its name, section, title, and meetingString.
	 * @return catalog an array with course elements. 
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][5]; 
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray; 
	}
	
	/**
	 * Will save the catalog to the given filename 
	 * @param fileName the name of the file the catalog will be saved 
	 * @throws IllegalArgumentException if the file cannot be saved 
	 */
	public void saveCourseCatalog(String fileName) {
		try {
	        CourseRecordIO.writeCourseRecords(fileName, catalog); 

		} catch(Exception e) {
			throw new IllegalArgumentException("The file cannot be saved."); 
		} 
	}
	
	
	
}
