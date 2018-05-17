package licence;

import java.util.Random;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;

/**
 * LicenceNumber.java
 * The licence number has three components. 
 * <ul>
 * <li>The concatenation of the initial of the first name of the driver with the initial of the last name of the driver. </li>
 * <li>The year of issue of the licence</li>
 * <li>An arbitrary serial number.</li>
 * </ul> 
 * For example: MS-1990-10
 * This class guarantee the uniqueness of licence numbers. 
 * LicenceNumber provides a class method, <code>getInstance</code>.
 * For getting a generally useful object of this type. LicenceNumber's getInstance method returns a LicenceNumber object whose LicenceNumber fields have been initialized.
 * <p>  LicenceNumber number = LicenceNumber.getInstance()  </p>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public final class LicenceNumber {
	/**
	 * fields for objects of class RegistrationNumber
	 */
	private static final Map<String, LicenceNumber> LICENCENUMBERS = new HashMap<String, LicenceNumber>();
	private String initial;
	private int year;
	private int serialNumber;
	private String strRep;
	
	/**
	 * Constructor for objects of class RegistrationNumber
	 */
	private LicenceNumber(String initial, int year, int serialNumber, String strRep) {
		this.initial = initial;
		this.year = year;
		this.serialNumber = serialNumber;
		this.strRep = strRep;
	}
	
	/**
	 * Get an unique instance of LicenceNumber class.
	 * 
	 * @return  an instance of RegistrationNumber class
	 */
	public static LicenceNumber getInstance(Name name, Date issue) {
		final String initial = name.getInitial();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(issue); 
		final int year = calendar.get(Calendar.YEAR);
		
		return getInstance(initial, year);
	}
	
	/**
	 * Get an unique instance of LicenceNumber class.
	 * Overload getInstance method
	 * 
	 * @throws  IllegalArgumentException
	 * @return  an instance of RegistrationNumber class
	 */
	public static LicenceNumber getInstance(String initial, int year) {
		if(initial == null || initial.isEmpty()) {
			throw new IllegalArgumentException("initial cannot be empty");
		}
		
		Random random = new Random();
		final int serialNumber = random.nextInt(11);
		
		final String strRep = initial + "-" + year + "-" + serialNumber;
		LicenceNumber aNumber = LICENCENUMBERS.get(strRep);
		
		if(aNumber == null) {
			aNumber = new LicenceNumber(initial, year, serialNumber, strRep);
			LICENCENUMBERS.put(strRep, aNumber);
		}
		
		return aNumber;
	}
	
	/**
	 * Get the initial of the first name of the driver with the initial of the last name of the driver. 
	 * 
	 * @return  the initial of the first name with the initial of the last name
	 */
	public String getInitial() {
		return initial;
	}
	
	/**
	 * Get the year of issue
	 * 
	 * @return  the year of issue
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Get the serial number
	 * 
	 * @return  the serial number
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * Get the licence number string representation
	 * 
	 * @return  the licence number string representation
	 */
	public String getStrRep() {
		return strRep;
	}
	
	/**
	 * Override the toString method
	 * 
	 * @return  "The licence number is XX-XXXX-XX"
	 */
	@Override
	public String toString() {
	    return "The licence number is " + strRep;
	}
}
