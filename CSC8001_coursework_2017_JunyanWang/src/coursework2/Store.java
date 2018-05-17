package coursework2;
import java.util.*;

/**
 * The Store class store the data of the ticket office.</p>
 * This class has methods which the menu needs to use
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Store {
	/**
     * fields for objects of class Store
     */
	private SortedArrayList<Client> clientList;
	private SortedArrayList<Event> eventList;
	
	/**
     * Constructor for objects of class Store 
     */
	public Store() {
		clientList = new SortedArrayList<Client>();
		eventList = new SortedArrayList<Event>();
	}
	
	/**
     * add a new event detail into store class
     * 
     * @param  name  the name of the event  
     * @param  amount  the amount of this event ticket
     */
	public void addEvent(String name, int amount) {
		Event newEvent = new Event(name,amount);
		eventList.add(newEvent);
	}
	
	/**
     * add a new client detail into store class
     * 
     * @param  name  the name of the event  
     * @param  amount  the amount of this event ticket
     */
	public void addClient(String firstName, String surName) {
		Client newClient = new Client(firstName, surName);
		clientList.add(newClient);
	}
	
	/**
     * return event list
     * 
     * @return  eventList  stored event list 
     */
	public SortedArrayList<Event> getEventList(){
		return eventList;
	}
	
	/**
     * return client list
     * 
     * @return  clientList  stored client list 
     */
	public SortedArrayList<Client> getClientList(){
		return clientList;
	}
	
	/**
     * get select event
     * 
     * @param  eventName  select event name
     * @return  event  select event or null 
     */
	public Event getSelectEvent(String eventName){
		int index = -1;
		
		for (int i = 0; i < eventList.size(); i++) {
			if(eventList.get(i).getName().equals(eventName)) {
				index = i;
			}
		}
		
		if (index == -1) {
			return null;
		}else {
			return eventList.get(index);
		}
	}
	
	/**
     * get select client
     * 
     * @param  firstName  select client first name
     * @param  surName  select client surname
     * @return  client  select client or null 
     */
	public Client getSelectClient(String firstName, String surName){
		int index = -1;
		
		for (int i = 0; i < clientList.size(); i++) {
			if(clientList.get(i).getFirstName().equals(firstName) 
					&& clientList.get(i).getSurName().equals(surName)) 
			{
				index = i;
			}
		}
		
		if (index == -1) {
			return null;
		}else {
			return clientList.get(index);
		}
	}
	
	/**
     * check client exist
     * 
     * @param  firstName  client's first name
     * @param  surName  client's family name
     * @return  boolean  exist or not
     */
	public boolean checkClientValid(String firstName, String surName) {
		boolean valid = true;
		
		if(getSelectClient(firstName,surName) == null) {
			valid = false;
		}
		
		return valid;
	}
	
	/**
     * check event exist
     * 
     * @param  eventName  event name
     * @return  boolean  exist or not
     */
	public boolean checkEventValid(String eventName) {
		boolean valid = true;
		
		if(getSelectEvent(eventName) == null) {
			valid = false;
		}
		
		return valid;
	}
	
	/**
     * buy event ticket, return a number that represent a specific meaning.
     * <ul>
     * <li>0: Completely</li>
     * <li>100: Purchase failure</li>
     * <li>101: invalid client</li>
     * <li>102: not exist event</li>
     * <li>103: not available tickets</li>
     * <li>104: a client can only buy tickets for at most 3 events</li>
     * </ul>
     * 
     * @param  firstName  client's first name
     * @param  surName  client's family name
     * @param  eventName  event name
     * @param  amount  how many ticket does the client want to buy
     * @return  result  buy completely or not
     */
	public int buyEventTicket(String firstName, String surName, String eventName, int amount) {
		Client client;
		HashMap<String, Integer> ticketList;
		Event event;
		
		// check valid client
		client = getSelectClient(firstName, surName);
		if(client == null) {
			return 101; 
		}
		
		// check available event
		event = getSelectEvent(eventName);
		if(event == null) {
			return 102;
		}else if(!event.checkAvailable(amount)){
			return 103;
		}
		
		//buy tickets
		ticketList = client.getTickets();
		if(ticketList.size() == 3) {
			if(ticketList.containsKey(eventName)) {
				event.buyTicket(amount);
				ticketList.put(eventName, ticketList.get(eventName) + amount);
				return 0;
			}else {
				return 104;
			}
		}else if(ticketList.size() < 3) {
			if(ticketList.containsKey(eventName)) {
				ticketList.put(eventName, ticketList.get(eventName) + amount);
			}else {
				ticketList.put(eventName, amount);
			}
			event.buyTicket(amount);
			return 0;
		}else {
			return 100;
		}
	}
	
	/**
     * cancel event ticket, return a number that represent a specific meaning. 
     * <ul>
     * <li>0: Completely</li>
     * <li>101: invalid client</li>
     * <li>102: invalid event</li>
     * <li>103: event not exist</li>
     * <li>104: you did not buy that number of ticket</li>
     * </ul>
     * 
     * @param  firstName  client's first name
     * @param  surName  client's family name
     * @param  eventName  event name
     * @param  amount  how many ticket does the client want to cancel
     * @return  result  cancel completely or not
     */
	public int cancelEventTicket(String firstName, String surName, String eventName, int amount) {
		Client client;
		Event event;
		HashMap<String, Integer> ticketList;
		
		// check valid client
		client = getSelectClient(firstName, surName);
		if(client == null) {
			return 101; 
		}
		
		// check valid event
		event = getSelectEvent(eventName);
		if(event == null) {
			return 102;
		}
		
		// check ticket exist
		ticketList = client.getTickets();
		if(!ticketList.containsKey(eventName)) {
			return 103;
		}else if(amount > ticketList.get(eventName)) {
			return 104;
		}else {
			ticketList.put(eventName, ticketList.get(eventName) - amount);
			if(ticketList.get(eventName) == 0) {
				ticketList.remove(eventName);
			}
			event.cancelTicket(amount);
			return 0;
		}
	}
}
