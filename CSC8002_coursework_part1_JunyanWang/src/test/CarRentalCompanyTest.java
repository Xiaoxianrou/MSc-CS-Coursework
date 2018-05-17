package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import car.Car;

import company.CarRentalCompany;
import licence.DrivingLicence;
import licence.Name;

/**
 * The test class CarRentalCompanyTest.
 * To test class CarRentalCompany
 *
 * @author  Junyan Wang
 * @version 1.0
 */
class CarRentalCompanyTest {
	/**
	 * fields for objects of class CarRentalCompanyTest
	 */
	private CarRentalCompany testAgency;
	private DrivingLicence drivingLicence;
	private Name name;
	private Date birthDate;
	private Date birthDateTest;
	private Date issueDate;
	private Date issueDateTest;
	private boolean isFull;
	
	/**
	 * Sets up fields
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// create a driving licence
		name = new Name("Junyan", "Wang");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String issueDateStr = "2010-9-25";
		String birtheDateStr = "1991-1-30";
		String issueDateTestStr = "2017-9-25";
		String birtheDateTestStr = "2000-1-30";
		birthDate = format.parse(birtheDateStr);
		issueDate = format.parse(issueDateStr);
		birthDateTest = format.parse(birtheDateTestStr);
		issueDateTest = format.parse(issueDateTestStr);
		isFull = true;
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
	}

	/**
	 * Test availableCars method.
	 */
	@Test
	void testAvailableCars() {
		// test no rented car condition
		testAgency = new CarRentalCompany();
		assertEquals(testAgency.availableCars(true), 20);
		assertEquals(testAgency.availableCars(false), 30);
		
		// test issue a car condition
		testAgency.issueCar(drivingLicence, true);
		assertEquals(testAgency.availableCars(true), 19);
	}
	
	/**
	 * Test getRentedCars method.
	 */
	@Test
	void testGetRentedCars() {
		// test no rented car condition
		testAgency = new CarRentalCompany();
		List<Car> rentedCars = testAgency.getRentedCars();
		assertEquals(rentedCars.size(), 0);
		
		// test issue a car condition
		testAgency.issueCar(drivingLicence, false);
		rentedCars = testAgency.getRentedCars();
		assertEquals(rentedCars.size(), 1);
	}
	
	/**
	 * Test getCar method.
	 */
	@Test
	void testGetCar() {
		// test no rented car condition
		testAgency = new CarRentalCompany();
		Car car = testAgency.getCar(drivingLicence);
		assertNull(car);
		
		// test issue a car condition
		testAgency.issueCar(drivingLicence, false);
		car = testAgency.getCar(drivingLicence);
		assertNotNull(car);
	}
	
	/**
	 * Test issueCar method.
	 */
	@Test
	void testiIssueCar() {
		testAgency = new CarRentalCompany();
		// test the person don't have a full driving licence condition
		isFull = false;
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
		assertFalse(testAgency.issueCar(drivingLicence, true));
		
		// test the person rent more than one car at a time condition
		isFull = true;
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
		testAgency.issueCar(drivingLicence, true);
		assertFalse(testAgency.issueCar(drivingLicence, true));
		
		// test the person younger than 21 and want to rent a samll car condition
		drivingLicence = new DrivingLicence(name, birthDateTest, issueDate, isFull);
		assertFalse(testAgency.issueCar(drivingLicence, false));
		
		// test the person held his licence less than 1 year and want to rent a samll car condition
		drivingLicence = new DrivingLicence(name, birthDate, issueDateTest, isFull);
		assertFalse(testAgency.issueCar(drivingLicence, false));
		
		// test the person younger than 25 and want to rent a large car condition
		drivingLicence = new DrivingLicence(name, birthDateTest, issueDate, isFull);
		assertFalse(testAgency.issueCar(drivingLicence, true));
		
		// test the person held his licence less than 5 year and want to rent a large car condition
		drivingLicence = new DrivingLicence(name, birthDate, issueDateTest, isFull);
		assertFalse(testAgency.issueCar(drivingLicence, true));
	}
	
	/**
	 * Test terminateRental method.
	 */
	@Test
	void testTerminateRental() {
		// test no rented car condition
		testAgency = new CarRentalCompany();
		drivingLicence = new DrivingLicence(name, birthDate, issueDate, isFull);
		assertEquals(testAgency.terminateRental(drivingLicence), -1);
		
		// test issue a car condition
		testAgency.issueCar(drivingLicence, false);
		assertEquals(testAgency.terminateRental(drivingLicence), 0);
		
		// test drive 200 kilometres condition
		testAgency.issueCar(drivingLicence, false);
		Car car = testAgency.getCar(drivingLicence);
		car.drive(200);
		assertEquals(testAgency.terminateRental(drivingLicence), 10);
	}
}
