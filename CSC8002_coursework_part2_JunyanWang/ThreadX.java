package coursework_part2;

/**
 * ThreadX.java
 * ThreadX class extends Class Thread.
 * Override the run method
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public class ThreadX extends Thread {
	/**
	 * fields for objects of class ThreadX
	 */
	private BinarySemaphore XSemaphore;
	private BinarySemaphore YSemaphore;
	private Semaphore ZSemaphore;
	
	/**
	 * Constructor for objects of class ThreadX
	 */
	public ThreadX(String name, BinarySemaphore XSemaphore, BinarySemaphore YSemaphore, Semaphore ZSemaphore) {
		super(name);
		this.XSemaphore = XSemaphore;
		this.YSemaphore = YSemaphore;
		this.ZSemaphore = ZSemaphore;
	}
	
	/**
	 * Run method execute when the instance of ThreadX start
	 * Print "X" repeatedly
	 */
	@Override
	public void run() {
		try{
			while(true) {
				ZSemaphore.P();
				XSemaphore.P();
				Thread.sleep((long)Math.ceil(Math.random()*999));
				System.out.println("X");
				YSemaphore.V();
			}
		}catch(InterruptedException e) {
			System.out.println("Interrupt");
		}
	}
}