package edu.ncsu.csc216.pack_scheduler.user;

/**
 * abstract class User which has firstName, lastName, id, email, and password 
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * User constructor for user object 
	 * @param firstName the firstName of the User 
	 * @param lastName the lastName of the User 
	 * @param id the id of the User 
	 * @param email the email of the user 
	 * @param password the password of the user 
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
    
		setFirstName(firstName);  
		setLastName(lastName); 
		setId(id); 
		setEmail(email); 
		setPassword(password); 
		
	}

	/**
	 * Returns Student's first name. 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets Student's first name. 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if name is null or an empty string.
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.length() == 0)
		{
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns Student's last name. 
	 * @return the lastName 
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets Student's last name. 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if the last name is null or empty string.
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.length() == 0)
		{
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns Student's id. 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets Student's id. 
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or an empty string. 
	 */
	protected void setId(String id) {
		if(id == null || id.length() == 0)
		{
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns Student's email. 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets Student's email. 
	 * @param email the email to set
	 * @throws IllegalArgumentException if id is null or an empty string 
	 * or if there is no @ symbol or . , and if the index of . is before the @ 
	 * with the message "Invalid Email."
	 */
	public void setEmail(String email) {
		if(email == null || email.length() == 0)
		{
			throw new IllegalArgumentException("Invalid email");
		}
		
		int count = 0;
		int count2 = 0; 
		int index = 0;
		int index2 = 0; 
		
		for(int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@') {
				index = i;
				count++; 
				break; 
			}
		}	
			
			for(int i = 0; i < email.length(); i++) {
				if(email.charAt(i) == '.') {
					index2 = i;
					count2++; 
				}
			}	
	
		
		
		
		if(count < 1 || count2 < 1 || index2 < index) {
			throw new IllegalArgumentException("Invalid email"); 
		}
		
		this.email = email;
	}

	/**
	 * Returns Student's password. 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets Student's password. 
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or an empty string. 
	 */
	public void setPassword(String password) {
		if(password == null || password.length() == 0)
		{
			throw new IllegalArgumentException("Invalid password");
		}
		
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLetter(password.charAt(i))) {
				break; 
			}
			if(i == password.length() - 1) {
				throw new IllegalArgumentException("Invalid password"); 
			}
		}
		
		this.password = password;
	}

	/**
	 * hashCode() method for User 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * equals() method for User 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}