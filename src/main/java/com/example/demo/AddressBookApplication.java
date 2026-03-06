package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.AddressBook;
import com.example.demo.service.AddressBookManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AddressBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
		System.out.println("Welcome to Address Book Program");
		Scanner sc = new Scanner(System.in);
		AddressBookManager manager = new AddressBookManager();

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
					System.out.println("5 Exit");

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

					else if (option == 5) {
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