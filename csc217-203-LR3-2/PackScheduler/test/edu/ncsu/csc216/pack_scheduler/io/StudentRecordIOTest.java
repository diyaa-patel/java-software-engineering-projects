package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.io.FileInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO Class 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
class StudentRecordIOTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** Valid course records without max credits */ 
	private final String validTestFileWithoutMaxCredits = "test-files/student_without_maxCredits.txt"; 
	/** Valid Student 0*/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Valid Student 1*/ 
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Valid Student 2*/ 
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Valid Student 3*/ 
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Valid Student 4*/ 
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Valid Student 5*/ 
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Valid Student 6*/ 
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Valid Student 7*/ 
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** Valid Student 8*/ 
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Valid Student 9*/ 
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/**Private String[] ValidStudents*/
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};
	/**Private String hashPW */
	private String hashPW;
	/** Private String HASH_ALGORITHM*/ 
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Resets student_records.txt for use in other tests.
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Tests readValidStudentRecords().
	 */
	@Test
	public void testReadValidCourseRecords() throws FileNotFoundException {
		SortedList<Student> records = StudentRecordIO.readStudentRecords(validTestFile);
		assertEquals(10, records.size());
	}
	

	/**
	 * Tests readInvalidStudentRecords().
	 */
	@Test
	public void testReadInvalidCourseRecords() throws FileNotFoundException {
		SortedList<Student> records;
		records = StudentRecordIO.readStudentRecords(invalidTestFile);
		assertEquals(0, records.size());
	}
	
	/**
	 * Tests readValidStudentRecords() when there is no max credits. 
	 */
	@Test
	public void testReadValidCourseRecordsWithoutMaxCredits() throws FileNotFoundException {
		SortedList<Student> records = StudentRecordIO.readStudentRecords(validTestFileWithoutMaxCredits);
		assertEquals(3, records.size());
	}
	

	/**
	 * Tests the writeStudentRecords() Method 
	 */
	@Test
	void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
		
		
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}  

}