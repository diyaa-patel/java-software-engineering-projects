package edu.ncsu.csc216.app_manager.model.application;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.app_manager.model.command.Command.Resolution;

/** 
 * Tests for Application Class 
 * @author Diya Patel 
 */
class ApplicationTest {

	/** note */ 
	private static final String NOTE = "note 1"; 
	/** reviewer Id */ 
	private static final int ID = 8; 
	/** summary */ 
	private static final String SUMMARY = "Application summary"; 
	/** app type */ 
	private static final AppType TYPE = AppType.NEW; 
	/** state */ 
	private static final String STATE = Application.REVIEW_NAME; 
	/** reviewer */
	private static final String REVIEWER = null;
	/** resolution */ 
	private static final String RESOLUTION = null; 
	/** confirmed */ 
	private static final boolean CONFIRMED = false; 
	
	/**
	 * Set up for tests 
	 * @throws Exception if there is an exception to throw 
	 */
	@BeforeEach
	public void setUp() throws Exception {
		//to implement
	}

	/**
	 * Tests Application Constructor with 4 parameters 
	 */
	@Test 
	public void testApplication1() {
		// Test a valid construction
		Application a = assertDoesNotThrow(
				() -> new Application(ID, TYPE, SUMMARY, NOTE),
				"Should not throw exception"); 
		
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + a.getStateName() + "] " + NOTE; 
		notes.add(newNote); 
		
		assertAll("Application", 
				() -> assertEquals(ID, a.getAppId(), "incorrect app id"), 
				() -> assertEquals(Application.A_NEW, a.getAppType(), "incorrect app type"),
				() -> assertEquals(SUMMARY, a.getSummary(), "incorrect summary"), 
				() -> assertEquals(notes, a.getNotes(), "incorrect notes"));
		
	}
	
	/**
	 * Tests Application Constructor with 8 parameters 
	 */
	@Test 
	public void testApplication2() {
		// Test a valid construction
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Application a = assertDoesNotThrow(
				() -> new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes),
				"Should not throw exception"); 
		
