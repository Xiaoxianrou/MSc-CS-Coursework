package coursework2;

import java.util.*;
import java.io.*;

/**
 * The office class main method show a menu and each option method.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Office {
	/**
	 * fields for objects of class Office
	 */
	private Store store;
	private Scanner scanner;

	/**
	 * Constructor for objects of class Office 
	 */
	public Office() {
		store = new Store();
		scanner = new Scanner(System.in);
	}

	/**
	 * main method</p>
	 * The menu contains:
	 * <ul>
	 * <li>f: to finish running the program.</li>
	 * <li>e: to display on the screen the information about all the events</li>
	 * <li>c: to display on the screen the information about all the clients</li>
	 * <li>b: to update the stored data when tickets are bought by one of the registered clients</li>
	 * <li>r: to update the stored data when a registered client cancels/returns tickets</li>
	 * </ul> 
	 */
	public static void main(String args[]) throws IOException {
		Office office = new Office();
		boolean finished = false;
		// read file
		FileReader fileReader = new FileReader("src/input");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		// store data
		office.storeData(bufferReader);

		while (!finished) {
			String init;

			System.out.println("-------------------");
			System.out.println("Welcome to the club menu. Please input the option letter");
			System.out.println("f - to finish running the program.");
			System.out.println("e - to display on the screen the information about all the events");
			System.out.println("c - to display on the screen the information about all the clients");
			System.out
					.println("b - to update the stored data when tickets are bought by one of the registered clients");
			System.out.println("r - to update the stored data when a registered client cancels/returns tickets.");
			System.out.println("-------------------");

			init = office.scanner.nextLine().trim();
			switch (init) {
			case "f":
				office.optionF();
				System.out.println("See you");
				finished = true;
				break;
			case "e":
				office.optionE();
				break;
			case "c":
				office.optionC();
				break;
			case "b":
				office.optionB();
				break;
			case "r":
				office.optionR();
				break;
			default:
				System.out.println("Please enter a valid option letter");
				break;
			}
		}
	}

	/**
	 * split the input file, then use ArrayList to store event and client
	 */
	public void storeData(BufferedReader file) throws IOException {
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		int eventAll = 0;
		Integer eventNum = 0;
		String eventName = "";

		// split file into array list
		while ((line = file.readLine()) != null) {
			lines.add(line);
		}
		// store event
		eventAll = Integer.parseInt(lines.get(0));
		for (int i = 1; i < eventAll * 2 + 1; i += 2) {
			eventName = lines.get(i);
			eventNum = Integer.parseInt(lines.get(i + 1));
			store.addEvent(eventName, eventNum);
		}
		// store client
		for (int j = 1 + eventAll * 2 + 1; j < lines.size(); j++) {
			String[] names = lines.get(j).split(" ");
			store.addClient(names[0], names[1]);
		}
	}

	/**
	 * option f
	 */
	public void optionF() {
		String finish = "";
		SortedArrayList<Event> eventList = store.getEventList();
		SortedArrayList<Client> clientList = store.getClientList();
		String eventSentence = "";
		String clientSentence = "";

		for (int i = 0; i < eventList.size(); i++) {
			eventSentence = eventSentence + eventList.get(i).getName() + '\n' + eventList.get(i).getAvailable() + '\n';
		}
		for (int i = 0; i < clientList.size(); i++) {
			clientSentence = clientSentence + clientList.get(i).getFirstName() + ' ' + clientList.get(i).getSurName()
					+ '\n';
		}

		finish = eventList.size() + "\n" + eventSentence + clientList.size() + "\n" + clientSentence;

		try {
			FileWriter fileWriter = new FileWriter("src/finish.txt");
			fileWriter.write(finish);
			fileWriter.close();
		} catch (Exception e) {
			System.out.println("IO Error");
		}
	}

	/**
	 * option e
	 */
	public void optionE() {
		SortedArrayList<Event> eventList = store.getEventList();

		System.out.println("-------------------");
		for (int i = 0; i < eventList.size(); i++) {
			System.out.println(eventList.get(i).getName() + "   " + eventList.get(i).getAvailable());
		}
		System.out.println("-------------------");

		System.out.println("Press enter return, then back to the main menu");
		scanner.nextLine();
	}

	/**
	 * option c
	 */
	public void optionC() {
		SortedArrayList<Client> clientList = store.getClientList();
		HashMap<String, Integer> ticketList;

		System.out.println("-------------------");
		for (int i = 0; i < clientList.size(); i++) {
			String ticketDetails = "";
			ticketList = clientList.get(i).getTickets();
			for (Map.Entry<String, Integer> ticket : ticketList.entrySet()) {
				ticketDetails = ticketDetails + ticket.getKey() + " " + ticket.getValue() + "   ";
			}
			System.out.println(
					clientList.get(i).getFirstName() + " " + clientList.get(i).getSurName() + "   " + ticketDetails);
		}
		System.out.println("-------------------");

		System.out.println("Press enter return, then back to the main menu");
		scanner.nextLine();
	}

	/**
	 * option b
	 */
	public void optionB() {
		String firstName = "";
		String surName = "";
		String eventName = "";
		String message;
		int amount;
		int choose;
		boolean finish = false;

		while (!finish) {
			System.out.println("enter the client first name");
			firstName = checkName();
			System.out.println("enter the client surname");
			surName = checkName();
			System.out.println("enter the event name");
			eventName = checkName();

			if (!store.checkClientValid(firstName, surName)) {
				message = "Sorry the client is invalid";
			} else if (!store.checkEventValid(eventName)) {
				message = "Sorry the event is invalid";
			} else {
				System.out.println("enter how many tickets do you want to buy");
				amount = checkInt();
				int purchaseState = store.buyEventTicket(firstName, surName, eventName, amount);
				message = purchaseState == 0 ? "Completely" : "Sorry, not available tickets";
				printLetter(firstName, eventName, purchaseState);
			}

			System.out.println("-------------------");
			System.out.println(message);
			System.out.println("(1). Continue buy tickets");
			System.out.println("(2). Back to the main menu");
			System.out.println("-------------------");
			choose = checkInt(1, 2);
			if (choose == 2) {
				finish = true;
			}
		}
	}

	/**
	 * option r
	 */
	public void optionR() {
		String firstName = "";
		String surName = "";
		String eventName = "";
		String message;
		int amount;
		int choose;
		boolean finish = false;

		while (!finish) {
			System.out.println("enter the client first name");
			firstName = scanner.nextLine();
			System.out.println("enter the client surname");
			surName = scanner.nextLine();
			System.out.println("enter the event name");
			eventName = scanner.nextLine();

			if (!store.checkClientValid(firstName, surName)) {
				message = "Sorry the client is invalid";
			} else if (!store.checkEventValid(eventName)) {
				message = "Sorry the event is invalid";
			} else {
				System.out.println("enter how many tickets do you want to cancel");
				amount = checkInt();
				int purchaseState = store.cancelEventTicket(firstName, surName, eventName, amount);
				message = purchaseState == 0 ? "Completely" : "Sorry, you haven't buy " + amount + " tickets";
			}

			System.out.println("-------------------");
			System.out.println(message);
			System.out.println("(1). Continue cancel tickets");
			System.out.println("(2). Back to the main menu");
			System.out.println("-------------------");
			choose = checkInt(1, 2);
			if (choose == 2) {
				finish = true;
			}
		}
	}

	/**
	 * print letter to a single file
	 *
	 * @param  firstName  the client first name
	 * @param  eventName  the event name
	 * @param  state the result number from class Store's method buyEventTicket
	 */
	public void printLetter(String firstName, String eventName, int state) {
		String content = "";
		String article = "";

		switch (state) {
		case 0:
			content = "Congradulation. Purchase Completely.";
			break;
		case 100:
			content = "Sorry. Purchase failure.";
			break;
		case 101:
			content = "Sorry. Invalid client.";
			break;
		case 102:
			content = "Sorry. Not exist event.";
			break;
		case 103:
			content = "Sorry. the tickets are not available.";
			break;
		case 104:
			content = "Sorry. A client can only buy tickets for at most 3 events";
			break;
		}
		article = "Dear " + firstName + ", \n" + content;

		try {
			FileWriter fileWriter = new FileWriter("src/output.txt");
			fileWriter.write(article);
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("IO Error");
		}
	}

	/**
	 * check name type
	 *
	 * @return  name
	 */
	public String checkName() {
		String name;

		do {
			name = scanner.nextLine();

			if (name.matches("[a-zA-z]+([a-zA-z]|\\s)*")) {
				break;
			} else {
				System.out.println("please input correct name type");
			}
		} while (true);

		return name;
	}

	/**
	 * check int type
	 *
	 * @return int number
	 */
	public int checkInt() {
		int result;

		do {
			try {
				String s = scanner.nextLine();
				result = Integer.parseInt(s);

				if (result > 0) {
					break;
				} else {
					System.out.println("please input number more than zero");
				}
			} catch (Exception e) {
				System.out.println("please input int number more than zero");
			}
		} while (true);

		return result;
	}

	/**
	 * check int type and given range
	 *
	 * @param  min  the min range
	 * @param  max  the max range
	 * @return  choose option
	 */
	public int checkInt(int min, int max) {
		int choose;

		do {
			try {
				String s = scanner.nextLine();
				choose = Integer.parseInt(s);

				if (choose >= min && choose <= max) {
					break;
				} else {
					System.out.println("please input from " + min + " to " + max);
				}
			} catch (Exception e) {
				System.out.println("please input int number from " + min + " to " + max);
			}
		} while (true);

		return choose;
	}
}
