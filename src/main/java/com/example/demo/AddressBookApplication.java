package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.repository.AddressBookRepository;
import com.example.demo.service.AddressBook;
import com.example.demo.service.AddressBookManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AddressBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
		System.out.println("Welcome to Address Book Program");
		Scanner sc = new Scanner(System.in);
		AddressBookManager manager = new AddressBookManager();
		AddressBookRepository repository = new AddressBookRepository();

		while (true) {
			System.out.println("1 Create Address Book");
			System.out.println("2 Use Address Book");
			System.out.println("3 Show Address Book");
			System.out.println("4 Search Person by City");
			System.out.println("5 Search Person by State");
			System.out.println("6 Contact count by city");
			System.out.println("7 Contact count by state");
			System.out.println("8 Exit");

			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 1) {
				manager.createAddressBook();
			}

			else if (choice == 2) {
				AddressBook book = manager.getAddressBook();
				if (book == null) continue;

				while (true) {
					System.out.println("1 Add Contact");
					System.out.println("2 Edit Contact");
					System.out.println("3 Delete Contact");
					System.out.println("4 Display Contacts");
					System.out.println("5 Sort Contacts By Name");
					System.out.println("6 Sort Contacts By City, State and Zip");
					System.out.println("7 Write Contacts To File");
					System.out.println("8 Read Contacts From File");
					System.out.println("9 Write Contacts To CSV");
					System.out.println("10 Read Contacts To CSV");
					System.out.println("11 Write Contacts To JSON");
					System.out.println("12 Read Contacts From JSON");
					System.out.println("13 Add Contact To Database");
					System.out.println("13 Add Contact To Database");
					System.out.println("14 Add Contact To Database (Transaction)");
					System.out.println("15 Retrieve from Database");
					System.out.println("16 Update contacts on Database based on first and lastname");
					System.out.println("17 Get contact by Date range");
					System.out.println("18 Get Contact By City From DB");
					System.out.println("19 Get Contact By State From DB");
					System.out.println("20 Add multiple Contacts with thred");
					System.out.println("21 Exit");

					
					int option = sc.nextInt();
					sc.nextLine();

					if (option == 1) {
						Contact contact = book.addContact();
						manager.addToCityAndStateMap(contact);
					}

					else if (option == 2) {
						book.editContact();
						manager.rebuildCityAndStateMaps();
					}

					else if (option == 3) {
						Contact removed = book.deleteContact();
						manager.removeFromCityAndStateMap(removed);
					}

					else if (option == 4) {
						book.displayContacts();
					}

					else if(option == 5) {
						book.sortContactsByName();
						book.displayContacts();
					}

					else if (option == 6) {
						book.sortByCityStateZip();
						book.displayContacts();
					}
					else if(option == 7) {
						book.writeToFile();
					}
					else if(option == 8){
						book.readFromFile();
					}
					else if (option == 9) {
						book.writeToCSV();;
					}
					else if (option == 10) {
						book.readFromCSV();
					}
					else if(option == 11 ){
						book.readFromJSON();;
					}
					else if (option == 12) {
						book.writeToJSON();
					}
					else if (option == 13) {
						repository.addContact();
					}
					else if (option == 14) {
						repository.addContactWithTransaction();
					}
					else if (option == 15) {
						repository.retrieveContacts();
					}
					else if (option == 16) {
						repository.updateContact();;
					}
					else if (option == 17) {
						System.out.println("Enter Start Date (yyyy-mm-dd):");
	                    String startDate = sc.nextLine();

	                    System.out.println("Enter End Date (yyyy-mm-dd):");
	                    String endDate = sc.nextLine();

	                    repository.getContactsByDateRange(startDate,endDate);
					}
					else if (option == 18) {
						System.out.println("Enter city:");
						String city = sc.nextLine();

						int count = repository.getContactCountByCity(city);

						System.out.println("Total contacts in city " +city + " : " + count);
					}
					else if (option == 19) {
						System.out.println("Enter state:");
						String state = sc.nextLine();

						int count = repository.getContactCountByState(state);

						System.out.println("Total contacts in state "+  state+ " : " + count);
					}
					else if(option == 20){

					    List<Contact> contacts = new ArrayList<>();

					    contacts.add(new Contact("Mahaveer","Rathour","Indore","Indore","MP","452001","9999999999","aman@gmail.com"));
					    contacts.add(new Contact("Raghunath","Vaishnav","Ujjain","Ujjain","MP","456001","8888888888","kanha@gmail.com"));

					    repository.addMultipleContacts(contacts);
					}
					else if (option == 21) {
						break;
					}
				}
			}

			else if (choice == 3) manager.displayAddressBooks();
			else if (choice == 4) manager.searchByCity();
			else if (choice == 5) manager.searchByState();
			else if (choice == 6) manager.countByCity();
			else if (choice == 7) manager.countByState();
			else if (choice == 8) break;
		}
	}
}