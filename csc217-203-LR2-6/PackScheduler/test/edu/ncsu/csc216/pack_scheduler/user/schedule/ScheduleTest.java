package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test the Schedule class 
 * @author Diya Patel 
 */
class ScheduleTest {
	
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
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * set up for test cases 
	 * @throws Exception if there is an exception to throw 
	 */
	@BeforeEach
	void setUp() throws Exception {
		//set up for test cases 
	}

	/**
	 * Tests Schedule.Schedule() 
	 */
	@Test
	void testSchedule() {       
		Schedule s = new Schedule(); 
		assertEquals(s.getTitle(), "My Schedule"); 
		assertEquals(s.getScheduledCourses().length, 0); 
		
	}
	/**
	 * Test Schedule.setTitle().
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule(); 
		
		//Set Title and check that changed
		s.setTitle("New Title");
		assertEquals("New Title", s.getTitle());
		
		//Check that exception is thrown if null title and no
		//change to title already there.
		try {
			s.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("New Title", s.getTitle());
		}
	}
	
	/**
	 * Test Schedule.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule(); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		//Add some courses and reset schedule
		assertTrue(s.addCourseToSchedule(c)); 
		assertEquals(1, s.getScheduledCourses().length);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(2, s.getScheduledCourses().length);
		
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
	
		
		//Check that resetting doesn't break future adds
		assertTrue(s.addCourseToSchedule(c));
		assertEquals(1, s.getScheduledCourses().length);
	}
	
	/**
	 * Tests Schedule.getScheduledCourses
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s = new Schedule(); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		assertTrue(s.addCourseToSchedule(c)); 
		assertEquals(1, s.getScheduledCourses().length);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(2, s.getScheduledCourses().length);
		
		String [][] schedule = s.getScheduledCourses();
		//Row 0
		assertEquals("CSC216", schedule[0][0]);
		assertEquals("001", schedule[0][1]);
		assertEquals("Software Development Fundamentals", schedule[0][2]);
		assertEquals("MW 1:30PM-2:45PM", schedule[0][3]);
		//Row 1
		assertEquals("CSC116", schedule[1][0]);
		assertEquals("002", schedule[1][1]);
		assertEquals("Intro to Programming", schedule[1][2]);
		assertEquals("MW 11:20AM-1:10PM", schedule[1][3]);	 
		
	}
	
	/**
	 * Test Schedule.addCourseToSchedule()
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule(); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		Course c2 = new Course("CSC316", "Intro to Programming", "003", 3, "spbalik", 250, "MW", 1140, 1310); 
	
		//Add some courses
		assertTrue(s.addCourseToSchedule(c)); 
		assertEquals(1, s.getScheduledCourses().length);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(2, s.getScheduledCourses().length);
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c1));
				assertEquals("You are already enrolled in " + c1.getName(), e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c2));
				assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
	
	}
	
	/**
	 * Test Schedule.removeCourseFromSchedule()
	 */
	@Test 
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule(); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		assertFalse(s.removeCourseFromSchedule(c)); 
		
		//Add some courses
		assertTrue(s.addCourseToSchedule(c)); 
		assertEquals(1, s.getScheduledCourses().length);
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(2, s.getScheduledCourses().length);
		
		assertTrue(s.removeCourseFromSchedule(c)); 
		assertEquals(1, s.getScheduledCourses().length);
	}
	
	/**
	 * Test Schedule.canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule(); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		Course c2 = new Course("CSC316", "Intro to Programming", "003", 3, "spbalik", 250, "MW", 1140, 1310); 
	
		assertTrue(s.canAdd(c)); 
		s.addCourseToSchedule(c); 
		assertTrue(s.canAdd(c1));
		s.addCourseToSchedule(c1); 
		
		assertFalse(s.canAdd(c1));
		assertFalse(s.canAdd(null)); 	
		assertFalse(s.canAdd(c2)); 
	}
	
	/**
	 * Test Schedule.getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule(); 
		assertEquals(s.getScheduleCredits(), 0); 
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		s.addCourseToSchedule(c); 
		assertEquals(s.getScheduleCredits(), 3); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 3, "spbalik", 250, "MW", 1120, 1310); 
		s.addCourseToSchedule(c1); 
		assertEquals(s.getScheduleCredits(), 6); 
	}
}
