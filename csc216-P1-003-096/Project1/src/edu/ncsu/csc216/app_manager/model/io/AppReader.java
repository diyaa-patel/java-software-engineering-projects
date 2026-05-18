package edu.ncsu.csc216.app_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * Class which has methods to read applications from file and 
 * process appliction 
 * @author Diya Patel 
 */
public class AppReader {
	
	
	
	/**
	 * Processes a file containing application information and creates a List of Applications. 
	 * @param fileName the name of the file to read 
	 * @return applications list of applications from the file  
	 * @throws IllegalArgumentException "Unable to load file." if the file is invalid.  
	 * @throws FileNotFoundException if file is not found 
	 */
	public static ArrayList<Application> readAppsFromFile(String fileName) {
		Scanner fileReader;
		ArrayList<Application> applications = new ArrayList<Application>(); 
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		    fileReader.useDelimiter("\\r?\\n?[*]");
		    String line = ""; 
		    while (fileReader.hasNextLine()) {  
		    	line = fileReader.next(); 
				Application application = processApp(line + "\n"); 
				applications.add(application);    
		       
		    }
		 
		    fileReader.close(); 
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}  
		  
	    return applications; 
	           
	}
	
	
	/**
	 * process current application 
	 * @param line the line to process  
	 * @return application the application after it is processed 
	 */
	private static Application processApp(String line) {
		boolean isNegativeId = line.startsWith("-");
		Scanner scan = new Scanner(line);        
		scan.useDelimiter("\\r?\\n?[-]");
		Scanner applicationRow = new Scanner(scan.next());
		applicationRow.useDelimiter(",");
		ArrayList<String> notes = new ArrayList<>();
		String noteLine = ""; 
		while (scan.hasNextLine()) { 	 
	    	noteLine = scan.next().replace("-", "").trim(); 
			notes.add(noteLine);  
	    }
		
		String resolution = null; 
		int id = Integer.parseInt(applicationRow.next().replace("*", ""));
		if(isNegativeId) {
			id = id * -1;
		}
		String state = applicationRow.next();
		String appType = applicationRow.next();       
		String summary = applicationRow.next();
		String reviewer = applicationRow.next();
		boolean processPaperwork = applicationRow.next().equals("true");
		if(applicationRow.hasNext()) {
			resolution = applicationRow.next();
		}

		applicationRow.close(); 
		Application application = new Application(id, state, appType, summary, reviewer, processPaperwork, resolution, notes); 
	    scan.close(); 
		return application; 
	}


}