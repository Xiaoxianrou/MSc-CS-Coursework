package test;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import car.RegistrationNumber;

/**
 * The test class RegistrationNumberTest.
 * To test class RegistrationNumber
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class RegistrationNumberTest {
	/**
	 * fields for objects of class RegistrationNumberTest
	 */
	private RegistrationNumber testRegistrationNumber;

	/**
	 * Sets up an instance of RegistrationNumber class
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testRegistrationNumber = RegistrationNumber.getInstance();
	}

	/**
	 * Test getLetter method.
	 */
	@Test
	void testGetLetter() {
		final char letter = testRegistrationNumber.getLetter();
		assertTrue(letter >= 'a' && letter <= 'z');
	}
	
	/**
	 * Test getNumber method.
	 */
	@Test
	void testGetNumber() {
		final int number = testRegistrationNumber.getNumber();
		assertTrue(number >=1000 && number<=9999);
	}

	/**
	 * Test getStrRep method.
	 */
	@Test
	void testGetStrRep() {
		final String strRep = testRegistrationNumber.getStrRep();
		final char letter = testRegistrationNumber.getLetter();
		final int number = testRegistrationNumber.getNumber();
		assertEquals(strRep, (letter + "" + number));
	}
	
	/**
	 * Test toString method.
	 */
	@Test
	void testToString() {
		final String toString = testRegistrationNumber.toString();
		final String strRep = testRegistrationNumber.getStrRep();
		assertEquals(toString, ("Registration number is " + strRep));
	}
}
