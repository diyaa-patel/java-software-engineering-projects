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
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.app_manager.model.io.AppReader;
import edu.ncsu.csc216.app_manager.model.io.AppWriter;

/**
 * Tests AppList class methods 
 * @author Diya Patel 
 */
class AppListTest {
	
	/** note */ 
	private static final String NOTE = "Note 1";  
	/** summary */ 
	private static final String SUMMARY = "Application summary"; 
	/** app type */ 
	private static final AppType TYPE = AppType.NEW; 
	/** state */ 
	private static final String STATE = Application.REVIEW_NAME; 
	/** confirmed */ 
	private static final boolean CONFIRMED = false; 

	/**
	 * set up for the test cases
	 * @throws Exception if exception needs to be thrown 
	 */
	@BeforeEach
	void setUp() throws Exception {
		//set up 
	}

	/**
	 * Tests AppList constructor 
	 */
	@Test
	void testAppList() {
		 
		AppList list = new AppList(); 
		assertEquals(list.getApps().size(), 0); 
	}

	/**
	 * Tests AppList.addApp() method 
	 */
	void testAddApp() {
		AppList appList = new AppList();
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Application a = new Application(1, STATE, TYPE.toString(), SUMMARY, "", CONFIRMED, null, notes); 
		appList.addApp(TYPE, SUMMARY, NOTE);           
		assertEquals(a, appList.getAppById(1));       
	}

	/**
	 * Tests AppList.addApps 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testAddApps() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications_ordered.txt"); 
		appList.addApps(applications); 
		assertEquals(appList.getApps(), applications); 
	}
	
	/**
	 * Tests AppList.addApps 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testAddApps2() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/app2.txt"); 
		appList.addApps(applications); 
		assertEquals(6, appList.getApps().size()); 
		assertEquals(1, appList.getApps().get(0).getAppId()); 
	}

//	/**
//	 * Tests AppList.getApps() 
//	 * @throws FileNotFoundException if file is not found 
//	 */
//	@Test
//	void testGetApps() throws FileNotFoundException {
//		AppList appList = new AppList();
//		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/app1.txt"); 
//		appList.addApps(applications);          
//		
//		assertEquals(applications, appList.getApps()); 
//	}

	/**
	 * Tests AppList.getAppsByType() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testGetAppsByType() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		appList.addApps(applications); 
		AppWriter.writeAppsToFile("test-files/testing2.txt", appList.getAppsByType(Application.A_NEW)); 
		assertTrue(checkFiles("test-files/newApps.txt", "test-files/testing2.txt"));
		
	}
	
	/**
	 * Tests AppList.getAppsByType() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testGetAppsByType2() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/app1.txt"); 
		appList.addApps(applications);  
		assertEquals(4, appList.getAppsByType(Application.A_OLD).size());
		
	}
	
	/**
	 * Tests AppList.getAppsByType() 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testGetAppsByTypeNew() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/app1.txt"); 
		appList.addApps(applications);  
		assertEquals(2, appList.getAppsByType(Application.A_NEW).size());
		
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
	 * Tests AppList.getAppById() method 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testGetAppById() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		appList.addApps(applications); 
		
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Application a = new Application(1, STATE, Application.A_NEW, SUMMARY, "", CONFIRMED, null, notes); 
		assertEquals(a.toString(), appList.getAppById(1).toString());
	}

	/**
	 * Test AppList.executeCommand() 
	 */
	@Test
	void testExecuteCommand() {
		AppList appList = new AppList();
		
		Command c = new Command(CommandValue.ACCEPT, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		appList.addApp(TYPE, SUMMARY, NOTE); 
		Application a = appList.getApps().getFirst();
		appList.executeCommand(a.getAppId(), c);
		notes.add("[" + Application.INTERVIEW_NAME + "] " + "note 1"); 
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
	 * Tests AppList.DeleteAppById() method 
	 * @throws FileNotFoundException if file is not found 
	 */
	@Test
	void testDeleteAppById() throws FileNotFoundException {
		AppList appList = new AppList();
		ArrayList<Application> applications = AppReader.readAppsFromFile("test-files/valid_applications.txt"); 
		appList.addApps(applications);     
		
		appList.deleteAppById(3);
		
		
		assertNull(appList.getAppById(3));  
	}

}
