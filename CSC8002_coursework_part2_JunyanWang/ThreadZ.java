package coursework_part2;

/**
 * ThreadZ.java
 * ThreadZ class extends Class Thread.
 * Override the run method
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public class ThreadZ extends Thread {
	/**
	 * fields for objects of class ThreadZ
	 */
	private Semaphore ZSemaphore;
	
	/**
	 * Constructor for objects of class ThreadZ
	 */
	public ThreadZ(String name, Semaphore ZSemaphore) {
		super(name);
		this.ZSemaphore = ZSemaphore;
	}
	
	/**
	 * Run method execute when the instance of ThreadZ start
	 * Print "Z" repeatedly
	 */
	public void run() {
		try{
			while(true) {
				Thread.sleep((long)Math.ceil(Math.random()*999));
				System.out.println("Z");
				ZSemaphore.V();
			}
		}catch(InterruptedException e) {
			System.out.println("Interrupt");
		}
	}
}
