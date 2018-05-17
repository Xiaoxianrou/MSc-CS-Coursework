package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import licence.DrivingLicence;
import licence.LicenceNumber;
import licence.Name;

/**
 * The test class DrivingLicenceTest
 * To test class DrivingLicence
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class DrivingLicenceTest {
	/**
	 * fields for objects of class DrivingLicenceTest
	 */
	private DrivingLicence drivingLicence;
	private Name name;
	private Date birthDate;
	private Date issueDate;
	private boolean isFull;

	/**
	 * Sets up an instance of DrivingLicence class
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		name = new Name("Junyan", "Wang");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String issueDateStr = "2010-9-25";
		String birtheDateStr = "1991-1-30";
		birthDate = format.parse(birtheDateStr);
		issueDate = format.parse(issueDateStr);
		isFull = true;
		
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
	}

	/**
	 * Test getName method.
	 */
	@Test
	void testGetName() {
		final Name testName = drivingLicence.getName();
		assertEquals(testName, name);
	}

	/**
	 * Test getBirthDate method.
	 */
	@Test
	void testGetBirthDate() {
		final Date testBirthDate = drivingLicence.getBirthDate();
		assertEquals(testBirthDate, birthDate);
	}
	
	/**
	 * Test getLicenceNumber method.
	 */
	@Test
	void testGetLicenceNumber() {
		final LicenceNumber testLicenceNumber = drivingLicence.getLicenceNumber();
		final String initial = testLicenceNumber.getInitial();
		final int year = testLicenceNumber.getYear();
		
		assertEquals(initial, "JW");
		assertEquals(year, 2010);
	}
	
	/**
	 * Test getIssueDate method.
	 */
	@Test
	void testGetIssueDate() {
		final Date testIssueDate = drivingLicence.getIssueDate();
		assertEquals(testIssueDate, issueDate);
	}
	
	/**
	 * Test isFull method.
	 */
	@Test
	void testIsFull() {
		final boolean testIsFull = drivingLicence.isFull();
		assertEquals(testIsFull, isFull);
	}

	/**
	 * Test toString method.
	 */
	@Test
	void testToString() {
		final String toString = drivingLicence.toString();
		assertEquals(toString,("This driving licence belongs to " + name.getFirstName() + " " + name.getLastName() + ".\n"
        		+ "The licence number is " + drivingLicence.getLicenceNumber().getStrRep() + "."));
	}
}
