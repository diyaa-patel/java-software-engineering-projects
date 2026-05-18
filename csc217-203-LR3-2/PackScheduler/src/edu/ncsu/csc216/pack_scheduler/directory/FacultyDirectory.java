package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyDirectory has a LinkedList of Faculty
 * @author Diya Patel
 * @author Ramcharan Reddy
 * @author Preeti Joshi
 */
public class FacultyDirectory {
    
    /** List of faculty in the directory */
    private LinkedList<Faculty> facultyDirectory;
    /** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    
    /**
     * Constructor initializes facultyDirectory 
     */
    public FacultyDirectory() {
        newFacultyDirectory();
    }
    
    /**
     * Creates an empty faculty directory
     */
    public void newFacultyDirectory() {
        facultyDirectory = new LinkedList<Faculty>();
    }
    
    /**
     * Loads faculty records from a file
     * @param fileName name of the file to load
     * @throws IllegalArgumentException if the file cannot be found
     */
    public void loadFacultyFromFile(String fileName) {
        try {
            facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file " + fileName);
        }
    }
    
    /**
     * Adds faculty to list of faculty
     * @param firstName First Name of faculty
     * @param lastName Last Name of faculty
     * @param id ID of faculty
     * @param email email of faculty
     * @param password password of faculty
     * @param repeatPassword repeated password of faculty
     * @param maxCourses maximum courses of faculty
     * @return true if faculty is added, false if faculty already exists
     * @throws IllegalArgumentException if unable to hash password or passwords don't match
     */
    public boolean addFaculty(String firstName, String lastName, String id, String email, 
                            String password, String repeatPassword, int maxCourses) {
        String hashPW = "";
        String repeatHashPW = "";
        
        if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        
        hashPW = hashString(password);
        repeatHashPW = hashString(repeatPassword);
        
        if (!hashPW.equals(repeatHashPW)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
        
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            if (f.getId().equals(faculty.getId())) {
                return false;
            }
        }
        return facultyDirectory.add(faculty);
    }
    
    /**
     * Hashes a String according to the SHA-256 algorithm
     * @param toHash the String to hash
     * @return the hashed String
     * @throws IllegalArgumentException if cannot hash password
     */
    public static String hashString(String toHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(toHash.getBytes());
            return Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }
    
    /**
     * Removes faculty from directory
     * @param facultyId ID of faculty to remove
     * @return true if faculty is removed, false if faculty is not found
     */
    public boolean removeFaculty(String facultyId) {
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            if (f.getId().equals(facultyId)) {
                facultyDirectory.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a 2D array where each row is faculty and columns are firstName, lastName and id
     * @return 2D array of faculty information
     */
    public String[][] getFacultyDirectory() 
    {
        String[][] directory = new String[facultyDirectory.size()][3];
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            directory[i][0] = f.getFirstName();
            directory[i][1] = f.getLastName();
            directory[i][2] = f.getId();
        }
        return directory;
    }
    
    /**
     * Saves Faculty Directory to the given file
     * @param fileName name of file to save to
     * @throws IllegalArgumentException if unable to write to file
     */
    public void saveFacultyDirectory(String fileName) {
        try {
            FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to write to file " + fileName);
        }
    }
    
    /**
     * Returns the faculty instance with the given id
     * @param id ID of faculty to find
     * @return faculty if found, null if not found
     */
    public Faculty getFacultyById(String id) {
    	if (id == null) {
            return null;
        }
        for (int i = 0; i < facultyDirectory.size(); i++) {
        	if (facultyDirectory.get(i).getId().equals(id)) {
            	return facultyDirectory.get(i);
            }
        }
        return null;
    }
}