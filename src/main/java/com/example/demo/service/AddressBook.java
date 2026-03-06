package com.example.demo.service;

import com.example.demo.model.Contact;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    Scanner sc = new Scanner(System.in);
    ArrayList<Contact> contactList = new ArrayList<>();

    public void addContact() {


        System.out.println("Enter First Name:");
        String firstName = sc.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = sc.nextLine();

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
}
