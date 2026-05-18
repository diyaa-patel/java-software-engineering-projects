/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class to validate course name 
 */
public class CourseNameValidator {
	/** The current state in FSM */ 
	private State currentState; 
	/** inital state */ 
	private InitialState initialState; 
	/** letter state */ 
	private LetterState letterState; 
	/** digit state */
	private DigitState digitState; 
	/** suffix state */ 
	private SuffixState suffixState; 
	
	/**
	 * Validates the course name 
	 * @param name the name of the course to validate 
	 * @return true if name is valid course name 
	 * @throws InvalidTransitionException if transition is invalid 
	 * @throws IllegalArgumentException if name is null or empty 
	 */
	public boolean isValid(String name) throws InvalidTransitionException{
		//System.out.println(name); 
		if(name == null || "".equals(name))
		{
			throw new IllegalArgumentException("Invalid course name.");
		}
		initialState = new InitialState(); 
		letterState = new LetterState(); 
		digitState = new DigitState(); 
		suffixState = new SuffixState();
		char nextChar; 
		currentState = initialState; 
		for(int i = 0; i < name.length(); i++) {
			nextChar = name.charAt(i); 
			if(Character.isLetter(nextChar)) {
				currentState.onLetter(); 
			}
			else if(Character.isDigit(nextChar)) {
				currentState.onDigit(); 
			}
			else {
				currentState.onOther(); 
			}
		}
		digitState.isDigitsCountMatch();
		return true; 
	}
	
	/**
	 * An abstract methods with all the different states to consider
	 */
	public abstract class State {
		/**
		 * abstract class on letter used for handling letter input 
		 * @throws InvalidTransitionException if transition is invalid 
		 */
		public abstract void onLetter() throws InvalidTransitionException; 
		
		/**
		 * used for handling digit input 
		 * @throws InvalidTransitionException if the transition is invalid 
		 */
		public abstract void onDigit() throws InvalidTransitionException; 
		
		/**
		 * handles any other input 
		 * @throws InvalidTransitionException because any other input other than a letter or digit is invalid 
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits."); 
		}
		
		
	}
	
	/**
	 * letter state for FSM 
	 */
	public class LetterState extends State {
		/** max number of letters allowed */ 
		final int max = 4; 
		/** counter for letters */ 
		private int counter = 0; 

		/**
		 * increments counter for letters 
		 * @throws InvalidTransitionException if transition is invalid 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			counter++; 
			if(counter > max) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters."); 
			}
			
		}

		/**
		 * changes currentState to digitState 
		 * @throws InvalidTransitionException if counter is equal to 0 s
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			if(counter == 0) {
				throw new InvalidTransitionException("Course name must start with a letter."); 
			}
			currentState = digitState; 
			currentState.onDigit();
			
		}
		
	}
	
	/**
	 * implements digit validation 
	 */
	public class DigitState extends State {
		/** number of digits */ 
		final int digit = 3; 
		/** counter for digits */ 
		private int counter = 0; 

		/**
		 * if character is letter 
		 * @throws InvalidTransitionException if character is letter 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(counter < digit) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			throw new InvalidTransitionException("Course name must start with a letter."); 
			
		}

		/**
		 * increments the counter and if counter is equal to digit currentState is changed to suffixState 
		 * @throws InvalidTransitionException if counter is greater than 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			counter++; 
			if(counter == digit) {
				currentState = suffixState; 
			}
			else if (counter > digit){
				throw new InvalidTransitionException("Course name can only have 3 digits."); 
			}
		}
		
		/**
		 * increments the counter and if counter is equal to digit currentState is changed to suffixState 
		 * @throws InvalidTransitionException if counter does not meet the requirement for digits 
		 */
		public void isDigitsCountMatch() throws InvalidTransitionException {
			if (counter < digit){
				throw new InvalidTransitionException("Course name can only have 3 digits."); 
			}
		}
		
	}
	
	/**
	 * Initial state for FSM 
	 */
	public class InitialState extends State {

		/**
		 * changes currentState to letterState and calls onLetter() method  
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			currentState = letterState; 
			currentState.onLetter();
			
		}

		/**
		 * throws Exception because initialState must be a letter 
		 * @throws InvalidTransitionException if the transition is invalid 
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter."); 
			
		}
		
	}
	
	/**
	 * implements validation for suffix state 
	 */
	public class SuffixState extends State {
		/** counter for suffix */ 
		private int counter = 0; 
		
		/**
		 * increments counter for suffix 
		 * @throws InvalidTransitionException if the counter is greater than 1 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			counter++; 
			if(counter > 1) {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix."); 
			}
			
		}

		/**
		 * throws exception if the character in suffixState is a digit 
		 * @throws InvalidTransitionException if character is digit 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if(counter == 0) {
				throw new InvalidTransitionException("Course name can only have 3 digits."); 
			}
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix."); 
			
		}
		
	}

}
