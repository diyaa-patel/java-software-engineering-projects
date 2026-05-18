/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;


import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests the Faculty Class 
 */
public class FacultyTest {
	
	/** Test Faculty's first name. */
	private String firstName = "first";
	/** Test Faculty's last name */
	private String lastName = "last";
	/** Test Faculty's id */
	private String id = "flast";
	/** Test Faculty's email */
	private String email = "first_last@ncsu.edu";
	/** Test Faculty's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Test max Credits */
	private int maxCourses = 3; 
//	/** Course name */
//	private static final String NAME = "CSC216";
//	/** Course title */
//	private static final String TITLE = "Software Development Fundamentals";
//	/** Course section */
//	private static final String SECTION = "001";
//	/** Course credits */
//	private static final int CREDITS = 3;
//	/** Course instructor id */
//	private static final String INSTRUCTOR_ID = "sesmith5";
//	/** Course meeting days */
//	private static final String MEETING_DAYS = "MW";
//	/** Course start time */
//	private static final int START_TIME = 1330;
//	/** Course end time */
//	private static final int END_TIME = 1445;
//	/** Course enrollment cap */
//    private static final int ENROLLMENT_CAP = 100;
	
	
	//This is a block of code that is executed when the FacultyTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the FacultyTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",3", s1.toString());
	}
	
	/**
	 * Tests getFirstName method 
	 */
	@Test 
	public void testGetFirstName() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals("first", s1.getFirstName()); 
	}
	
	/**
	 * Tests getFirstName method for invalid first name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidFirstName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("", lastName, id, email, hashPW, maxCourses));
				assertEquals("Invalid first name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(null, lastName, id, email, hashPW, maxCourses));
				assertEquals("Invalid first name", e2.getMessage());
	}
	
	/**
	 * Tests getLastName method 
	 */
	@Test 
	public void testGetLastName() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals("last", s1.getLastName()); 
	}
	
	/**
	 * Tests getLastName method for invalid last name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidLastName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, "", id, email, hashPW, maxCourses));
				assertEquals("Invalid last name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, null, id, email, hashPW, maxCourses));
				assertEquals("Invalid last name", e2.getMessage());
	}
	
	/**
	 * Tests getId method 
	 */
	@Test 
	public void testGetId() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals(id, s1.getId()); 
	}
	
	/**
	 * Tests getId method for invalid Id, which is an empty string or null 
	 */
	@Test 
	public void testInvalidId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, "", email, hashPW, maxCourses));
				assertEquals("Invalid id", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, null, email, hashPW, maxCourses));
				assertEquals("Invalid id", e2.getMessage());
	}
	
	/**
	 * Tests getEmail method 
	 */
	@Test 
	public void testGetEmail() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals(email, s1.getEmail()); 
	}
	
	/**
	 * Tests getEmail method for invalid email, which is an empty string or null or missing an @ or if "." is before @
	 */
	@Test 
	public void testInvalidGetEmail() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, "", hashPW, maxCourses));
				assertEquals("Invalid email", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, null, hashPW, maxCourses));
				assertEquals("Invalid email", e2.getMessage());
				
			Exception e3 = assertThrows(IllegalArgumentException.class,
						() -> new Faculty(firstName, lastName, id, "invalidemail", hashPW, maxCourses));
						assertEquals("Invalid email", e3.getMessage());
			
			Exception e4 = assertThrows(IllegalArgumentException.class,
								() -> new Faculty(firstName, lastName, id, "invalid.ema@il", hashPW, maxCourses));
								assertEquals("Invalid email", e4.getMessage());
								
			Exception e5 = assertThrows(IllegalArgumentException.class,
					() -> new Faculty(firstName, lastName, id, "invalid.email", hashPW, maxCourses));
					assertEquals("Invalid email", e5.getMessage());		
					
			Exception e6 = assertThrows(IllegalArgumentException.class,
					() -> new Faculty(firstName, lastName, id, "invalid@email", hashPW, maxCourses));
					assertEquals("Invalid email", e6.getMessage());		
	}
	
	/**
	 * Tests getPassword method 
	 */
	@Test 
	public void testGetPassword() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals(hashPW, s1.getPassword()); 
	}
	
	/**
	 * Tests getPassword method for invalid password, which is an empty string or null 
	 * or if password only contains digits 
	 */
	@Test 
	public void testInvalidPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, "", maxCourses));
				assertEquals("Invalid password", e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Faculty(firstName, lastName, id, email, null, maxCourses));
						assertEquals("Invalid password", e2.getMessage());
						
		Exception e3 = assertThrows(IllegalArgumentException.class,
								() -> new Faculty(firstName, lastName, id, email, "1234", maxCourses));
								assertEquals("Invalid password", e3.getMessage());
				
	}		
	
	
	/**
	 * Tests getMaxCourses method 
	 */
	@Test 
	public void testGetMaxCourses() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertEquals(maxCourses, s1.getMaxCourses()); 
		assertFalse(s1.isOverloaded()); 
		Faculty s2; 
		try {
			s2 = new Faculty(firstName, lastName, id, email, hashPW, 4);
			assertTrue(s2.isOverloaded());  
		} catch(Exception e){
			// exception thrown is caught 
			System.out.println(); 
		}
	}
	
	/**
	 * Tests getSchedule method 
	 */
	@Test 
	public void testGetSchedule() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses); 
		assertEquals(s1.getSchedule().getNumScheduledCourses(), 0); 
	}
	
	/**
	 * Tests isOverloaded method 
	 */
	@Test 
	public void testIsOverLoaded() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		assertFalse(s1.isOverloaded());
		
		
	}
	
	/**
	 * Tests getMaxCredits method for invalid values 
	 */
	@Test 
	public void testInvalidGetMaxCredits() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, hashPW, 0));
				assertEquals("Invalid max courses", e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Faculty(firstName, lastName, id, email, hashPW, 4));
						assertEquals("Invalid max courses", e2.getMessage());
	}
	
	/**
	 * Tests setFirstName
	 */
	@Test
	public void testInvalidSetFirstName() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCourses);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage()); //Check correct exception message
		assertEquals("first", s.getFirstName()); //Check that first name didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage()); //Check correct exception message
		assertEquals("first", s.getFirstName()); //Check that first name didn't change
	}
	
	/**
	 * Tests setLastName
	 */
	@Test
	public void testInvalidSetLastName() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCourses);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage()); //Check correct exception message
		assertEquals("last", s.getLastName()); //Check that last name didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage()); //Check correct exception message
		assertEquals("last", s.getLastName()); //Check that last name didn't change
	}
	
	/**
	 * Tests setEmail
	 */
	@Test
	public void testInvalidSetEmail() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCourses);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that email didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(""));
		assertEquals("Invalid email", e2.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that email didn't change
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("invalidemail"));
		assertEquals("Invalid email", e3.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that email didn't change
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("inval.idem@ail"));
		assertEquals("Invalid email", e4.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that email didn't change
	}
	
	/**
	 * Tests setPassword
	 */
	@Test
	public void testInvalidSetPassword() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCourses);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage()); //Check correct exception message
		assertEquals("hashedpassword", s.getPassword()); //Check that password didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(""));
		assertEquals("Invalid password", e2.getMessage()); //Check correct exception message
		assertEquals("hashedpassword", s.getPassword()); //Check that password didn't change
	}
	
	/**
	 * Tests setMaxCourses
	 */
	@Test
	public void testInvalidSetMaxCourses() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCourses);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCourses(0));
		assertEquals("Invalid max courses", e1.getMessage()); //Check correct exception message
		assertEquals(maxCourses, s.getMaxCourses()); //Check that maxCredits didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCourses(20));
		assertEquals("Invalid max courses", e2.getMessage()); //Check correct exception message
		assertEquals(maxCourses, s.getMaxCourses()); //Check that maxCredits didn't change
	}
	
	/**
	 * Tests that the equals method works for all Course fields.
	 */
	@Test
	public void testEqualsObject() {
		Faculty c1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		Faculty c2 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		Faculty c5 = new Faculty("diya", lastName, id, email, hashPW, maxCourses);
		Faculty c6 = new Faculty(firstName, "patel", id, email, hashPW, maxCourses);
		Faculty c7 = new Faculty(firstName, lastName, "dapatel", email, hashPW, maxCourses);
		Faculty c8 = new Faculty(firstName, lastName, id, "dapatel@ncsu.edu", hashPW, maxCourses);
		Faculty c9 = new Faculty(firstName, lastName, id, email, "acd123", maxCourses);
		Faculty c10 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		

		// Test for equality in both directions
		assertEquals(c1, c2);
		assertEquals(c1, c1);
		assertEquals(c2, c1);
	

		// Test for each of the fields
		assertNotEquals(c1, c5);
		assertNotEquals(null, c1);
		//assertNotEquals(new FacultyDirectory(), c1);
		
		assertNotEquals(c1, c6);
		assertNotEquals(c1, c7);
		assertNotEquals(c1, c8);
		assertNotEquals(c1, c9);
		assertNotEquals(c1, c10);
	}

	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Faculty c1 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		Faculty c2 = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		Faculty c5 = new Faculty("diya", lastName, id, email, hashPW, maxCourses);
		Faculty c6 = new Faculty(firstName, "patel", id, email, hashPW, maxCourses);
		Faculty c7 = new Faculty(firstName, lastName, "dapatel", email, hashPW, maxCourses);
		Faculty c8 = new Faculty(firstName, lastName, id, "dapatel@ncsu.edu", hashPW, maxCourses);
		Faculty c9 = new Faculty(firstName, lastName, id, email, "acd123", maxCourses);
		Faculty c10 = new Faculty(firstName, lastName, id, email, hashPW, 2);


		// Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());

		// Test for each of the fields
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
		assertNotEquals(c1.hashCode(), c10.hashCode());

	}
	


}
