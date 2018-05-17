package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import licence.Name;

/**
 * The test class NameTest.
 * To test class Name
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class NameTest {
	/**
	 * fields for objects of class RegistrationNumberTest
	 */
	private Name testName;
	
	/**
	 * Sets up an instance of Name class
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testName = new Name("Junyan", "Wang");
	}

	/**
	 * Test getFirstName method.
	 */
	@Test
	void testGetFirstName() {
		final String firstName = testName.getFirstName();
		assertEquals(firstName,"Junyan");
	}

	/**
	 * Test getLastName method.
	 */
	@Test
	void testGetLastName() {
		final String lastName = testName.getLastName();
		assertEquals(lastName,"Wang");
	}
	
	/**
	 * Test getInitial method.
	 */
	@Test
	void testGetInitial() {
		final String initial = testName.getInitial();
		assertEquals(initial,"JW");
	}
	
	/**
	 * Test toString method.
	 */
	@Test
	void testToString() {
		final String toString = testName.toString();
		final String firstName = testName.getFirstName();
		final String lastName = testName.getLastName();
		assertEquals(toString,("The drive name is " + firstName + " " + lastName));
	}
}
