/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for CourseNameValidator 
 * @author Sreyan 
 * @author Diya Patel 
 * @author Preeti Joshi 
 */
class CourseNameValidatorTest {

	/**
	 * set up for test cases 
	 */
	private CourseNameValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new CourseNameValidator();
    }

    /**
     * Tests if Course Names are valid
     */
    @Test
    public void testValidCourseNames() {
        try {
            assertTrue(validator.isValid("CSC216"));
            assertTrue(validator.isValid("CSC216A"));
            assertTrue(validator.isValid("E115"));
            assertTrue(validator.isValid("MA141"));
            assertTrue(validator.isValid("PY205L"));
            assertTrue(validator.isValid("E115A"));
            assertTrue(validator.isValid("EEEE115"));
            assertTrue(validator.isValid("EEEE115A"));
        } catch (InvalidTransitionException e) {
            fail("Exception should not be thrown for valid course names.");
        }
    }

 /**
  * Tests if name is invalid order
  */
 @Test
    public void testInvalidCourseNamesStartingWithDigit() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("123CSC");
        });
        assertEquals("Course name must start with a letter.", exception.getMessage());
    }
 
 /**
  * Tests if name is only letters and digits
  */
    @Test
    public void testInvalidCourseNamesWithRandomCharacters() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC216#");
        });
        assertEquals("Course name can only contain letters and digits.", exception.getMessage());
    }

    /**
     * Tests if course name has only 1 - 4 digits
     */
    @Test
    public void testInvalidCourseNamesMoreThanFourLetters() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSCCC216");
        });
        assertEquals("Course name cannot start with more than 4 letters.", exception.getMessage());
    }
 
    /**
     * Test if course name only has 3 digits
     */
    @Test
    public void testInvalidCourseNamesMoreThanThreeDigits() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC2161");
        });
        assertEquals("Course name can only have 3 digits.", exception.getMessage());
    }

    
    /**
     * Tests if name only has one extra suffix
     */
    @Test
    public void testInvalidCourseNamesWithMoreThanOneSuffix() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC216AA");
        });
        assertEquals("Course name can only have a 1 letter suffix.", exception.getMessage());
    }

    /**
     * Tests if name ends with optional suffix or not
     */
    @Test
    public void testInvalidCourseNamesWithDigitsAfterSuffix() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC216A1");
        });
        assertEquals("Course name cannot contain digits after the suffix.", exception.getMessage());
    }
   
    /**
     * Tests if name ends with optional suffix or not
     */
    @Test
    public void testInvalidCourseNameOneLettersOneDigit() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("E1S");
        });
        assertEquals("Course name must have 3 digits.", exception.getMessage());
    }
    
    /**
     * Tests if name ends with optional suffix or not
     */
    @Test
    public void testInvalidCourseNameOneLettersTwoDigit() {
        Exception exception = assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("E12S");
        });
        assertEquals("Course name must have 3 digits.", exception.getMessage());
    }
 

}
