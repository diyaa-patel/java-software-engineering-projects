package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * CourseRoll class Tests for methods 
 * @author Diya Patel 
 */
class CourseRollTest {
    /** Test course */
    private Course course;
  
    /**
     * set up for test
     * @throws Exception if exception needs to be thrown 
     */
    @BeforeEach
    void setUp() throws Exception {
        course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
     
    }

    /**
     * tests CourseRoll constructor using setEnrollmentCap 
     */
    @Test
    public void testCourseRoll() {
        CourseRoll cs = new CourseRoll(course, 30); 
        assertEquals(cs.getEnrollmentCap(), 30); 
        assertEquals(cs.getOpenSeats(), 30);
        assertEquals(0, cs.getNumberOnWaitlist());
        
        CourseRoll cs2 = new CourseRoll(course, 10); 
        assertEquals(cs2.getEnrollmentCap(), 10); 
        assertEquals(cs2.getOpenSeats(), 10);
        
        CourseRoll cs3 = new CourseRoll(course, 250); 
        assertEquals(cs3.getEnrollmentCap(), 250); 
        assertEquals(cs3.getOpenSeats(), 250);
        
        assertThrows(IllegalArgumentException.class, () -> new CourseRoll(course, 9));
        assertThrows(IllegalArgumentException.class, () -> new CourseRoll(course, 251));
        assertThrows(IllegalArgumentException.class, () -> new CourseRoll(null, 10));
    }
    
    /**
     * Tests the enroll method with waitlist
     */
    @Test
    public void testEnroll() {
        CourseRoll cs = new CourseRoll(course, 10); 
        assertEquals(cs.getEnrollmentCap(), 10); 
        assertEquals(cs.getOpenSeats(), 10);
        
        // Test regular enrollment
        Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "hashPW", 10);
        cs.enroll(s1);
        assertEquals(9, cs.getOpenSeats());
        
        // Fill the course
        for (int i = 0; i < 9; i++) {
            cs.enroll(new Student("first" + i, "last" + i, "id" + i, "email" + i + "@ncsu.edu", "hashPW", 10));
        }
        
        // Test waitlist enrollment
        Student waitlistStudent = new Student("wait", "list", "waitid", "wait@ncsu.edu", "hashPW", 10);
        cs.enroll(waitlistStudent);
        assertEquals(1, cs.getNumberOnWaitlist());
        assertEquals(0, cs.getOpenSeats());
        
        // Test enrollment exceptions
        assertThrows(IllegalArgumentException.class, () -> cs.enroll(null));
        assertThrows(IllegalArgumentException.class, () -> cs.enroll(s1)); // duplicate enrollment
    }
    
    /**
     * Tests the drop method with waitlist
     */
    @Test
    public void testDrop() {
        CourseRoll cs = new CourseRoll(course, 10); 
        
        // Test regular drop
        Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "hashPW", 10);
        cs.enroll(s1);
        assertEquals(9, cs.getOpenSeats());
        cs.drop(s1);
        assertEquals(10, cs.getOpenSeats());
        
        // Test drop with waitlist
        // Fill the course
        for (int i = 0; i < 10; i++) {
            cs.enroll(new Student("first" + i, "last" + i, "id" + i, "email" + i + "@ncsu.edu", "hashPW", 10));
        }
        
        // Add to waitlist
        Student waitlistStudent = new Student("wait", "list", "waitid", "wait@ncsu.edu", "hashPW", 10);
        cs.enroll(waitlistStudent);
        assertEquals(1, cs.getNumberOnWaitlist());
        
        // Drop enrolled student
        Student enrolledStudent = new Student("first0", "last0", "id0", "email0@ncsu.edu", "hashPW", 10);
        cs.drop(enrolledStudent);
        assertEquals(0, cs.getNumberOnWaitlist()); // waitlist student should be enrolled
        
        // Test dropping null
        assertThrows(IllegalArgumentException.class, () -> cs.drop(null));
    }
    
    /**
	 * Tests CourseRoll.setEnrollmentCap() 
	 */
	@Test 
	public void testSetEnrollmentCap() {
		CourseRoll cs = new CourseRoll(course, 30); 
		assertEquals(cs.getEnrollmentCap(), 30);  
		assertThrows(IllegalArgumentException.class, () -> cs.setEnrollmentCap(-1)); 
		assertThrows(IllegalArgumentException.class, () -> cs.setEnrollmentCap(5));  
		
	}
    
    /**
     * Tests waitlist functionality
     */
    @Test
    public void testWaitlist() {
        CourseRoll cs = new CourseRoll(course, 10);
        
        // Fill the course
        for (int i = 0; i < 10; i++) {
            cs.enroll(new Student("first" + i, "last" + i, "id" + i, "email" + i + "@ncsu.edu", "hashPW", 10));
        }
        
        // Test waitlist capacity
        for (int i = 0; i < 10; i++) {
            cs.enroll(new Student("wait" + i, "list" + i, "wid" + i, "wait" + i + "@ncsu.edu", "hashPW", 10));
        }
        assertEquals(10, cs.getNumberOnWaitlist());
        
        // Test waitlist full
        Student extraStudent = new Student("extra", "student", "extra", "extra@ncsu.edu", "hashPW", 10);
        assertThrows(IllegalArgumentException.class, () -> cs.enroll(extraStudent));
    }
    /**
     * Tests the canEnroll method thoroughly
     */
    @Test
    public void testCanEnroll() {
        CourseRoll cs = new CourseRoll(course, 10);
        
        // Test with null student
        assertFalse(cs.canEnroll(null));
        
        // Test with valid student when seats are available
        Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "hashPW", 10);
        assertTrue(cs.canEnroll(s1));
        
        // Enroll the student and test again
        cs.enroll(s1);
        assertFalse(cs.canEnroll(s1)); // Should return false for already enrolled student
        
        // Fill the course
        for (int i = 1; i < 10; i++) {
            cs.enroll(new Student("first" + i, "last" + i, "id" + i, "email" + i + "@ncsu.edu", "hashPW", 10));
        }
        
        // Test when course is full but waitlist has space
        Student waitlistStudent = new Student("wait", "list", "waitid", "wait@ncsu.edu", "hashPW", 10);
        assertTrue(cs.canEnroll(waitlistStudent));
    }
}