		assertAll("Application", 
				() -> assertEquals(ID, a.getAppId(), "incorrect app id"), 
				() -> assertEquals(STATE, a.getStateName(), "incorrect state"),
				() -> assertEquals(TYPE.toString(), a.getAppType(), "incorrect app type"),
				() -> assertEquals(SUMMARY, a.getSummary(), "incorrect summary"),
				() -> assertEquals(REVIEWER, a.getReviewer(), "incorrect reviewer"),
				() -> assertEquals(CONFIRMED, a.isProcessed(), "incorrect boolean"),
				() -> assertEquals(null, a.getResolution(), "incorrect resolution"),
				() -> assertEquals(notes, a.getNotes(), "incorrect notes"));
		
	}
	
	/**
	 * Tests Application Constructor with 8 parameters 
	 */
	@Test 
	public void testApplication3() {
		// Test a valid construction
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Application a = assertDoesNotThrow(
				() -> new Application(7, "RefCheck", "Old", "Application Summary", "reviewer", true, "", notes),
				"Should not throw exception"); 
		
		assertAll("Application", 
				() -> assertEquals(7, a.getAppId(), "incorrect app id"), 
				() -> assertEquals("RefCheck", a.getStateName(), "incorrect state"),
				() -> assertEquals("Old", a.getAppType(), "incorrect app type"),
				() -> assertEquals("Application Summary", a.getSummary(), "incorrect summary"),
				() -> assertEquals("reviewer", a.getReviewer(), "incorrect reviewer"),
				() -> assertEquals(true, a.isProcessed(), "incorrect boolean"),
				() -> assertEquals(null, a.getResolution(), "incorrect resolution"),
				() -> assertEquals(notes, a.getNotes(), "incorrect notes"));
		
	}
	
	/**
	 * Tests Application Constructor with 8 parameters 
	 */
	@Test 
	public void testApplicationInvalid() {
		// Test a invalid construction
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Application(7, "Review", "Oldgy", "summary", "", false, "", notes)); 
		assertEquals("Application cannot be constructed.", e.getMessage(), "Incorrect exception thrown with invalid command"); 	
		
		
		
	}
	
	/**
	 * Tests Application.getAppId() 
	 */
	@Test
	public void testGetAppId() {
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(ID, a.getAppId());
	}

	/**
	 * Tests Application.setAppId() 
	 * @param num of the id which will be set 
	 */
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 25})
	public void testSetAppId(int num) {
		// Testing valid numbers 
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);
		Application application = assertDoesNotThrow(
				() -> new Application(num, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes),
				"Should not throw exception");
		assertEquals(num, application.getAppId(), "Failed test with valid id - " + num);
	}

	/**
	 * Tests Application.getSummary()
	 */
	@Test
	public void testGetSummary() {
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(SUMMARY, a.getSummary());
	}

	/**
	 * Tests Application.setSummary() 
	 * @param summary the summaries to be tested 
	 */
	@ParameterizedTest
	@ValueSource(strings = {"summary", "summary 2", "summary 3"})
	public void testSetSummary(String summary) {
		// Testing valid summaries 
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);
		Application application = assertDoesNotThrow(
				() -> new Application(ID, STATE, TYPE.toString(), summary, REVIEWER, CONFIRMED, RESOLUTION, notes),
				"Should not throw exception");
		assertEquals(summary, application.getSummary(), "Failed test with valid summary - " + summary);
	}

	/**
	 * Tests Application.getReviewer() 
	 */
	@Test
	public void testGetReviewer() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(REVIEWER, a.getReviewer());
	}

	/**
	 * Tests Application.setReviewer() 
	 * @param reviewer the different string reviewers 
	 */
	@ParameterizedTest
	@ValueSource(strings = {"reviewer", "reviewer 2", "reviewer 3"})
	public void testSetReviewer(String reviewer) {
		// Testing valid summaries 
				ArrayList<String> notes = new ArrayList<String>(); 
				String newNote = "[" + STATE + "] " + NOTE; 
				notes.add(newNote);
				Application application = assertDoesNotThrow(
						() -> new Application(ID, STATE, TYPE.toString(), SUMMARY, reviewer, CONFIRMED, RESOLUTION, notes),
						"Should not throw exception");
				assertEquals(reviewer, application.getReviewer(), "Failed test with valid reviewer - " + reviewer);
	}

	/**
	 * Tests Application.isProcessed() 
	 */
	@Test
	public void testIsProcessed() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(CONFIRMED, a.isProcessed());
	}

	/**
	 * Tests Application.setProcessPaperwork() 
	 * @param confirmed the confirmation 
	 */
	@ParameterizedTest
	@ValueSource(booleans = {false})
	public void testSetProcessPaperwork(boolean confirmed) {
		// Testing valid summaries 
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);
		Application application = assertDoesNotThrow(
				() -> new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, confirmed, RESOLUTION, notes),
				"Should not throw exception");
		assertEquals(confirmed, application.isProcessed(), "Failed test with valid boolean - " + confirmed);
	}

	/**
	 * Tests Application.getNotes() 
	 */
	@Test
	public void testGetNotes() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(notes, a.getNotes());
	}

	/**
	 * Tests Application.setNotes() 
	 */
	@Test
	public void testSetNotes() {
		// Testing valid notes 
		ArrayList<String> notes = new ArrayList<String>(); 
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote); 
		
		Application application = assertDoesNotThrow(
				() -> new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes),
				"Should not throw exception");
 
		assertEquals(notes, application.getNotes(), "Failed test with valid notes - " + notes);
	}

	/**
	 * Tests Application.getResolution() 
	 */
	@Test
	public void testGetResolution() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(null, a.getResolution());
	}

	/**
	 * Tests Application.setResolution() 
	 * @param resolution the resolution to set 
	 */
	@ParameterizedTest
	@ValueSource(strings = {Command.R_OFFERCOMPLETED})
	public void testSetResolution(String resolution) {
		// Testing valid summaries 
				ArrayList<String> notes = new ArrayList<String>(); 
				String newNote = "[" + STATE + "] " + NOTE; 
				notes.add(newNote);
				Application application = assertDoesNotThrow(
						() -> new Application(ID, STATE, "New", SUMMARY, REVIEWER, CONFIRMED, resolution, notes),
						"Should not throw exception");
				assertEquals(resolution, application.getResolution(), "Failed test with valid resolution - " + resolution);
	}


	/**
	 * Tests Application.getAppType() 
	 */
	@Test
	public void testGetApptype() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(TYPE.toString(), a.getAppType());
	}


	/**
	 * Tests Application.getStateName() 
	 */
	@Test
	public void testGetStateName() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(STATE, a.getStateName());
	}

	/**
	 * Tests Application.getNotesString() 
	 */
	@Test
	public void testGetNotesString() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE;
		String newNote2 = "[" + Application.REFCHK_NAME + "] " + NOTE + "2"; 
		notes.add(newNote); 
		notes.add(newNote2);  
		
		String notesString = "-" +  newNote + "\n-" +  newNote2 + "\n";
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(notesString, a.getNotesString());
	}

	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateReviewStateAcceptCommand() {
		Command c = new Command(CommandValue.ACCEPT, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		a.update(c); 
		notes.add("note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(null, a.getResolution());
		assertFalse(a.isProcessed());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateReviewStateAcceptInvalidCommand() {
		Command c = new Command(CommandValue.REOPEN, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		 
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c));
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateRefCheckStateAcceptCommand() {
		Command c = new Command(CommandValue.ACCEPT, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + Application.REFCHK_NAME + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.REFCHK_NAME, TYPE.toString(), SUMMARY, "reviewer", true, RESOLUTION, notes);  
		a.update(c); 
		notes.add("note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.OFFER_NAME, a.getStateName());
		assertTrue(a.isProcessed());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(TYPE.toString(), a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateRefCheckStateRejectCommand() {
		Command c = new Command(CommandValue.REJECT, "203", Resolution.REFCHKCOMPLETED, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + Application.REFCHK_NAME + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.REFCHK_NAME, TYPE.toString(), SUMMARY, "reviewer", true, RESOLUTION, notes);  
		a.update(c); 
		notes.add("note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals(Application.CLOSED_NAME, a.getStateName());
		assertEquals(Command.R_REFCHKCOMPLETED, a.getResolution());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(TYPE.toString(), a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateRefCheckStateAcceptInvalidCommand() {
		
		Command c = new Command(CommandValue.REOPEN, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + Application.REFCHK_NAME + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.REFCHK_NAME, TYPE.toString(), SUMMARY, "reviewer", true, RESOLUTION, notes);  
		 
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c));
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateInterviewStateAcceptCommand() {
		Command c = new Command(CommandValue.ACCEPT, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		
		Application a = new Application(ID, "Interview", TYPE.toString(), SUMMARY, "reviewer", CONFIRMED, RESOLUTION, notes);  
		a.update(c);
		notes.add("note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.REFCHK_NAME, a.getStateName());
		assertEquals(null, a.getResolution());
		assertTrue(a.isProcessed()); 
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(TYPE.toString(), a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateInterviewNoReviewerOnAccept() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		  
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Command(CommandValue.ACCEPT, null, null, "note 1")); 
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command"); 	
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateInterviewToCLosedWithNoReviewer() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
		Command c2 = new Command(CommandValue.REJECT, null, Resolution.INTCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.CLOSED_NAME, a.getStateName());
		assertEquals(Command.R_INTCOMPLETED, a.getResolution());  
		assertEquals(null, a.getReviewer()); 
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateInterviewToWaitlistNoReviewer() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
		Command c2 = new Command(CommandValue.STANDBY, null, Resolution.INTCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.WAITLIST_NAME, a.getStateName());
		assertEquals(Command.R_INTCOMPLETED, a.getResolution());  
		assertEquals(null, a.getReviewer());   
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateReviewToWaitlistNewAppWithReviewer() {
		Application a = new Application(ID, AppType.NEW, SUMMARY, "Note 1"); 
		
		Command c2 = new Command(CommandValue.STANDBY, "reviewer", Resolution.REVCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.WAITLIST_NAME, a.getStateName());
		assertEquals(Command.R_REVCOMPLETED, a.getResolution());  
		assertEquals("reviewer", a.getReviewer());   
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateWaitlisttoRefCheck() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
		Command c2 = new Command(CommandValue.STANDBY, null, Resolution.INTCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.WAITLIST_NAME, a.getStateName());
		assertEquals(Command.R_INTCOMPLETED, a.getResolution());  
		assertEquals(null, a.getReviewer()); 
		
		Command c3 = new Command(CommandValue.REOPEN, null, null, "Note 1");
		a.update(c3);
		assertEquals(Application.REFCHK_NAME, a.getStateName());
		assertEquals(null, a.getResolution());  
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateWaitlisttoReview() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		
		Command c2 = new Command(CommandValue.STANDBY, null, Resolution.REVCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.WAITLIST_NAME, a.getStateName());
		assertEquals(Command.R_REVCOMPLETED, a.getResolution());  
		assertEquals(null, a.getReviewer()); 
		
		Command c3 = new Command(CommandValue.REOPEN, null, null, "Note 1");
		a.update(c3);
		assertEquals(Application.REVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		assertEquals(null, a.getResolution()); 
	}

	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateReviewToWaitlistOldApp() {
		Application a = new Application(ID, AppType.OLD, SUMMARY, "Note 1"); 
		
		Command c2 = new Command(CommandValue.STANDBY, "reviewer", Resolution.REVCOMPLETED, "Note 1");
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c2)); 
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		  
	}

	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateOfferToClosedAccept() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "Note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
		Command c2 = new Command(CommandValue.ACCEPT, "213", null, "Note 1");
		a.update(c2);
		assertEquals(Application.REFCHK_NAME, a.getStateName());   
		assertEquals("213", a.getReviewer());
		
		Command c3 = new Command(CommandValue.ACCEPT, "213", null, "Note 1");
		a.update(c3);
		assertEquals(Application.OFFER_NAME, a.getStateName());   
		assertEquals("213", a.getReviewer());
		
		Command c4 = new Command(CommandValue.ACCEPT, "213", null, "Note 1");
		a.update(c4);
		assertEquals(Application.CLOSED_NAME, a.getStateName()); 
		assertEquals(Application.A_HIRED, a.getAppType()); 
		
		
	} 
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateOfferToClosedReject() {
		Application a = new Application(ID, TYPE, SUMMARY, "Note 1"); 
		Command c1 = new Command(CommandValue.ACCEPT, "203", null, "Note 1");   
		a.update(c1);
		assertEquals("203", a.getReviewer());
		assertEquals(Application.INTERVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
		Command c2 = new Command(CommandValue.ACCEPT, "213", null, "Note 1");
		a.update(c2);
		assertEquals(Application.REFCHK_NAME, a.getStateName());   
		assertEquals("213", a.getReviewer());
		
		Command c3 = new Command(CommandValue.ACCEPT, "213", null, "Note 1");
		a.update(c3);
		assertEquals(Application.OFFER_NAME, a.getStateName());   
		assertEquals("213", a.getReviewer());
		
		Command c4 = new Command(CommandValue.REJECT, null, Resolution.OFFERCOMPLETED, "Note 1");
		a.update(c4);
		assertEquals(Application.CLOSED_NAME, a.getStateName()); 
		assertEquals(Command.R_OFFERCOMPLETED, a.getResolution()); 
		assertEquals(null, a.getReviewer());
		
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testInvalidUpdateReviewInvalidReopen() {
		Application a = new Application(ID, AppType.OLD, SUMMARY, "Note 1"); 
		Application b = new Application(ID, AppType.OLD, SUMMARY, "Note 1");  
		
		Command c1 = new Command(CommandValue.REOPEN, "203", null, "Note 1"); 
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c1));
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		
		assertEquals(Application.A_OLD, a.getAppType()); 
		assertEquals(a.toString(), b.toString()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateReviewToClosedNewAppWithReviewer() {
		Application a = new Application(ID, AppType.NEW, SUMMARY, "Note 1");   
		Command c1 = new Command(CommandValue.REJECT, "reviewer", Resolution.REVCOMPLETED, "Note 1"); 
		a.update(c1); 
		assertEquals(Application.CLOSED_NAME, a.getStateName()); 
		assertEquals(Command.R_REVCOMPLETED, a.getResolution()); 
		assertEquals("reviewer", a.getReviewer());
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateOfferStateAcceptCommand() {
		Command c = new Command(CommandValue.ACCEPT, "203", null, "note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, "Offer", TYPE.toString(), SUMMARY, "reviewer", CONFIRMED, RESOLUTION, notes);  
		a.update(c);
		notes.add("note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.CLOSED_NAME, a.getStateName());
		assertEquals(Command.R_OFFERCOMPLETED, a.getResolution());
		assertTrue(a.isProcessed()); 
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(Application.A_HIRED, a.getAppType()); 
		
	}
	
	
	
//	/**
//	 * Tests Application.update() 
//	 */
//	@Test
//	public void testUpdateWaitListStateReopen() {
//		Command c = new Command(CommandValue.REOPEN, "203", Resolution.INTCOMPLETED, "Note 1");
//		ArrayList<String> notes = new ArrayList<String>();
//		String newNote = "[" + STATE + "] " + NOTE; 
//		notes.add(newNote);  
//		
//		Application a = new Application(ID, "Waitlist", TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, Command.R_REFCHKCOMPLETED, notes);  
//		a.update(c);
//		notes.add("Note 1"); 
//		assertEquals(notes, a.getNotes());
//		assertEquals("203", a.getReviewer());
//		assertEquals(Application.REFCHK_NAME, a.getStateName());
//		assertEquals(Command.R_REFCHKCOMPLETED, a.getResolution());
//		assertTrue(a.isProcessed()); 
//		assertEquals(SUMMARY, a.getSummary()); 
//		assertEquals(ID, a.getAppId());
//		assertEquals(TYPE.toString(), a.getAppType()); 
//		
//	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateWaitListStateReopenInvalidCommand() {
		Command c = new Command(CommandValue.REOPEN, "203", Resolution.REVCOMPLETED, "Note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, "Waitlist", TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, Command.R_REVCOMPLETED, notes);  
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c));
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		
 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateCloseStateReopen() {
		Command c = new Command(CommandValue.REOPEN, "203", Resolution.REVCOMPLETED, "Note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.CLOSED_NAME, "New", SUMMARY, REVIEWER, CONFIRMED, Command.R_REVCOMPLETED, notes);  
		a.update(c);
		notes.add("Note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals(Application.REVIEW_NAME, a.getStateName());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateCloseStateReopenInvalidCommand() {
		Command c = new Command(CommandValue.REJECT, "203", Resolution.REVCOMPLETED, "Note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.CLOSED_NAME, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, Command.R_REVCOMPLETED, notes);  
		Exception e = assertThrows(UnsupportedOperationException.class,
				() -> a.update(c));
		assertEquals("Invalid information.", e.getMessage(), "Incorrect exception thrown with invalid command");
		
 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateCloseStateToReview() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, AppType.NEW, SUMMARY, "Note 1");  
		assertEquals(a.getStateName(), Application.REVIEW_NAME); 
		Command c1 = new Command(CommandValue.REJECT, null, Resolution.REVCOMPLETED, "Note 1");
		a.update(c1);
		assertEquals(Application.CLOSED_NAME, a.getStateName());
		assertEquals(Command.R_REVCOMPLETED, a.getResolution());  
		
		Command c2 = new Command(CommandValue.REOPEN, "203", Resolution.REVCOMPLETED, "Note 1");
		a.update(c2);
		assertEquals(Application.REVIEW_NAME, a.getStateName());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
	}

	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateInterviewStateAccept() {
		Command c = new Command(CommandValue.ACCEPT, "203", null, "Note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.INTERVIEW_NAME, "Old", SUMMARY, "reviewer", CONFIRMED, null, notes);  
		a.update(c);
		notes.add("Note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals("203", a.getReviewer());
		assertEquals(Application.REFCHK_NAME, a.getStateName());
		assertTrue(a.isProcessed());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(Application.A_OLD, a.getAppType()); 
		
	}
	
	/**
	 * Tests Application.update() 
	 */
	@Test
	public void testUpdateInterviewStateReject() {
		Command c = new Command(CommandValue.REJECT, "203", Resolution.REVCOMPLETED, "Note 1");
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE; 
		notes.add(newNote);  
		
		Application a = new Application(ID, Application.INTERVIEW_NAME, "Old", SUMMARY, "reviewer", CONFIRMED, null, notes);  
		a.update(c);
		notes.add("Note 1"); 
		assertEquals(notes, a.getNotes());
		assertEquals(Application.CLOSED_NAME, a.getStateName());
		assertEquals(Command.R_INTCOMPLETED, a.getResolution());
		assertEquals(SUMMARY, a.getSummary()); 
		assertEquals(ID, a.getAppId());
		assertEquals(Application.A_OLD, a.getAppType()); 
		 
	}


	/**
	 * Tests Application.toString() 
	 */
	@Test
	public void testToString() {
		ArrayList<String> notes = new ArrayList<String>();
		String newNote = "[" + STATE + "] " + NOTE;
		notes.add(newNote); 
		
		String newString = "*" + ID + "," + STATE + "," + TYPE + "," + SUMMARY + "," + REVIEWER + "," + CONFIRMED + "," + "" +  "\n" + "-" +  newNote + "\n";
		Application a = new Application(ID, STATE, TYPE.toString(), SUMMARY, REVIEWER, CONFIRMED, RESOLUTION, notes);  
		assertEquals(newString, a.toString());
	}
	
	
	
	
	

}