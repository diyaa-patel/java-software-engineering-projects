package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager class that uses the StudentDirectory class, CourseCatalog class, and 
 * and a inner class that work with the User class 
 */
public class RegistrationManager {
	
	/** Registration manager instance */
	private static RegistrationManager instance;
	/** course catalog */
	private CourseCatalog courseCatalog;
	/** student directory */
	private StudentDirectory studentDirectory;
	/** user registrar */
	private User registrar;
	/** current user */ 
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** registrar properties */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * registrar constructor which creates a new registrar 
	 */
	private RegistrationManager() {
		createRegistrar();
		studentDirectory = new StudentDirectory(); 
		courseCatalog = new CourseCatalog(); 
	}
	/**
	 * creates a registrar 
	 * @throws IllegalArgumentException if it cannot be created 
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
//			System.out.println("----");
//			System.out.println(prop.getProperty("first") + "- "+ prop.getProperty("last")  + "- "+ prop.getProperty("id")  + "- "+ prop.getProperty("email"));
//			System.out.println("----");
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * will make the pw into a hashPW 
	 * @param pw the pw of the user 
	 * @return Base64.getEncoder().encodeToString(digest1.digest()) if it can be hashed 
	 * @throws IllegalArgumentException if the pw cannot be hashed 
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * gets an instance of RegistrationManager class 
	 * @return instance the instance of RegistrationManager 
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * gets the course catalog 
	 * @return courseCatalog a catalog with course objects 
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * gets the student directory 
	 * @return studentDirectory a directory with student objects 
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

//	/**
//	 * checks if the currentUser's password matches 
//	 * @param id the id of the currentUser
//	 * @param password the password of the currentUser 
//	 * @return false if the password does not match the local hash pw 
//	 */
//	public boolean login(String id, String password) {
//		if(currentUser != null) {
//			throw new IllegalArgumentException("Already logined."); 
//		}
//		String localHashPW = hashPW(password);
//			
//		
//		if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
//			currentUser = registrar;
//			return true;
//		}
//		
//		try
//		{
//			Student s = studentDirectory.getStudentById(id);
//			if (s!= null && s.getPassword().equals(localHashPW)) 
//			{
//				currentUser = s;
//				return true;
//			}
//		}
//		catch(Exception e)
//		{
//			throw new IllegalArgumentException("User doesn't exist.");
//		}
//		return false;
//	}
	
	/**
	 * Login method
	 * @param id the id of the currentUser 
	 * @param password the password input 
	 * @return false if user not found or password mismatch 
	 * @throws IllegalArgumentException if user not found 
	 */
	public boolean login(String id, String password) {
		String localHashPW = hashPW(password);
//		System.out.println(id); 
//		System.out.println(registrar.getId());
		if (currentUser != null) { 
			return false; 
		}
//		String[][] dir = studentDirectory.getStudentDirectory();
//		System.out.println("??----");
//		for(int i = 0;i<dir.length ; i++) {
//
//			System.out.println(dir[i][0] + "- "+ dir[i][1] + "- "+ dir[i][2]);
//		}
//		System.out.println("??----");
		if (registrar.getId().equals(id)) {
			
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
					return true;
			}
		
		} 
		else {
			Student s = studentDirectory.getStudentById(id);
			if (s != null && s.getPassword().equals(localHashPW)) {
				currentUser = s;
					return true;
			}
			else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}
				return false;
	}

	/**
	 * will logout the currentUser 
	 */
	public void logout() {
		currentUser = null; 
		//currentUser = registrar; 
	}
	
	/**
	 * will return the current user of the registration manager. 
	 * @return currentUser the user that is currntly using the registration manager 
	 */
	public User getCurrentUser() {
		return currentUser; 
	}
	
	/**
	 * will clear data in studentDirectory and courseCatalog 
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * A inner class that will work with the User class 
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * @param firstName the first name of the User 
		 * @param lastName the last name of the User
		 * @param id the id of the User 
		 * @param email the email of the User 
		 * @param hashPW the hashPW of the User 
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	    	System.out.println();
	        //do nothing 
	    }
	} 
}