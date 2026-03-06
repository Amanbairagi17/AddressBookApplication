package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class AddressBookManager {
    Map<String, AddressBook> addressBooks = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public void createAddressBook() {
        System.out.println("Enter Address Book Name:");
        String name = sc.nextLine();

        if (addressBooks.containsKey(name)) {
            System.out.println("Address Book already exists");
            return;
        }

        addressBooks.put(name, new AddressBook());

        System.out.println("Address Book created successfully!");
    }

    public AddressBook getAddressBook() {
        System.out.println("Enter Address Book Name:");
        String name = sc.nextLine();

        if (!addressBooks.containsKey(name)) {
            System.out.println("Address Book not found.");
            return null;
        }

        return addressBooks.get(name);
    }

    public void searchByCity() {
        System.out.println("Enter City:");
        String city = sc.nextLine();

        addressBooks.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .forEach(System.out::println);
    }

    public void searchByState() {

        System.out.println("Enter State:");
        String state = sc.nextLine();

        addressBooks.values()
                .stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .forEach(System.out::println);
    }

    public void displayAddressBooks(){
        for (String book : addressBooks.keySet()){
            System.out.println(book);
        }
    }
}