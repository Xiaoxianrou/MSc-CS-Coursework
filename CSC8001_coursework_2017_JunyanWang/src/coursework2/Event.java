package coursework2;

/**
 * The Event class store the event's name and the number of tickets available for this event.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Event implements Comparable<Event>{
	/**
     * fields for objects of class Event
     */
	private String name;
	private int available;
	
	/**
     * Constructor for objects of class Event
     * 
     * @param  name  the name of the Event  
     * @param  available  the number of tickets available for this event
     */
	public Event(String name, int available) {
		this.name = name;
		this.available = available;
	}

	/**
     * get the name of this event
     * 
     * @return  name  the name of this event  
     */
	public String getName() {
		return name;
	}
	
	/**
     * get available tickets number of this event
     * 
     * @return  available  the number of tickets available for this event  
     */
	public int getAvailable() {
		return available;
	}
	
	/**
     * set the new name of this event
     * 
     * @param  newName  the new name of this event  
     */
	public void getName(String newName) {
		name = newName;
	}
	
	/**
     * check available or not
     * 
     * @return  boolean  whether the client buy this event's ticket  
     */
	public boolean checkAvailable(int num) {
		if (num > available) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
     * buy tickets, compute available tickets
     * 
     * @param  num  how many ticket to buy
     * @return  boolean  whether the client buy this event's ticket  
     */
	public boolean buyTicket(int num) {
		if (num > available) {
			return false;
		}else {
			available = available - num;
			return true;
		}
	}
	
	/**
     * cancel tickets, compute available tickets
     * 
     * @param  num  how many ticket to cancel 
     */
	public void cancelTicket(int num) {
		available = available + num;
	}
	
	/**
     * compare two event
     * 
     * @return  nameCmp  compare the name of the event by alphabet
     */
	public int compareTo(Event nextEvent) {
		int nameCmp = name.compareTo(nextEvent.name);
		return nameCmp;
	}
}