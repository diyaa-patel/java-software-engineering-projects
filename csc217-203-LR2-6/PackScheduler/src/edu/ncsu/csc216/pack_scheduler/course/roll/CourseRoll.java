/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * CourseRoll class that deals with the size of the class  
 */
public class CourseRoll {
	/** custom LinkedAbstractList of Students */
   private LinkedAbstractList<Student> roll; 
   /** the roll's enrollment capacity */ 
   private int enrollmentCap; 
   /** smallest class size */ 
   private static final int MIN_ENROLLMENT = 10; 
   /** largest class size */ 
   private static final int MAX_ENROLLMENT = 250;
   
   /**
    * CourseRoll constructor that sets the this.enrollmentCap to the enrollmentCap
    * @param enrollmentCap the capacity for roll's enrollment 
    */
   public CourseRoll(int enrollmentCap) {
	   roll = new LinkedAbstractList<Student>(10); 
	   setEnrollmentCap(enrollmentCap); 
   }
   
   /**
    * gets the enrollment cap 
    * @return enrollmentCap the capacity for the roll 
    */
   public int getEnrollmentCap() {
	   return enrollmentCap; 
   }
   
   /**
    * sets the enrollment cap 
    * @param enrollmentCap the cap to set it to
    * @throws IllegalArgumentException if the enrollmentCap is less than the min 
    * or greater than the max enrollment  
    */
   public void setEnrollmentCap(int enrollmentCap) {
	   if(enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
		   throw new IllegalArgumentException(); 
	   }
	   this.enrollmentCap = enrollmentCap; 
   }
   
   /**
    * enrolls the student by adding to roll if exception is not thrown 
    * @param s the student to enroll 
    * @throws IllegalArgumentException if the student is null, there are no open seats, or if student is already enrolled.
    */
   public void enroll(Student s) {
	   if(s == null || getOpenSeats() <= 0 || !canEnroll(s)) {
		   throw new IllegalArgumentException(); 
	   }
	   roll.add(s); 
	   
   }
   
   /**
    * drops student from roll 
    * @param s the student to drop
    * @throws IllegalArgumentException if the student is null or if it thrown from LinkedAbstractList class
    * or if student is not enrolled in roll  
    */
   public void drop(Student s) {
	   if(s == null) {
		   throw new IllegalArgumentException(); 
	   }
	   for(int i = 0; i < roll.size(); i++) {
		   if (roll.get(i) == s) {
			   roll.remove(s); 
			   break; 
		   }
	   }
   }
   
   /**
    * gets the amount of seats that are left 
    * @return open the amount of seats open 
    */
   public int getOpenSeats() {
	   int open = getEnrollmentCap() - roll.size(); 
	   return open; 
   }
   
   /**
    * checks whether the student can be enrolled or not 
    * @param s the student to enroll 
    * @return false if the student cannot be enrolled 
    */
   public boolean canEnroll(Student s) {
	   for(int i = 0; i < roll.size(); i++) {
		   if (roll.get(i) == s) {
			   return false; 
		   }
	   }
	   return getOpenSeats() > 0; 
   }
}
