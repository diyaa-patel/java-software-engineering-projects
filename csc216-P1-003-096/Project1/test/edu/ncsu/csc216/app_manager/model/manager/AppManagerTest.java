package edu.ncsu.csc216.app_manager.model.manager;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.app_manager.model.io.AppReader;

class AppManagerTest {
	
	/** note */ 
	private static final String NOTE = "Note 1";  
	/** summary */ 
	private static final String SUMMARY = "Application summary"; 
	/** state */ 
	private static final String STATE = Application.REVIEW_NAME;  
	

	/**
	 * set up for tests
	 * @throws Exception if there is an exception to be thrown 
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//to implement 
	}

	
	/**
	 * Tests AppManager.getInstance() 
	 */
	@Test
	public void testGetInstance() {
		AppManager appManager = AppManager.getInstance();
		appManager.createNewAppList();
		Object[][] objects = appManager.getAppListAsArray();
		assertEquals(0, objects.length);
	}

	/**
	 * Tests AppManager.saveAppsToFile() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testSaveAppsToFile() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.loadAppsFromFile("test-files/valid_applications.txt");
		appManager.saveAppsToFile("test-files/saveApps.txt");  
		assertTrue(checkFiles("test-files/valid_applications_ordered.txt", "test-files/saveApps.txt")); 
	}

	/**
	 * Tests AppManager.loadAppsFromFile() 
	 * @throws FileNotFoundException if file is not found  
	 */
	@Test
	public void testLoadAppsFromFile() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.createNewAppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		appManager.loadAppsFromFile("test-files/valid_applications.txt");
		assertEquals(appManager.getAppListAsArray().length, applications.size());
		
		for(int i = 0; i < applications.size(); i++) {
			Application a = appManager.getAppById(applications.get(i).getAppId());
			assertEquals(a.toString(), applications.get(i).toString());
		}
		  
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

	/**
	 * Tests AppManager.createNewAppList() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testCreateNewAppList() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.createNewAppList(); 
		appManager.loadAppsFromFile("test-files/valid_applications.txt"); 
		appManager.createNewAppList();  
		assertEquals(appManager.getAppListAsArray().length, 0); 
	}

	/**
	 * Tests AppManager.getAppListAsArray() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testGetAppListAsArray() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.createNewAppList();
		appManager.loadAppsFromFile("test-files/testing2.txt");
		appManager.getAppListAsArray(); 
		
		Object[][] applications = new Object[2][4]; 
		applications[0][0] = 1;
		applications[0][1] = Application.REVIEW_NAME; 
		applications[0][2] = Application.A_NEW; 
		applications[0][3] = "Application summary"; 
		applications[1][0] = 14;
		applications[1][1] = Application.WAITLIST_NAME; 
		applications[1][2] = Application.A_NEW; 
		applications[1][3] = "Application summary"; 
		
		assertEquals(appManager.getAppListAsArray().length, applications.length);
		
		for(int i = 0; i < applications.length; i++) {
			for(int j = 0; j < applications[i].length; j++) {
			assertEquals(appManager.getAppListAsArray()[i][j], applications[i][j]);
			}
		}
	}

	/**
	 * Tests AppManager.getAppListAsArrayByAppType() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testGetAppListAsArrayByAppType() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.createNewAppList();
		appManager.loadAppsFromFile("test-files/valid_applications.txt");
		appManager.getAppListAsArray(); 
		
		Object[][] applications = new Object[2][4]; 
		applications[0][0] = 1;
		applications[0][1] = Application.REVIEW_NAME; 
		applications[0][2] = Application.A_NEW; 
		applications[0][3] = "Application summary"; 
		applications[1][0] = 14;
		applications[1][1] = Application.WAITLIST_NAME; 
		applications[1][2] = Application.A_NEW; 
		applications[1][3] = "Application summary"; 
		
		assertEquals(appManager.getAppListAsArrayByAppType(Application.A_NEW).length, applications.length);
		
		for(int i = 0; i < applications.length; i++) {
			for(int j = 0; j < applications[i].length; j++) {
			assertEquals(appManager.getAppListAsArrayByAppType(Application.A_NEW)[i][j], applications[i][j]);
			}
		}
	}

	/**
	 * Tests AppManager.getAppById() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testGetAppById() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.loadAppsFromFile("test-files/testing2.txt");
	
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + Application.REVIEW_NAME + "] " + "Note 1"; 
		notes.add(newNote); 
		
		Application a = new Application(1, Application.REVIEW_NAME, Application.A_NEW, "Application summary", "", false, null, notes); 
		assertEquals(a.toString(), appManager.getAppById(1).toString());
	}

	/**
	 * Tests AppManager.executeCommand() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testExecuteCommand() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.loadAppsFromFile("test-files/testing2.txt");

		Command c = new Command(CommandValue.ACCEPT, "203", null, "Note 2");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		String newNote2 = "[" + Application.INTERVIEW_NAME + "] " + "Note 2";
		notes.add(newNote2); 
		appManager.executeCommand(1, c);
		Application a = appManager.getAppById(1); 
		
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(null, a.getResolution());
		assertFalse(a.isProcessed()); 
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(1, a.getAppId());
		assertEquals(Application.A_OLD, a.getAppType()); 
	}

	/**
	 * Tests AppManager.deletedAppById() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	public void testDeleteAppById() throws FileNotFoundException {
		AppManager appManager = AppManager.getInstance();
		appManager.loadAppsFromFile("test-files/valid_applications.txt");    
		
		appManager.deleteAppById(3);
		
		assertNull(appManager.getAppById(3)); 
	}

//	/**
//	 * Tests AppManager.addAppToList() 
//	 */
//	@Test
//	public void testAddAppToList() {
//		fail("Not yet implemented");
//	}

	
}

