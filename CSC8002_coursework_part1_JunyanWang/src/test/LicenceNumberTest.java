package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.text.SimpleDateFormat;

import licence.LicenceNumber;
import licence.Name;

/**
 * The test class LicenceNumberTest
 * To test class LicenceNumber
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class LicenceNumberTest {
	/**
	 * fields for objects of class LicenceNumberTest
	 */
	private LicenceNumber licenceNumber;
	
	/**
	 * Sets up an instance of LicenceNumber class
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Name name = new Name("Junyan", "Wang");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String dateStr = "2010-9-25";
		Date date = new Date();
		date = format.parse(dateStr);
		
		licenceNumber = LicenceNumber.getInstance(name, date);
	}

	/**
	 * Test getInitial method.
	 */
	@Test
	void testGetInitial() {
		final String initial = licenceNumber.getInitial();
		assertEquals(initial, "JW");
	}
	
	/**
	 * Test getYear method.
	 */
	@Test
	void testGetYear() {
		final int year = licenceNumber.getYear();
		assertTrue(year == 2010);
	}
	
	/**
	 * Test getSerialNumber method.
	 */
	@Test
	void testGetSerialNumber() {
		final int serialNumber = licenceNumber.getSerialNumber();
		assertTrue(serialNumber >=0 && serialNumber <= 10);
	}
	
	/**
	 * Test getStrRep method.
	 */
	@Test
	void testGetStrRep() {
		final String strRep = licenceNumber.getStrRep();
		final String initial = licenceNumber.getInitial();
		final int year = licenceNumber.getYear();
		final int serialNumber = licenceNumber.getSerialNumber();
		assertEquals(strRep,(initial + "-" + year + "-" + serialNumber));
	}
	

	/**
	 * Test toString method.
	 */
	@Test 
	void testToString(){
		final String toString = licenceNumber.toString();
		final String strRep = licenceNumber.getStrRep();
		assertEquals(toString,("The licence number is " + strRep));
	}

}
