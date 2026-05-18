package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * CourseRoll class Tests for methods 
 * @author Diya Patel 
 */
class CourseRollTest {

	/**
	 * set up for test
	 * @throws Exception if exception needs to be thrown 
	 */
	@BeforeEach
	void setUp() throws Exception {
		//for set up
	}

	/**
	 * tests CourseRoll constructor using setEnrollmentCap 
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll cs = new CourseRoll(30); 
		assertEquals(cs.getEnrollmentCap(), 30); 
		assertEquals(cs.getOpenSeats(), 30);
		
		CourseRoll cs2 = new CourseRoll(10); 
		assertEquals(cs2.getEnrollmentCap(), 10); 
		assertEquals(cs2.getOpenSeats(), 10);
		
		CourseRoll cs3 = new CourseRoll(250); 
		assertEquals(cs3.getEnrollmentCap(), 250); 
		assertEquals(cs3.getOpenSeats(), 250);
		
	    assertThrows(IllegalArgumentException.class, () -> new CourseRoll(9));
	    assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251)); 
	}
	
	/**
	 * Tests the enroll method
	 */
	@Test
	public void testEnroll() {
		CourseRoll cs = new CourseRoll(30); 
		assertEquals(cs.getEnrollmentCap(), 30); 
		assertEquals(cs.getOpenSeats(), 30);
		
		Student s1 = new Student("firstName", "lastName", "id", "pmjoshi@ncsu.edu", "hashPW", 10);
		cs.enroll(s1);
		
		assertEquals(29, cs.getOpenSeats());
		
		assertThrows(IllegalArgumentException.class, () -> cs.enroll(null));
	}
	
	/**
	 * Tests the drop method
	 */
	@Test
	public void testDrop() {
		CourseRoll cs = new CourseRoll(30); 
		assertEquals(cs.getEnrollmentCap(), 30); 
		assertEquals(cs.getOpenSeats(), 30);
		
		Student s1 = new Student("firstName", "lastName", "id", "pmjoshi@ncsu.edu", "hashPW", 10);
		cs.enroll(s1);
		
		assertEquals(29, cs.getOpenSeats());
		
		cs.drop(s1);

		assertEquals(30, cs.getOpenSeats());
		
		
	}
	
}


