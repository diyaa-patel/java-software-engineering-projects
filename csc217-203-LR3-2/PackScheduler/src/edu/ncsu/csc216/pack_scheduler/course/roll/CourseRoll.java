/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * CourseRoll class that deals with the size of the class 
 * has methods for enrolling students and checking the maximum capacity when enrolling or dropping student.  
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
   /** Maximum waitlist size */
   private static final int WAITLIST_SIZE = 10;

   /** Waitlist for the course */
   private LinkedQueue<Student> waitlist;

   /** Course associated with this roll */
   private Course course;    
   
   /**
    * CourseRoll constructor that sets the this.enrollmentCap to the enrollmentCap
    * @param course the course associated with the roll 
    * @param enrollmentCap the capacity for roll's enrollment 
    * @throws IllegalArgumentException if course is null
    */
   public CourseRoll(Course course, int enrollmentCap) {
	    if (course == null) {
	        throw new IllegalArgumentException("Course cannot be null");
	    }
	    this.course = course;
	    this.roll = new LinkedAbstractList<Student>(enrollmentCap);
	    this.waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
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
		   throw new IllegalArgumentException("Enrollment capacity is invalid. "); 
	   }
	   roll.setCapacity(enrollmentCap);
	   this.enrollmentCap = enrollmentCap; 
   }
   
   /**
    * enrolls the student by adding to roll if exception is not thrown 
    * @param student the student to enroll 
    * @throws IllegalArgumentException if the student is null, there are no open seats, or if student is already enrolled.
    */
   public void enroll(Student student) {
	    if (student == null) {
	        throw new IllegalArgumentException("Student cannot be null");
	    }

	    // Check if student is already enrolled
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(student)) {
	            throw new IllegalArgumentException("Student already enrolled");
	        }
	    }

	    // If there's room in the course, add to roll
	    if (roll.size() < enrollmentCap) {
	        roll.add(student);
	        //student.addCourseToSchedule(course); 
	    } else {
	        // Otherwise, try to add to waitlist
	        waitlist.enqueue(student);
	    }
	}
   
   /**
    * drops student from roll 
    * @param student the student to drop
    * @throws IllegalArgumentException if the student is null or if it thrown from LinkedAbstractList class
    * or if student is not enrolled in roll  
    */
   public void drop(Student student) {
	    if (student == null) {
	        throw new IllegalArgumentException("Student cannot be null");
	    }

	    // Try to remove from roll
	    boolean removed = false;
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(student)) {
	            roll.remove(i);
	            removed = true;
	            
	            // Try to enroll first student from waitlist
	            if (!waitlist.isEmpty()) {
	                Student nextStudent = waitlist.dequeue();
	                roll.add(nextStudent);
	                 nextStudent.addCourseToSchedule(course); 
	                //nextStudent.addCourseToSchedule(course); 
	            }
	            break;
	        }
	    }

	    // If not in roll, try to remove from waitlist
	    if (!removed) {
	        LinkedQueue<Student> tempQueue = new LinkedQueue<Student>(WAITLIST_SIZE);
	        while (!waitlist.isEmpty()) {
	            Student s = waitlist.dequeue();
	            if (!s.equals(student)) {
	                tempQueue.enqueue(s);
	            }
	        }
	        // Restore waitlist without the dropped student
	        while (!tempQueue.isEmpty()) {
	            waitlist.enqueue(tempQueue.dequeue());
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
    * @param student the student to enroll 
    * @return false if the student cannot be enrolled 
    */
   public boolean canEnroll(Student student) {
	    if (student == null) {
	        return false;
	    }

	    // Check if student is already enrolled
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(student)) {
	            return false;
	        }
	    }

	    // Check if student is on waitlist
	    LinkedQueue<Student> tempQueue = new LinkedQueue<Student>(WAITLIST_SIZE);
	    boolean onWaitlist = false;
	    while (!waitlist.isEmpty()) {
	        Student s = waitlist.dequeue();
	        if (s.equals(student)) {
	            onWaitlist = true;
	        }
	        tempQueue.enqueue(s);
	    }
	    // Restore waitlist
	    while (!tempQueue.isEmpty()) {
	        waitlist.enqueue(tempQueue.dequeue());
	    }

	    return !onWaitlist && (getOpenSeats() > 0 || waitlist.size() < WAITLIST_SIZE);
	}
   
   
   /**
    * Gets the number of students on the waitlist
    * @return number of students on waitlist
    */
   public int getNumberOnWaitlist() {
       return waitlist.size();
   }
}
