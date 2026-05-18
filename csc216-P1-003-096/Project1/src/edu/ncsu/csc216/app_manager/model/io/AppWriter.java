package edu.ncsu.csc216.app_manager.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.app_manager.model.application.Application;

/**
 * App Writer contains method to write applications to a file 
 * @author Diya Patel 
 */ 
public class AppWriter { 
	
	/**
	 * Writes the given list of Applcaitions to the file name provided. 
	 * @param fileName the file name to write 
	 * @param list the list of applicatons to write to the file 
	 * @throws FileNotFoundException if file is note found 
	 * @throws IllegalArgumentException "Unable to save file." if there are any errors while processing
	 */
	public static void writeAppsToFile(String fileName, List<Application> list) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(new File(fileName));
			for (Application a : list) {
	    		fileWriter.print(a.toString());
	    	}
		
			fileWriter.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new IllegalArgumentException("Unable to save file."); 
		}
		 
	}

}
