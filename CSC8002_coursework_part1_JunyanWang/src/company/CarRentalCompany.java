package company;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import car.Car;
import car.SmallCar;
import car.LargeCar;
import licence.DrivingLicence;

/**
 * CarRentalCompany.java
 * The company rents cars from its fleet of 30 small cars and 20 large cars
 * A person can only rent out one car at a time.
 * 
 * @author junyan
 * @version 1.0
 */
public class CarRentalCompany {
	/**
	 * fields for objects of class CarRentalCompany
	 */
	private List<SmallCar> smallCars = new ArrayList<SmallCar>();
	private List<LargeCar> largeCars = new ArrayList<LargeCar>();
	private Map<DrivingLicence, Car> records = new HashMap<DrivingLicence, Car>();
	
	/**
	 * Constructor for objects of class CarRentalCompany
	 */
	public CarRentalCompany() {
		int i = 0;
		int j = 0;
		
		while(i < 30) {
			smallCars.add(new SmallCar());
			i++;
		}
		while(j < 20) {
			largeCars.add(new LargeCar());
			j++;
		}
	}
	
	/**
	 * This method returns the number of cars of the specified type that are available to rent.
	 * 
	 * @param  isLarge  whether the car is large or not
	 * @return  the number of cars of the specified type that are available to rent
	 */
	public int availableCars(boolean isLarge) {
		int num = 0;
		
		if(isLarge) {
			for(LargeCar car : largeCars) {
				if(!car.isRented()) {
					num++;
				}
			}
		}else {
			for(SmallCar car : smallCars) {
				if(!car.isRented()) {
					num++;
				}
			}
		}
		
		return num;
	}
	
	/**
	 * This method returns a collection of all the cars currently rented out (if any)
	 * 
	 * @return  a collection of all the cars currently rented out. if no car, return empty list.
	 */
	public List<Car> getRentedCars(){
		final List<Car> rentedCars = new ArrayList<Car>();
		for(LargeCar car : largeCars) {
			if(car.isRented()) {
				rentedCars.add(car);
			}
		}
		for(SmallCar car : smallCars) {
			if(car.isRented()) {
				rentedCars.add(car);
			}
		}
		
		return rentedCars;
	}
	
	/**
	 * Given a person's driving licence, this method returns the car they are currently renting(if any)
	 * 
	 * @param  drivingLicence  a person's driving licence
	 * @return  the car they are currently renting by the giving person, if no car, return null.
	 */
	public Car getCar(DrivingLicence drivingLicence) {
		final Car car = records.get(drivingLicence);
		return car;
	}
	
	/**
	 * This method determines whether the person is eligible to rent a car of the specified type and, if there is a car available, issues a car of the specified type. 
	 * When issuing a car, the following rules must be observed.
	 * <ul>
	 * <li>The person renting the car must have a full driving licence</li>
	 * <li>They cannot rent more than one car at a time</li>
	 * <li>To rent a small car, they must be at least 21 years old and must have held their licence for at least 1 year</li>
	 * <li>To rent a large car, they must be at least 25 years old and must have held their licence for at least 5 years</li>
	 * </ul>
	 * If a car cannot be issued, the method prints an indication "A car can not be issued".
	 * 
	 * @param  drivingLicence   a person's driving licence
	 * @param  isLarge  whether the car is large or not
	 */
	public boolean issueCar(DrivingLicence drivingLicence, boolean isLarge) {
		Calendar today = Calendar.getInstance();
		Calendar birthday = Calendar.getInstance();
		Calendar issueday = Calendar.getInstance();
		birthday.setTime(drivingLicence.getBirthDate());
		issueday.setTime(drivingLicence.getIssueDate());
		int age = computeYear(today, birthday);
		int held = computeYear(today, issueday);
		boolean canIssue = true;
		boolean hasFullCar = false;
		Car newCar = null;
		
		if(!drivingLicence.isFull()) {
			canIssue = false;
		}else {
			if(records.containsKey(drivingLicence)) {
				canIssue = false;
			}else {
				if(availableCars(isLarge) == 0) {
					canIssue = false;
				}else {
					if(isLarge) {
						if(age < 25 || held < 5) {
							canIssue = false;
						}else {
							for(LargeCar car : largeCars) {
								if(!car.isRented() && car.isTankFull()) {
									car.setRentStatus(true);
									newCar = car;
									hasFullCar = true;
									break;
								}
							}
							if(!hasFullCar) {
								canIssue = false;
							}
						}
					}else {
						if(age < 21 || held < 1) {
							canIssue = false;
						}else {
							for(SmallCar car : smallCars) {
								if(!car.isRented() && car.isTankFull()) {
									car.setRentStatus(true);
									newCar = car;
									hasFullCar = true;
									break;
								}
							}
							if(!hasFullCar) {
								canIssue = false;
							}
						}
					}
				}
			}
		}
		
		if(canIssue) {
			records.put(drivingLicence, newCar);
			return true;
		}else {
			System.out.println("A car can not be issued");
			return false;
		}
	}
	
	/**
	 * This method terminates the rental contract associated with the given driving licence.
	 * This method is not responsible for managing charges for the required fuel. It just reports the amount of fuel required to fill the tank.
	 * This method changes the status of the returned car to not rented
	 * 
	 * @param  drivingLicence  a person's driving licence
	 * @return  the amount of fuel in Litres required to fill the car's tank. if not available, return -1.
	 */
	public int terminateRental(DrivingLicence drivingLicence) {
		int fuel = -1;
		
		if(records.containsKey(drivingLicence)) {
			fuel = records.get(drivingLicence).getFuelCapacity() - records.get(drivingLicence).getCurrentFuel();
			records.get(drivingLicence).setRentStatus(false);
			records.remove(drivingLicence);
		}
		
		return fuel;
	}
	
	/**
	 * This method compute how many years past.
	 * 
	 * @param  dayOne  day one
	 * @param  dayTwo  day two
	 * @return  the compute year
	 */
	public static int computeYear(Calendar dayOne, Calendar dayTwo) {
		int year = 0;
		
		year = dayOne.get(Calendar.YEAR) - dayTwo.get(Calendar.YEAR);
		// check the day and the month
		if(dayOne.get(Calendar.MONTH) < dayTwo.get(Calendar.MONTH)) {
			year--;
		}else if(dayOne.get(Calendar.MONTH) == dayTwo.get(Calendar.MONTH)
				&& dayOne.get(Calendar.DAY_OF_MONTH) < dayTwo.get(Calendar.DAY_OF_MONTH)) {
			year--;
		}
		
		return year;
	}
}
