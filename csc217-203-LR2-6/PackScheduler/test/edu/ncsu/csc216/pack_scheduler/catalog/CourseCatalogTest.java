/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class with all its different methods 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt"; 
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	 /** Course enrollment cap */
    private static final int ENROLLMENT_CAP = 100;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	 

	/**
	 * Setup for the test cases 
	 * @throws java.lang.Exception if there is an exception 
	 */
	@BeforeEach
	void setUp() throws Exception {
		//empty
	}


	/**
	 * Test Course Catalog constructor
	 */
	@Test 
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog(); 
		assertEquals(catalog.getCourseCatalog().length, 0); 
	}
	
	/**
	 * Tests newCourseCatalog method 
	 */
	@Test 
	public void testNewCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog(); 
		catalog.newCourseCatalog(); 
		assertEquals(catalog.getCourseCatalog().length, 0);  
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog(); 
		
		//Attempt to get a course that doesn't exist
		assertNull(catalog.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		assertEquals(c, catalog.getCourseFromCatalog(NAME, SECTION));
	}
	
	/**
	 * Test CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog catalog = new CourseCatalog();    
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Attempt to add a course 
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, catalog.getCourseCatalog().length);
		assertEquals(c, catalog.getCourseFromCatalog(NAME, SECTION)); 
	
		
		//Attempt to add a course that already exists
		try {
			catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC 216", e.getMessage());
		}
	}
	 
	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog catalog = new CourseCatalog();    
				
		//Test valid file
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(13, catalog.getCourseCatalog().length);   
	}  
	
	/**
	 * Test WolfScheduler.removeCourse().
	 */
	@Test
	public void testRemoveActivityFromSchedule() {
		CourseCatalog catalog = new CourseCatalog();  
		
		//Attempt to remove from empty schedule
		assertFalse(catalog.removeCourseFromCatalog(NAME, SECTION));
		
		//Add some courses and remove them
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(catalog.addCourseToCatalog("CSC226", TITLE, "001", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(catalog.addCourseToCatalog("CSC116", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));         
		assertEquals(3, catalog.getCourseCatalog().length);

		//Remove CSC226
		assertTrue(catalog.removeCourseFromCatalog("CSC226", "001"));
		assertEquals(2, catalog.getCourseCatalog().length);
		
		//Remove CSC116
		assertTrue(catalog.removeCourseFromCatalog("CSC116", "002"));
		assertEquals(1, catalog.getCourseCatalog().length);
		
		//Remove CSC216
		assertTrue(catalog.removeCourseFromCatalog("CSC216", "001"));
		assertEquals(0, catalog.getCourseCatalog().length);
		
		//Check that removing all doesn't break future adds
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, catalog.getCourseCatalog().length);
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME); 
		assertEquals(c, catalog.getCourseFromCatalog(NAME, SECTION));
	
	}
 
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();  
			
		//Add some courses and remove them
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(catalog.addCourseToCatalog("CSC226", "Discrete Math", "001", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));         
		assertEquals(3, catalog.getCourseCatalog().length);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalogArray = catalog.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalogArray[0][0]);
		assertEquals("002", catalogArray[0][1]);
		assertEquals("Intro to Programming - Java", catalogArray[0][2]);
		//Row 1
		assertEquals("CSC216", catalogArray[1][0]);
		assertEquals("001", catalogArray[1][1]);
		assertEquals("Software Development Fundamentals", catalogArray[1][2]);
		//Row 2
		assertEquals("CSC226", catalogArray[2][0]);
		assertEquals("001", catalogArray[2][1]);
		assertEquals("Discrete Math", catalogArray[2][2]);
	} 
	
	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();  
		
		//Add a course 
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, catalog.getCourseCatalog().length);
		catalog.saveCourseCatalog("test-files/new.txt");
		checkFiles("test-files/expected_new.txt", "test-files/new.txt");
	}    
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	} 
	

	

}
