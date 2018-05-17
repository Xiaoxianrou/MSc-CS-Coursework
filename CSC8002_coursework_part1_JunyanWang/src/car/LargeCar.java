package car;

/**
 * LargeCar.java
 * Large car class extends abstract class AbstractCar.
 * Override "drive" method
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public final class LargeCar extends AbstractCar{
	/**
	 * fields for objects of class LargeCar
	 */
	private final int capacity = 60;
	private final int firstRate = 10;
	private final int lastRate = 15;
	
	/**
	 * Constructor for objects of class LargeCar
	 * <ul>
	 * <li>Set the fuel capacity of the large car</li>
	 * <li>Set the current fuel of the large car. At first it is same as the fuel capacity
	 * </ul>
	 */
	public LargeCar() {
		super();
		super.setFuelCapacity(capacity);
		super.setCurrentFuel(capacity);
	}
	
	/**
	 * A method to "drive" the large car for a given number of whole Kilometres.
	 * The drive method calculates the amount of fuel consumed during a journey, deducts that amount from the fuel in the tank.  
	 * <ul>
	 * <li>A car cannot be driven if it is not currently rented.</li>
	 * <li>A car cannot be driven if it has 0 or less Litres of fuel in its tank.</li>
	 * <li>A large car consumes fuel at the rate of 10 Kilometres/Litre for the first 50 Kilometres of a journey and then at the rate of 15 Kilometres/Litre for the remainder of the journey.</li>
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
		
		if (distance <= 50) {
			consumedFuel = super.drive(distance, firstRate);
		}else {
			consumedFuel = super.drive(50, firstRate) + super.drive((distance -50), lastRate);
		}
		
		return consumedFuel;
	}
}
