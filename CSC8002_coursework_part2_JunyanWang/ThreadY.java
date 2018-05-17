package coursework_part2;

/**
 * ThreadY.java
 * ThreadY class extends Class Thread.
 * Override the run method
 * 
 * @author Junyan Wang
 * @version 1.0
 */
public class ThreadY extends Thread {
	/**
	 * fields for objects of class ThreadY
	 */
	private BinarySemaphore XSemaphore;
	private BinarySemaphore YSemaphore;
	private Semaphore ZSemaphore;
	
	/**
	 * Constructor for objects of class ThreadY
	 */
	public ThreadY(String name, BinarySemaphore XSemaphore, BinarySemaphore YSemaphore, Semaphore ZSemaphore) {
		super(name);
		this.XSemaphore = XSemaphore;
		this.YSemaphore = YSemaphore;
		this.ZSemaphore = ZSemaphore;
	}
	
	/**
	 * Run method execute when the instance of ThreadY start
	 * Print "Y" repeatedly
	 */
	@Override
	public void run() {
		try{
			while(true) {
				ZSemaphore.P();
				YSemaphore.P();
				Thread.sleep((long)Math.ceil(Math.random()*999));
				System.out.println("Y");
				XSemaphore.V();
			}
		}catch(InterruptedException e) {
			System.out.println("Interrupt");
		}
	}
}
