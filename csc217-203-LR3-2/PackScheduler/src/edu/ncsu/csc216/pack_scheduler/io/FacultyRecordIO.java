package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Handles reading and writing of Faculty records to and from files
 */
public class FacultyRecordIO {
    
    /**
     * Reads faculty records from a file and returns them in a LinkedList
     * @param fileName name of the file containing faculty records
     * @return LinkedList of faculty records
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        LinkedList<Faculty> records = new LinkedList<Faculty>();
        
        while (fileReader.hasNextLine()) {
            try {
                String line = fileReader.nextLine();
                if (!line.trim().isEmpty()) {
                    Faculty faculty = processFacultyString(line);
                    records.add(faculty);
                }
            } catch (IllegalArgumentException e) {
            	System.out.println(); 
                // Skip the line that caused the exception
            }
        }
        
        fileReader.close();
        return records;
    }

    /**
     * Writes the faculty records to a file
     * @param fileName name of the file to write to
     * @param facultyDirectory list of faculty to write to the file
     * @throws IOException if cannot write to file
     */
    public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
        PrintStream fileWriter = new PrintStream(new File(fileName));
        
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty faculty = facultyDirectory.get(i);
            fileWriter.println(faculty.getFirstName() + "," + 
                             faculty.getLastName() + "," + 
                             faculty.getId() + "," + 
                             faculty.getEmail() + "," + 
                             faculty.getPassword() + "," + 
                             faculty.getMaxCourses());
        }
        
        fileWriter.close();
    }

    /**
     * Processes a single line from the file into a Faculty object
     * @param line the line to process
     * @return Faculty object created from the line
     * @throws IllegalArgumentException if the line is invalid
     */
    private static Faculty processFacultyString(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid faculty record.");
        }

        String[] tokens = line.split(",");
        if (tokens.length != 6) {
            throw new IllegalArgumentException("Invalid faculty record.");
        }

        try {
            String firstName = tokens[0];
            String lastName = tokens[1];
            String id = tokens[2];
            String email = tokens[3];
            String password = tokens[4];
            int maxCourses = Integer.parseInt(tokens[5]);

            return new Faculty(firstName, lastName, id, email, password, maxCourses);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid max courses.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid faculty record.");
        }
    }
}