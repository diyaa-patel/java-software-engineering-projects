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

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the Student object
 * @author SarahHeckman
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
public class StudentTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Test max Credits */
	private int maxCredits = 18; 
	
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
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
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
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * Tests getFirstName method 
	 */
	@Test 
	public void testGetFirstName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first", s1.getFirstName()); 
	}
	
	/**
	 * Tests getFirstName method for invalid first name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidFirstName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, hashPW));
				assertEquals("Invalid first name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW));
				assertEquals("Invalid first name", e2.getMessage());
	}
	
	/**
	 * Tests getLastName method 
	 */
	@Test 
	public void testGetLastName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("last", s1.getLastName()); 
	}
	
	/**
	 * Tests getLastName method for invalid last name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidLastName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW));
				assertEquals("Invalid last name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW));
				assertEquals("Invalid last name", e2.getMessage());
	}
	
	/**
	 * Tests getId method 
	 */
	@Test 
	public void testGetId() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(id, s1.getId()); 
	}
	
	/**
	 * Tests getId method for invalid Id, which is an empty string or null 
	 */
	@Test 
	public void testInvalidId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW));
				assertEquals("Invalid id", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW));
				assertEquals("Invalid id", e2.getMessage());
	}
	
	/**
	 * Tests getEmail method 
	 */
	@Test 
	public void testGetEmail() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(email, s1.getEmail()); 
	}
	
	/**
	 * Tests getEmail method for invalid email, which is an empty string or null or missing an @ or if "." is before @
	 */
	@Test 
	public void testInvalidGetEmail() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW));
				assertEquals("Invalid email", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW));
				assertEquals("Invalid email", e2.getMessage());
				
			Exception e3 = assertThrows(IllegalArgumentException.class,
						() -> new Student(firstName, lastName, id, "invalidemail", hashPW));
						assertEquals("Invalid email", e3.getMessage());
			
			Exception e4 = assertThrows(IllegalArgumentException.class,
								() -> new Student(firstName, lastName, id, "invalid.ema@il", hashPW));
								assertEquals("Invalid email", e4.getMessage());
								
			Exception e5 = assertThrows(IllegalArgumentException.class,
					() -> new Student(firstName, lastName, id, "invalid.email", hashPW));
					assertEquals("Invalid email", e5.getMessage());		
					
			Exception e6 = assertThrows(IllegalArgumentException.class,
					() -> new Student(firstName, lastName, id, "invalid@email", hashPW));
					assertEquals("Invalid email", e6.getMessage());		
	}
	
	/**
	 * Tests getPassword method 
	 */
	@Test 
	public void testGetPassword() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(hashPW, s1.getPassword()); 
	}
	
	/**
	 * Tests getPassword method for invalid password, which is an empty string or null 
	 * or if password only contains digits 
	 */
	@Test 
	public void testInvalidPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, ""));
				assertEquals("Invalid password", e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Student(firstName, lastName, id, email, null));
						assertEquals("Invalid password", e2.getMessage());
						
		Exception e3 = assertThrows(IllegalArgumentException.class,
								() -> new Student(firstName, lastName, id, email, "1234"));
								assertEquals("Invalid password", e3.getMessage());
				
	}		
	
	/**
	 * Tests getFirstName method 
	 */
	@Test 
	public void testGetFirstName2() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals("first", s1.getFirstName()); 
	}
	
	/**
	 * Tests getFirstName method for invalid first name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidFirstName2() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, hashPW, maxCredits));
				assertEquals("Invalid first name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW, maxCredits));
				assertEquals("Invalid first name", e2.getMessage());
	}
	
	/**
	 * Tests getLastName method 
	 */
	@Test 
	public void testGetLastName2() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals("last", s1.getLastName()); 
	}
	
	/**
	 * Tests getLastName method for invalid last name, which is an empty string or null 
	 */
	@Test 
	public void testInvalidLastName3() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW, maxCredits));
				assertEquals("Invalid last name", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW, maxCredits));
				assertEquals("Invalid last name", e2.getMessage());
	}
	
	/**
	 * Tests getId method 
	 */
	@Test 
	public void testGetId2() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals(id, s1.getId()); 
	}
	
	/**
	 * Tests getId method for invalid Id, which is an empty string or null 
	 */
	@Test 
	public void testInvalidId2() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW, maxCredits));
				assertEquals("Invalid id", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW, maxCredits));
				assertEquals("Invalid id", e2.getMessage());
	}
	
	/**
	 * Tests getEmail method 
	 */
	@Test 
	public void testGetEmail2() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals(email, s1.getEmail()); 
	}
	
	/**
	 * Tests getEmail method for invalid email, which is an empty string or null or missing an @ or if "." is before @
	 */
	@Test 
	public void testInvalidGetEmail2() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", hashPW, maxCredits));
				assertEquals("Invalid email", e1.getMessage());
				
			Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW, maxCredits));
				assertEquals("Invalid email", e2.getMessage());
				
			Exception e3 = assertThrows(IllegalArgumentException.class,
						() -> new Student(firstName, lastName, id, "invalidemail", hashPW, maxCredits));
						assertEquals("Invalid email", e3.getMessage());
			
			Exception e4 = assertThrows(IllegalArgumentException.class,
								() -> new Student(firstName, lastName, id, "invalid.ema@il", hashPW, maxCredits));
								assertEquals("Invalid email", e4.getMessage());
	}
	
	/**
	 * Tests getPassword method 
	 */
	@Test 
	public void testGetPassword2() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals(hashPW, s1.getPassword()); 
	}
	
	/**
	 * Tests getPassword method for invalid password, which is an empty string or null 
	 * or if password only contains digits 
	 */
	@Test 
	public void testInvalidPassword2() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, "", maxCredits));
				assertEquals("Invalid password", e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Student(firstName, lastName, id, email, null, maxCredits));
						assertEquals("Invalid password", e2.getMessage());
						
		Exception e3 = assertThrows(IllegalArgumentException.class,
								() -> new Student(firstName, lastName, id, email, "1234", maxCredits));
								assertEquals("Invalid password", e3.getMessage());
				
	}	
	
	/**
	 * Tests getMaxCredits method 
	 */
	@Test 
	public void testGetMaxCredits() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals(maxCredits, s1.getMaxCredits()); 
	}
	
	/**
	 * Tests getMaxCredits method for invalid values 
	 */
	@Test 
	public void testInvalidGetMaxCredits() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 20));
				assertEquals("Invalid max credits", e1.getMessage());
				
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> new Student(firstName, lastName, id, email, hashPW, 1));
						assertEquals("Invalid max credits", e2.getMessage());
	}
	
	/**
	 * Tests setFirstName
	 */
	@Test
	public void testInvalidSetFirstName() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
	 * Tests setMaxCredits
	 */
	@Test
	public void testInvalidSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", maxCredits);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCredits(2));
		assertEquals("Invalid max credits", e1.getMessage()); //Check correct exception message
		assertEquals(maxCredits, s.getMaxCredits()); //Check that maxCredits didn't change
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(20));
		assertEquals("Invalid max credits", e2.getMessage()); //Check correct exception message
		assertEquals(maxCredits, s.getMaxCredits()); //Check that maxCredits didn't change
	}
	
	/**
	 * Tests that the equals method works for all Course fields.
	 */
	@Test
	public void testEqualsObject() {
		Student c1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student c2 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student c3 = new Student(firstName, lastName, id, email, hashPW);
		Student c4 = new Student(firstName, lastName, id, email, hashPW);
		Student c5 = new Student("diya", lastName, id, email, hashPW, maxCredits);
		Student c6 = new Student(firstName, "patel", id, email, hashPW, maxCredits);
		Student c7 = new Student(firstName, lastName, "dapatel", email, hashPW, maxCredits);
		Student c8 = new Student(firstName, lastName, id, "dapatel@ncsu.edu", hashPW, maxCredits);
		Student c9 = new Student(firstName, lastName, id, email, "acd123", maxCredits);
		Student c10 = new Student(firstName, lastName, id, email, hashPW, 12);
		

		// Test for equality in both directions
		assertEquals(c1, c2);
		assertEquals(c1, c1);
		assertEquals(c2, c1);
		assertEquals(c3, c4);
		assertEquals(c4, c3);
	

		// Test for each of the fields
		assertNotEquals(c1, c5);
		assertNotEquals(null, c1);
		assertNotEquals(new StudentDirectory(), c1);
		
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
		Student c1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student c2 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student c3 = new Student(firstName, lastName, id, email, hashPW);
		Student c4 = new Student(firstName, lastName, id, email, hashPW);
		Student c5 = new Student("diya", lastName, id, email, hashPW, maxCredits);
		Student c6 = new Student(firstName, "patel", id, email, hashPW, maxCredits);
		Student c7 = new Student(firstName, lastName, "dapatel", email, hashPW, maxCredits);
		Student c8 = new Student(firstName, lastName, id, "dapatel@ncsu.edu", hashPW, maxCredits);
		Student c9 = new Student(firstName, lastName, id, email, "acd123", maxCredits);
		Student c10 = new Student(firstName, lastName, id, email, hashPW, 12);


		// Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());
		assertEquals(c3.hashCode(), c4.hashCode()); 

		// Test for each of the fields
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
		assertNotEquals(c1.hashCode(), c10.hashCode());

	}
	
	/**
	 * Tests compareTo method in Student.java 
	 */
	@Test 
	public void testCompareTo() {
		Student c4 = new Student(firstName, lastName, id, email, hashPW);
		Student c5 = new Student("diya", lastName, id, email, hashPW, maxCredits); 
		Student c7 = new Student(firstName, lastName, "dapatel", email, hashPW, maxCredits); 
		 //if this.firstName.cT firstName > 0 
		assertTrue(c4.compareTo(c5) > 0); 
		
		//if this.firstName.cT firstName < 0 
		assertTrue(c5.compareTo(c4) < 0); 
		
		//this this.id.cT id > 0 
		assertTrue(c7.compareTo(c5) > 0); 
		
		//students are equal 
		assertEquals(c4.compareTo(c4), 0); 
	}
	
	/**
	 * Tests Student.canAdd() 
	 */
	@Test 
	public void testCanAdd() {
		Student s = new Student(firstName, lastName, id, email, hashPW);
		Course c =  new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 250, MEETING_DAYS, START_TIME, END_TIME); 
		Course c1 = new Course("CSC116", "Intro to Programming", "002", 4, "spbalik", 250, "MW", 1120, 1310); 
		//Course c2 = new Course("MA305", "Linear Algebra", "002", 4, "spbalik", 250, "H", 1120, 1310); 
		Course ic = new Course("CSC316", "Intro to Programming", "003", 4, "spbalik", 250, "MW", 1140, 1310); 
		Course c3 = new Course("MA241", "Intro to Programming", "001", 4, "spbalik", 250, "MW", 1450, 1600); 
		Course c4 = new Course("MA242", "Intro to Programming", "001", 4, "spbalik", 250, "T", 1140, 1250);  
		Course ic2 = new Course("MA305", "Intro to Programming", "001", 4, "spbalik", 250, "T", 1340, 1450);   
		
		assertTrue(s.canAdd(c)); 
		s.addCourseToSchedule(c); 
		assertTrue(s.canAdd(c1));   
		s.addCourseToSchedule(c1); 
		s.addCourseToSchedule(c3); 
		s.addCourseToSchedule(c4); 
		
		assertFalse(s.canAdd(c1));
		assertFalse(s.canAdd(null)); 	
		assertFalse(s.canAdd(ic)); 
		assertFalse(s.canAdd(ic2)); 
		
	}


}
