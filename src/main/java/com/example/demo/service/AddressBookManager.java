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

    public void displayAddressBooks(){
        for (String book : addressBooks.keySet()){
            System.out.println(book);
        }
    }
}