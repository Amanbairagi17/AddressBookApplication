package com.example.demo;

import com.example.demo.service.AddressBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
		System.out.println("Welcome to Address Book Program");

		AddressBook addressBook = new AddressBook();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1 Add Contact");
			System.out.println("2 Edit Contact");
			System.out.println("3 Delete Contact");
			System.out.println("4 Display Contacts");
			System.out.println("5 Exit");

			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 1) addressBook.addContact();
			else if (choice == 2) addressBook.editContact();
			else if (choice == 3) addressBook.deleteContact();
			else if (choice == 4) addressBook.displayContacts();
			else if (choice == 5) break;
			else System.out.println("Invalid choice.");
		}
	}

}
