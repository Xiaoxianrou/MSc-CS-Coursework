package coursework_part2;

/**
 * Semaphore.java
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public class Semaphore {
	/**
	 * fields for objects of class Semaphore
	 */
	protected int value;
	
	/**
	 * Constructor for objects of class Semaphore with no parameter
	 */
	public Semaphore() {
		value = 0;
	}
	
	/**
	 * Constructor for objects of class Semaphore with an int parameter
	 */
	public Semaphore(int initial) {
		value = (initial>0) ? initial : 0;
	}
	
	/**
	 * P method
	 * Decrementing a semaphore corresponds to acquiring a resource (or permission to proceed)
	 * or waiting for a resource to become available or an event to happen
	 */
	public synchronized void P() throws InterruptedException{
		while(value == 0) {
			wait();
		}
		value--;
	}
	
	/**
	 * V method
	 * Incrementing a semaphore corresponds to releasing a resource or signalling an event
	 */
	public synchronized void V() {
		value++;
		notify();
	}
}
