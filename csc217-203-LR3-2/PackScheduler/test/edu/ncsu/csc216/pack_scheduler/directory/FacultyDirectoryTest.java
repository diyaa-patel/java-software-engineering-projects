package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * FacultyDirectory represents a list of Faculty 
 * @author Diya Patel
 * @author Preeti Joshi
 * @author Ramcharan Reddy
 */
public class FacultyDirectoryTest {

	/** Valid records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max courses */
	private static final int MAX_COURSES = 2;
	/** Test max courses */
	private static final int MAX_COURSES_BAD = 4;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		//Test invalid file
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.loadFacultyFromFile("invalid file"));
		assertEquals("Unable to read file invalid file", e1.getMessage());
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		// Test valid Faculty with maxCredits < 3
        
        
        Exception e3 = assertThrows(IllegalArgumentException.class, () -> 
        	sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES_BAD));
        assertEquals("Invalid max courses", e3.getMessage());
    
        // Test valid Faculty with 3 <= maxCredits <= Faculty.MAX_CREDITS
        boolean result3 = sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
        assertFalse(result3);
        
        // Test invalid Faculty passwords don't match
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> 
                sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", "NMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", MAX_COURSES));
        assertEquals("Passwords do not match", e1.getMessage());

        // Test invalid Faculty password is null
        Exception e2 = assertThrows(IllegalArgumentException.class, () ->
                sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", null, null, MAX_COURSES));
        assertEquals("Invalid password", e2.getMessage());

	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add faculty and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertFalse(sd.removeFaculty("efrost"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(8, facultyDirectory.length);
		assertEquals("Elton", facultyDirectory[5][0]);
		assertEquals("Briggs", facultyDirectory[5][1]);
		assertEquals("ebriggs", facultyDirectory[5][2]);
	}
	
	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testgetFacultyByID() {
		FacultyDirectory sd = new FacultyDirectory();
		assertEquals(null, sd.getFacultyById(null));
	}

	
	/**
	 * Test for FacultyDirectory.getFacultyById() 
	 */
	@Test 
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		sd.addFaculty("Elton", "Briggs", "ebriggs", "ebriggs@ncsu.edu", "pw", "pw", 3);
		String hashPW = FacultyDirectory.hashString("pw");
		Faculty fac = new Faculty("Elton", "Briggs", "ebriggs", "ebriggs@ncsu.edu", hashPW, 3);
		assertEquals(fac, sd.getFacultyById("ebriggs")); 
	}


}
