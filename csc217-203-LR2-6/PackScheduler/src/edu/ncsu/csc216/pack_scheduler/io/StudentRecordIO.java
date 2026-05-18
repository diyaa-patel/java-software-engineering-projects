package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * StudentRecordIO reads records from the file and returns the records in a arraylist. 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
public class StudentRecordIO {

	/**
	 * Reads the records from the file 
	 * @param fileName the name of the file
	 * @return records all the records in the file  
	 * @throws FileNotFoundException if the file does not exist 
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Student> records = new SortedList<Student>(); 
	    while (fileReader.hasNextLine()) {
			try { 
				Student record = readStudent(fileReader.nextLine()); 
				records.add(record); 
	        } 
	        catch (IllegalArgumentException e) {
	        	System.out.println(); 
	        } 
	       
	    }
	    fileReader.close();
	    return records;          
	}

	/**
	 * This class will write the Student s in student directory to the file represented by the fileName s
	 * @param fileName the name of the file 
	 * @param studentDirectory the directory for students 
	 * @throws IOException if unable to write file 
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < studentDirectory.size(); i++) {
    	    fileWriter.println(studentDirectory.get(i).toString());
    	}

    	fileWriter.close();
        
		
	}
	
	  /**
	    * This method loads the line into scanner object and reads the token to create
	    * course object 
	    * @param nextLine the next line argument 
	    * @return student instance of course object 
	    */
		private static Student readStudent(String nextLine) {   
			Scanner scan = new Scanner(nextLine);        
			scan.useDelimiter(","); 
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			String password = scan.next();
			int maxCredits = 0;
			if(scan.hasNext()) {
				maxCredits = Integer.parseInt(scan.next());
			}
					
			Student record; 
			if(maxCredits == 0) {
				record = new Student (firstName, lastName, id, email, password); 
			}
			else {
				record = new Student (firstName, lastName, id, email, password, maxCredits); 
			} 
		    scan.close(); 
			return record; 
			
		}    
	
	    

}
