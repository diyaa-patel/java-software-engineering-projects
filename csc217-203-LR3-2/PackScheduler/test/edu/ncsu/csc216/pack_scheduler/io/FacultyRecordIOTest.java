package edu.ncsu.csc216.pack_scheduler.io;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * test class for FacultyRecordIO
 */
public class FacultyRecordIOTest {

	@Test
	void readFacultyRecordsTest() {
		LinkedList<Faculty> faculty = null;
		try {
			faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		
		assertEquals("Ashely", faculty.get(0).getFirstName());
		assertEquals("Meadows", faculty.get(1).getLastName());
		assertEquals("bbrewer", faculty.get(2).getId());
		assertEquals("Fusce.dolor.quam@amalesuadaid.net", faculty.get(3).getEmail());
		assertEquals("MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", faculty.get(4).getPassword());
		assertEquals(3, faculty.get(5).getMaxCourses());
		try {
			assertEquals(8, FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt").size());
		} catch (FileNotFoundException e) {
			
			fail();
		}
	}
	
	@Test
	void writeFacultyRecordsTest() throws IOException {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Ashely", "Witt",  "awitt", "mollis@Fuscealiquetmagna.net", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 2));
		faculty.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 3));
		faculty.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 1));
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/rcr.txt", faculty);
		} catch (FileNotFoundException e) {
			fail();
		}
		checkFiles("test-files/expected_faculty_records.txt", "test-files/rcr.txt");
	}
	
	@Test
	void testReadFacultyRecordsWithInvalidMaxCourses() {
	    LinkedList<Faculty> faculty = null;
	    try {
	        // Test file should contain records with invalid maxCourses values
	        faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records_invalid.txt");
	        
	        // Verify that only valid records were read
	        assertEquals(2, faculty.size());
	        
	        // Verify the contents of the valid records
	        Faculty faculty1 = faculty.get(0);
	        assertEquals("John", faculty1.getFirstName());
	        assertEquals("Smith", faculty1.getLastName());
	        assertEquals("jsmith", faculty1.getId());
	        assertEquals("jsmith@ncsu.edu", faculty1.getEmail());
	        assertEquals("hashedpassword", faculty1.getPassword());
	        assertEquals(2, faculty1.getMaxCourses());
	        
	    } catch (FileNotFoundException e) {
	        fail("Unable to read file");
	    }
	}
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
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