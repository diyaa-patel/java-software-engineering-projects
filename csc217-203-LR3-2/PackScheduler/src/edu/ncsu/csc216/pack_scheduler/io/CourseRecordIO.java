package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileInputStream; 
import java.io.PrintStream;

import java.io.File; 

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
//import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
//import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList; 

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course current = courses.get(i);
	                if (course.getName().equals(current.getName()) &&
	                    course.getSection().equals(current.getSection())) {
	                    duplicate = true;
	                    break;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	        	System.out.println(); 
	            // Skip invalid course entries
	        }
	    }
	    fileReader.close();
	    return courses;
	}

    /**
     * Writes the given list of Courses to 
     * @param fileName file to write schedule of Courses to
     * @param catalog list of Courses to write
     * @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> catalog) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < catalog.size(); i++) {
    	    fileWriter.println(catalog.get(i).toString());
    	}

    	fileWriter.close();
        
    }
    
   /**
    * This method loads the line into scanner object and reads the token to create
    * course object 
    * @param nextLine the next line argument 
    * @return course instance of course object 
    * @throws IllegalArgumentException if there is a problem with reading the course or if it is Arranged
    */
    private static Course readCourse(String nextLine) {
        Scanner scanner = new Scanner(nextLine);
        scanner.useDelimiter(",");
        try {
            String name = scanner.next();
            String title = scanner.next();
            String section = scanner.next();
            int credits = scanner.nextInt();
            String instructorIdToCheck = scanner.next();
            int enrollmentCap = scanner.nextInt();
            String meetingDays = scanner.next();

            Course course;
            if ("A".equals(meetingDays)) {
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException();
                }
                course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
            } else {
                int startTime = scanner.nextInt();
                int endTime = scanner.nextInt();
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException();
                }
                course = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
            }
            FacultyDirectory facDir = RegistrationManager.getInstance().getFacultyDirectory(); 
            if (facDir.getFacultyById(instructorIdToCheck) != null) {
                course.setInstructorId(instructorIdToCheck);
                facDir.getFacultyById(instructorIdToCheck).getSchedule().addCourseToSchedule(course); 
            }

            return course;
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        } finally {
            scanner.close();
        }
    }
 }
