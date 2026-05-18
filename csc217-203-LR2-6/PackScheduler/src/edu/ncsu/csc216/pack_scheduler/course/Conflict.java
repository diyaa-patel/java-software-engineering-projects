/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict Interface which has a void method that calls the checkConflict method in Activity 
 * @author Diya Patel 
 */
public interface Conflict {
	
	/**
	 * will check if there is a conflict with the activities 
	 * @param possibleConflictingActivity a activity which could be conflicting with another 
	 * @throws ConflictException if there is a conflict with an activity 
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException; 

}
