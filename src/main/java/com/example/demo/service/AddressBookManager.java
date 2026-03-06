package com.example.demo.service;

import com.example.demo.model.Contact;

import java.util.*;


public class AddressBookManager {
    Map<String, AddressBook> addressBooks = new HashMap<>();
    Map<String, List<Contact>> cityPersonMap = new HashMap<>();
    Map<String, List<Contact>> statePersonMap = new HashMap<>();
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


        if(!cityPersonMap.containsKey(city) || cityPersonMap.get(city).size() == 0) {
            System.out.println("No contact");
            return;
        }

        cityPersonMap
                .get(city)
                .stream()
                .forEach(System.out::println);
    }

    public void searchByState() {

        System.out.println("Enter State:");
        String state = sc.nextLine();

        if(!statePersonMap.containsKey(state) || statePersonMap.get(state).size() == 0) {
            System.out.println("No contact");
            return;
        }

        statePersonMap
                .get(state)
                .stream()
                .forEach(System.out::println);
    }

    public void displayAddressBooks(){

        for (String book : addressBooks.keySet()){
            System.out.println(book );
        }
    }

    public void addToCityAndStateMap(Contact contact) {
        if(!cityPersonMap.containsKey(contact.getCity())) cityPersonMap.put(contact.getCity(), new ArrayList<>());
        cityPersonMap.get(contact.getCity()).add(contact);

        if(!statePersonMap.containsKey(contact.getState())) statePersonMap.put(contact.getState(), new ArrayList<>());
        statePersonMap.get(contact.getState()).add(contact);
    }
}