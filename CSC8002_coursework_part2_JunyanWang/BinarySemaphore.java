package coursework_part2;

/**
 * BinarySemaphore.java
 * Binary Semaphore class extends Class Semaphore.
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public class BinarySemaphore extends Semaphore{
	/**
	 * Constructor for objects of class BinarySemaphore with no parameter
	 */
	public BinarySemaphore() {
		super();
	}
	
	/**
	 * Constructor for objects of class BinarySemaphore with an int parameter
	 */
	public BinarySemaphore(int initial) {
		super(initial > 0 ? 1 : 0);
	}
	
	/**
	 * Override method V
	 * if value less than 1, increment once. if not, do nothing.
	 */
	@Override
	public synchronized void V() {
		if (value < 1) {
			super.V();
		}
	} 
}
