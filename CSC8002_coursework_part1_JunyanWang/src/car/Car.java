package car;

/**
 * Car.java
 * Interface car class that has the following methods.
 * <ul>
 * <li>A method to get the car's registration number
 * <li>A method to get the capacity in whole Litres of the car's fuel tank, which is 49 Litres for a small car and 60 Litres for a large car
 * <li>A method to get the amount of fuel in whole Litres currently in the fuel tank
 * <li>A method that indicates whether the car's fuel tank is full or not
 * <li>A method that indicates whether the car is rented or not
 * <li>A method to add a given number of whole Litres to the fuel tank (up to the tank's capacity) and which, after execution, indicates how much fuel was added to the tank (which will be 0 if the car is not rented or its tank is already full)
 * <li>A method to "drive" the car for a given number of whole Kilometres that returns the number of whole Litres of fuel consumed during the journey
 * </ul>
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public interface Car {
	/**
	 * This method return the registration number of the car.
	 */
	public String getRegNumber();
	
	/**
	 * This method return the fuel capacity of the car.
	 */
	public int getFuelCapacity();
	
	/**
	 * This method return the current fuel of the car.
	 */
	public int getCurrentFuel();
	
	/**
	 * This method return whether the tank is full.
	 */
	public boolean isTankFull();
	
	/**
	 * This method return whether the car is rented.
	 */
	public boolean isRented();
	
	/**
	 * This method is adding fuel to the car and after execution indicates how much fuel was added to the tank .
	 */
	public int addFuel(int amount);
	
	/**
	 * This method "drive" the car for a given number of whole Kilometres that returns the number of whole Litres of fuel consumed during the journey.
	 */
	public int drive(int distance);
	
	/**
	 * This method set the fuel capacity.
	 */
	public void setFuelCapacity(int fuelCapacity);
	
	/**
	 * This method set the current fuel.
	 */
	public void setCurrentFuel(int currentFuel);
	
	/**
	 * This method set the rent status.
	 */
	public void setRentStatus(boolean rentStatus);
}
