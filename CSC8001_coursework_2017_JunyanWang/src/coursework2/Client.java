package coursework2;
import java.util.*;

/**
 * The Client class store the event's name and the number of tickets available for this event.</p>
 * This class store the client's first name and surname. </p>
 * This class also use HashMap to store the tickets.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Client implements Comparable<Client>{
	/**
     * fields for objects of class Client
     */
	private String firstName;
	private String surName;
	private HashMap<String, Integer> tickets;
	
	/**
     * Constructor for objects of class Client
     * 
     * @param  firstName  the first name of the client  
     * @param  surName  the surname of the client
     */
	public Client(String firstName, String surName) {
		this.firstName = firstName;
		this.surName = surName;
		tickets = new HashMap<String, Integer>();
	}
	
	/**
     * get the client's first name
     * 
     * @return  firstName  the first name of the client   
     */
	public String getFirstName() {
		return firstName;
	}
	
	/**
     * get the client's surname
     * 
     * @return  surName  the surname of the client   
     */
	public String getSurName() {
		return surName;
	}
	
	/**
     * set the client's first name
     * 
     * @param  newFirstName  the new first name of the client   
     */
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	
	/**
     * set the client's surname
     * 
     * @param  newSurName  the new surname of the client   
     */
	public void setSurName(String newSurName) {
		surName = newSurName;
	}
	
	/**
     * compare two client
     * 
     * @return  result  if the next client's surname first letter is greater than this client's surname
     * 					then return a number bigger than 0, otherwise return a number smaller than 0
     * 				    if they have same surname, then compare their first name using the same rule.
     */
	public int compareTo(Client nextClient) {
		// firstly, compare their surname
		int surnameCompare = surName.compareTo(nextClient.surName);
		if (surnameCompare !=0) {
			return surnameCompare;
		}
		// if they have same surname, then compare their first name
		int firstNameCompare = firstName.compareTo(nextClient.firstName);
		return firstNameCompare;
	}
	
	/**
     * get the tickets of the client
     * 
     * @return  tickets  the tickets of the client     
     */
	public HashMap<String, Integer> getTickets() {
		return tickets;
	}
	
}
