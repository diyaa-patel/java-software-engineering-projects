package edu.ncsu.csc216.app_manager.model.io;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.app_manager.model.application.Application;

class AppReaderTest {

	/**
	 * Set up for tests 
	 * @throws Exception if there is an exception to throw 
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//to implement 
	}

	/**
	 * Tests appReader.readAppsFromFile() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testReadAppsFromFile() throws FileNotFoundException { 
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		assertEquals(applications.size(), 6); 
		//add to make sure its equal data for first application 
	}
	
	/**
	 * Tests appReader.readAppsFromFile() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testReadAppsFromFile2() throws FileNotFoundException { 
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/app2.txt"); 
		assertEquals(applications.size(), 7); 
		//add to make sure its equal data for first application 
	}
	
	/**
	 * Tests appReader.readAppsFromFile() 
	 * @throws IllegalArgumentException because file is unable to load 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testReadAppsFromFileInvalid() throws FileNotFoundException {  
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> AppReader.readAppsFromFile("test-files/app7.txt")); 
		assertEquals("Unable to load file.", e.getMessage(), "Incorrect exception thrown with invalid command");
	}
	
	/**
	 * Tests appReader.readAppsFromFile() 
	 * @throws IllegalArgumentException because negative id 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testReadAppsFromFileInvalid2() throws FileNotFoundException {  
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> AppReader.readAppsFromFile("test-files/app18.txt")); 
		assertEquals("Application cannot be constructed.", e.getMessage(), "Incorrect exception thrown with invalid command");
	}


}