package licence;

/**
 * Name.java
 * This class stores the first name and the last name of the driver 
 * and offer <code>getInitial</code> method to get the initial of the first name with the initial of the last name.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public final class Name {
	/**
	 * fields for objects of class Name
	 */
	private String firstName;
	private String lastName;
	
	/**
	 * Constructor for objects of class Name
	 * 
	 * @throws  IllegalArgumentException
	 */
	public Name(String firstName, String lastName) {
		if(firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
			throw new IllegalArgumentException("first name or last name cannot be empty");
		}
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Get the first name
	 * 
	 * @return  the first name of the driver
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Get the last name
	 * 
	 * @return  the last name of the driver
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Get the initial of the first name of the driver with the initial of the last name of the driver. 
	 * 
	 * @return  the initial of the first name with the initial of the last name
	 */
	public String getInitial() {
		String initial = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();
		return initial;
	}
	
	/**
	 * Override the toString method
	 * 
	 * @return  "The drive name is XXXX XXXX"
	 */
	@Override
	public String toString() {
	    return "The drive name is " + firstName + " " + lastName;
	}
}
