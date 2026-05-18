package edu.ncsu.csc216.app_manager.model.io;

 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;

class AppWriterTest {

	/**
	 * Set up for tests 
	 * @throws Exception if there is an exception to throw 
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//to implement 
	}

	/**
	 * Tests AppWriter.writeAppsToFile() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testWriteAppsToFile() throws FileNotFoundException {
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		 
		AppWriter.writeAppsToFile("test-files/new_applications.txt", applications); 
		assertTrue(checkFiles("test-files/valid_applications_output.txt", "test-files/new_applications.txt"));  
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 * @return true if file matches
	 */
	private boolean checkFiles(String expFile, String actFile) {
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
				return false;
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
				return false;
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
		return true;
	}  

}