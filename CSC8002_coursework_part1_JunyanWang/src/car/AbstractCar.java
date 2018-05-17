package car;

/**
 * AbstractCar.java
 * Abstract car class implements interface car class methods.
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public abstract class AbstractCar implements Car{
	/**
	 * fields for objects of class AbstractCar
	 */
	private RegistrationNumber regNumber;
	private int fuelCapacity;
	private int currentFuel;
	private boolean isRented;
	
	/**
	 * Constructor for objects of class AbstractCar
	 */
	public AbstractCar() {
		this.regNumber = RegistrationNumber.getInstance();
		this.isRented = false;
	}
	
	/**
	 * A method to get the car's registration number.
	 * 
	 * @return the registration number of the car
	 * @see car.Car#getRegNumber()
	 */
	public String getRegNumber() {
		return regNumber.getStrRep();
	}
	
	/**
	 * A method to get the capacity in whole Litres of the car's fuel tank.
	 * <ul>
	 * <li>return 49 Litres for a small car</li>
	 * <li>return 60 Litres for a large car</li>
	 * </ul>
	 * 
	 * @return  the fuel capacity of the car
	 * @see car.Car#getFuelCapacity()
	 */
	public int getFuelCapacity() {
	    return fuelCapacity;
	}

	/**
	 * A method to get the amount of fuel in whole Litres currently in the fuel tank.
	 * 
	 * @return  the fuel capacity of the car
	 * @see car.Car#getCurrentFuel()
	 */
	public int getCurrentFuel() {
	    return currentFuel;
	}
    
	/**
	 * A method that indicates whether the car's fuel tank is full or not.
	 * 
	 * @return  <code>true</code> if the tank is full; <code>false</code> if the tank is not full.
	 * @see car.Car#isTankFull()
	 */
	public boolean isTankFull() {
		return fuelCapacity == currentFuel;
	}
    
	/**
	 * A method that indicates whether the car is rented or not.
	 * 
	 * @return  <code>true</code> if the car is rented; <code>false</code> if the car is not rented.
	 * @see car.Car#isRented()
	 */
	public boolean isRented() {
		return isRented;
	}
    
	/**
	 * A method that set the fuel capacity, usually use in subclass constructor.
	 * 
	 * @see car.Car#setFuelCapacity(int)
	 */
	public void setFuelCapacity(int fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}
    
	/**
	 * A method that set the current fuel.
	 * 
	 * @see car.Car#setCurrentFuel(int)
	 */
    public void setCurrentFuel(int currentFuel) {
		this.currentFuel = currentFuel;
	}
    
	/**
	 * A method that set the rent status
	 * .
	 * @see car.Car#setRentStatus(boolean)
	 */
	public void setRentStatus(boolean rentStatus) {
		this.isRented = rentStatus;
	}
    
	/**
	 * A method to add a given number of whole Litres to the fuel tank
	 * which will be 0 if the car is not rented or its tank is already full
	 * 
	 * @return  how much fuel was added to the tank 
	 * @see car.Car#addFuel(int)
	 */
	public int addFuel(int amount) {
		if(!isRented) {
			return 0;
		}
		if(fuelCapacity == currentFuel) {
			return 0;
		}
			
		if((amount + currentFuel) > fuelCapacity) {
			amount = fuelCapacity - currentFuel;
			currentFuel = fuelCapacity;
		}else {
			currentFuel = amount + currentFuel;
		}
		
		return amount;
	}
    
	/**
	 * A method to "drive" the car for a given number of whole Kilometres 
	 * 
	 * @return  the number of whole Litres of fuel consumed during the journey
	 */
	public int drive(int distance, int rate) {
		final int consumedFuel;
		
		consumedFuel = distance/rate;
		currentFuel = currentFuel - consumedFuel;
		
		return consumedFuel;
	}
    
}
