package car;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * RegistrationNumber.java
 * A car registration number has two components - a single letter followed by a four digit number. 
 * For example: a1234 
 * Registration numbers are unique. This class guarantee that no two cars have the same registration number. 
 * RegistrationNumber provides a class method, <code>getInstance</code>.
 * For getting a generally useful object of this type. RegistrationNumber's getInstance method returns a RegistrationNumber object whose RegistrationNumber fields have been initialized.
 * <p>  RegistrationNumber number = RegistrationNumber.getInstance()  </p>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public final class RegistrationNumber {
	/**
	 * fields for objects of class RegistrationNumber
	 */
	private static final Map<String, RegistrationNumber> REGNUMBERS = new HashMap<String, RegistrationNumber>();
	private char letter;
	private int number;
	private String strRep;
	
	/**
	 * Constructor for objects of class RegistrationNumber
	 */
	private RegistrationNumber(char letter, int number, String strRep) {
		this.letter = letter;
		this.number = number;
		this.strRep = strRep;
	}
	
	/**
	 * Get an unique instance of RegistrationNumber class.
	 * <ul>
	 * <li>Generate a random letter</li>
	 * <li>Generate a random four digit number</li>
	 * </ul>
	 * 
	 * @return  an instance of RegistrationNumber class
	 */
	public static RegistrationNumber getInstance () {
		Random random = new Random();
		final Character letter = (char) (random.nextInt(26) + 'a');
		final int number = (int) (random.nextInt(9000) + 1000);
		final String strRep = letter + "" + number;
		
		RegistrationNumber aNumber= REGNUMBERS.get(strRep);
		if(aNumber == null) {
			aNumber = new RegistrationNumber(letter, number, strRep);
			REGNUMBERS.put(strRep, aNumber);
		}
		
		return aNumber;
	}
	
	/**
	 * Get the registration number's first component: a single letter
	 * 
	 * @return  a single letter
	 */
	public char getLetter() {
		return letter;
	}
	
	/**
	 * Get the registration number's last component: a four digit number
	 * 
	 * @return  a four digit number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get the registration number string representation
	 * 
	 * @return  the registration number string representation
	 */
	public String getStrRep() {
		return strRep;
	}
	
	/**
	 * Override the toString method
	 * 
	 * @return  "Registration number is XXXXX"
	 */
	@Override
	public String toString() {
	    return "Registration number is " + strRep;
	}
}
