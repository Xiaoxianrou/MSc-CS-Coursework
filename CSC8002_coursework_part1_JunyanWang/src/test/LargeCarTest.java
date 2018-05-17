package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import car.Car;
import car.LargeCar;
import company.CarRentalCompany;
import licence.DrivingLicence;
import licence.Name;

/**
 * The test class LargeCarTest.
 * To test class LargeCar
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class LargeCarTest {
	/**
	 * fields for objects of class LargeCarTest
	 */
	private LargeCar car;
	private CarRentalCompany testAgency;
	private DrivingLicence drivingLicence;
	private Name name;
	private Date birthDate;
	private Date issueDate;
	private boolean isFull;
	
	/**
	 * Sets up fields
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		car = new LargeCar();
		// create a driving licence
		name = new Name("Junyan", "Wang");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String issueDateStr = "2010-9-25";
		String birtheDateStr = "1991-1-30";
		birthDate = format.parse(birtheDateStr);
		issueDate = format.parse(issueDateStr);
		isFull = true;
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
		testAgency = new CarRentalCompany();
	}

	/**
	 * Test getRegNumber method.
	 */
	@Test
	void testGetRegNumber() {
		final String registrationNumber = car.getRegNumber();
		assertNotNull(registrationNumber);
	}

	/**
	 * Test getFuelCapacity method.
	 */
	@Test
	void testGetFuelCapacity() {
		final int capacity = car.getFuelCapacity();
		assertEquals(capacity, 60);
	}
	
	/**
	 * Test getCurrentFuel method.
	 */
	@Test
	void testGetCurrentFuel() {
		final int current = car.getCurrentFuel();
		assertEquals(current, 60);
		
		// test rent a car and drive 200 kilometres condition
		testAgency.issueCar(drivingLicence, true);
		Car testCar = testAgency.getCar(drivingLicence);
		testCar.drive(50);
		assertEquals(testCar.getCurrentFuel(), 55);
	}
	
	/**
	 * Test isTankFull method.
	 */
	@Test 
	void testIsTankFull() {
		final boolean isTankFull = car.isTankFull();
		assertTrue(isTankFull);
		
		// test rent a car and drive 200 kilometres condition
		testAgency.issueCar(drivingLicence, true);
		Car testCar = testAgency.getCar(drivingLicence);
		testCar.drive(200);
		assertFalse(testCar.isTankFull());
	}
	
	/**
	 * Test isRented method.
	 */
	@Test
	void testIsRented() {
		final boolean isRented = car.isRented();
		assertFalse(isRented);
		
		// test rent a car and drive 200 kilometres condition
		testAgency.issueCar(drivingLicence, true);
		Car testCar = testAgency.getCar(drivingLicence);
		assertTrue(testCar.isRented());
	}
	
	/**
	 * Test setFuelCapacity method.
	 */
	@Test
	void testSetFuelCapacity() {
		car.setFuelCapacity(59);
		assertEquals(car.getFuelCapacity(), 59);
	}
	
	/**
	 * Test setCurrentFuel method.
	 */
	@Test
	void testSetCurrentFuel() {
		car.setCurrentFuel(59);
		assertEquals(car.getCurrentFuel(), 59);
	}
	
	/**
	 * Test setRentStatus method.
	 */
	@Test
	void testSetRentStatus() {
		car.setRentStatus(true);
		assertEquals(car.isRented(), true);
	}
	
	/**
	 * Test addFuel method.
	 */
	@Test
	void testAddFuel() {
		car.setFuelCapacity(60);
		car.setCurrentFuel(60);
		car.setRentStatus(false);
		
		// test the car is not rented condition
		assertEquals(car.addFuel(20), 0);
		
		// test the car's tank is full condition
		assertEquals(car.addFuel(20), 0);
		
		// test normal condition
		car.setRentStatus(true);
		car.setCurrentFuel(50);
		assertEquals(car.addFuel(20), 10);
	}
	
	/**
	 * Test drive method.
	 */
	@Test
	void testDrive() {
		car.setFuelCapacity(60);
		car.setCurrentFuel(60);
		car.setRentStatus(false);
		
		// test the car's tank is less than 0 condition
		car.setRentStatus(true);
		car.setCurrentFuel(0);
		assertEquals(car.drive(200), -1);
		
		// test the car is not currently rented condition
		car.setRentStatus(false);
		car.setCurrentFuel(60);
		assertEquals(car.drive(200), -1);
		
		// test the distance is less than 0 condition
		car.setRentStatus(true);
		car.setCurrentFuel(60);
		assertEquals(car.drive(-200), -1);
		
		// test normal condition
		car.setRentStatus(true);
		car.setCurrentFuel(60);
		assertEquals(car.drive(200), 15);
	}
}
