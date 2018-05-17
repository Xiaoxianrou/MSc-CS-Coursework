package car;

/**
 * SmallCar.java
 * Small car class extends abstrct class AbstractCar.
 * Override "drive" method
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public final class SmallCar extends AbstractCar{
	/**
	 * fields for objects of class SmallCar
	 */
	private final int capacity = 49;
	private final int rate = 20;
	
	/**
	 * Constructor for objects of class SmallCar
	 * <ul>
	 * <li>Set the fuel capacity of the small car</li>
	 * <li>Set the current fuel of the small car. At first it is same as the fuel capacity
	 * </ul>
	 */
	public SmallCar() {
		super();
		super.setFuelCapacity(capacity);
		super.setCurrentFuel(capacity);
	}
	
	/**
	 * A method to "drive" the small car for a given number of whole Kilometres.
	 * The drive method calculates the amount of fuel consumed during a journey, deducts that amount from the fuel in the tank.  
	 * <ul>
	 * <li>A car cannot be driven if it is not currently rented.</li>
	 * <li>A car cannot be driven if it has 0 or less Litres of fuel in its tank.</li>
	 * <li>A small car consumes fuel at the rate of 20 Kilometres/Litre
	 * </ul>
	 * 
	 * @return  the amount of fuel consumed. if not appropriate return -1
	 * @see car.Car#drive(int)
	 */
	@Override
	public int drive(int distance) {
		final int consumedFuel;
		
		if(distance < 0) {
			return -1;
		}
		if(!super.isRented()) {
			return -1;
		}
		if(super.getCurrentFuel() <= 0) {
			return -1;
		}
		consumedFuel = super.drive(distance, rate);
		
		return consumedFuel;
	}
}
