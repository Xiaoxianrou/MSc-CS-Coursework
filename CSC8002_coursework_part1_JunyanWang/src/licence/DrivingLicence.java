package licence;

import java.util.Date;

/**
 * DrivingLicence.java
 * A driving licence has five components
 * <ul>
 * <li>The driver's name (comprising a first and last name)</li>
 * <li>The date of birth of the driver</li>
 * <li>A unique licence number</li>
 * <li>A date of issue</li>
 * <li>An indication whether the licence is full driving licence or not</li>
 * </ul>
 * This class has methods to access them.
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public final class DrivingLicence {
	/**
	 * fields for objects of class RegistrationNumber
	 */
	private Name name;
	private Date birthDate;
	private LicenceNumber licenceNumber;
	private Date issueDate;
	private boolean isFull;
	
	/**
	 * Constructor for objects of class RegistrationNumber
	 */
	public DrivingLicence(Name name, Date birthDate, Date issueDate, boolean isFull) {
		this.name = name;
		this.birthDate = birthDate;
		this.licenceNumber = LicenceNumber.getInstance(name, issueDate);
		this.issueDate = issueDate;
		this.isFull = isFull;
	}
	
	/**
	 * Get the name of the driver
	 * 
	 * @return  the name of the driver
	 */
	public Name getName() {
		return name;
	}
	
	/**
	 * Get the birthday of the driver
	 * 
	 * @return  the driver's birthday
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Get the licence number
	 * 
	 * @return  the licence number
	 */
	public LicenceNumber getLicenceNumber() {
		return licenceNumber;
	}
	
	/**
	 * Get the issue day of the driving licence
	 * 
	 * @return  the issue day of the driving licence
	 */
	public Date getIssueDate() {
		return issueDate;
	}
	/**
	 * A method that indicates the licence is a full licence or not.
	 * 
	 * @return  <code>true</code> if driving licence is full; <code>false</code> if driving licence is not full.
	 */
	public boolean isFull() {
		return isFull;
	}
	
	/**
	 * Override the toString method
	 * 
	 * @return  "This driving licence belongs to XXXX XXXX."
	 * 			"The licence number is XX-XXXX-XX."
	 */
	@Override
	public String toString() {
	    return "This driving licence belongs to " + name.getFirstName() + " " + name.getLastName() + ".\n"
	    		+ "The licence number is " + licenceNumber.getStrRep() + ".";
	}
}
