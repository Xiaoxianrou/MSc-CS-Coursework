package coursework_part2;

/**
 * UseSemaphores.java
 * Run these three Threads and print the results
 * 
 * @author junyan
 * @version 1.0
 */
public class UseSemaphores {
	/**
	 * fields for objects of class UseSemaphores
	 */
	private static BinarySemaphore XSemaphore = new BinarySemaphore(1);
	private static BinarySemaphore YSemaphore = new BinarySemaphore();
	private static Semaphore ZSemaphore = new Semaphore();
	
	/**
	 * Main method
	 */
	public static void main(String[] args) throws InterruptedException{
		Thread X = new ThreadX("X", XSemaphore, YSemaphore, ZSemaphore);
		Thread Y = new ThreadY("Y", XSemaphore, YSemaphore, ZSemaphore);
		Thread Z = new ThreadZ("Z", ZSemaphore);
		X.start();
		Y.start();
		Z.start();
		Thread.sleep(5000);
		System.exit(0); 
	}
}
