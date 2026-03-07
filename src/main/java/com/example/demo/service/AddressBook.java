package com.example.demo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import com.example.demo.model.Contact;

public class AddressBook {
    ArrayList<Contact> contactList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Contact addContact() {
        System.out.println("Enter First Name:");
        String firstName = sc.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = sc.nextLine();

        Contact newContact = new Contact();
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);

        if (contactList.contains(newContact)) {
            System.out.println("Contact already exists with name " + firstName + " " + lastName);
            return null;
        }

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Enter City:");
        String city = sc.nextLine();

        System.out.println("Enter State:");
        String state = sc.nextLine();

        System.out.println("Enter Zip:");
        String zip = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phone = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone, email);

        contactList.add(contact);

        System.out.println("Contact added successfully!");
        return contact;
    }

    public void editContact() {
        System.out.println("Enter the First Name of the contact to edit:");
        String name = sc.nextLine();

        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {

                System.out.println("Enter new Address:");
                contact.setAddress(sc.nextLine());

                System.out.println("Enter new City:");
                contact.setCity(sc.nextLine());

                System.out.println("Enter new State:");
                contact.setState(sc.nextLine());

                System.out.println("Enter new Zip:");
                contact.setZip(sc.nextLine());

                System.out.println("Enter new Phone Number:");
                contact.setPhoneNumber(sc.nextLine());

                System.out.println("Enter new Email:");
                contact.setEmail(sc.nextLine());

                System.out.println("Contact updated successfully!");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    public Contact deleteContact() {
        System.out.println("Enter the First Name of the contact to delete:");
        String name = sc.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFirstName().equalsIgnoreCase(name)) {
                Contact removed = contactList.remove(i);
                System.out.println("Contact deleted successfully!");
                return removed;
            }
        }

        System.out.println("Contact not found.");
        return null;
    }

    public void displayContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    public ArrayList<Contact> getContacts() {
        return contactList;
    }

    public void sortContactsByName() {
        contactList.sort((c1, c2) ->
                c1.getFirstName().compareToIgnoreCase(c2.getFirstName())
        );

        System.out.println("Contacts sorted successfully.");
    }

    public void sortByCityStateZip() {
        contactList.stream()
                .sorted(Comparator
                        .comparing(Contact::getCity)
                        .thenComparing(Contact::getState)
                        .thenComparing(Contact::getZip))
                .forEach(System.out::println);
    }
    
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter("AddressBook.txt");
            for (Contact contact : contactList) {

                writer.write(contact.getFirstName() + "," +
                        contact.getLastName() + "," +
                        contact.getAddress() + "," +
                        contact.getCity() + "," +
                        contact.getState() + "," +
                        contact.getZip() + "," +
                        contact.getPhoneNumber() + "," +
                        contact.getEmail());
                writer.write("\n");
            }

            writer.close();
            System.out.println("Contacts written to file successfully.");
        } 
        catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
    
    public void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("AddressBook.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}