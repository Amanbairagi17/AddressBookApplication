package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repository.AddressBookRepository;

@SpringBootTest
class AddressbookappApplicationTests {

	
	@Test
	public void shouldRetrieveContactsFromDatabase() {

        AddressBookRepository repo = new AddressBookRepository();

        assertTrue(repo.retrieveContacts() >= 0);
    }
    @Test
    public void update_contanct_syncWithDB() {

        AddressBookRepository repo = new AddressBookRepository();

        Contact contactFromDB = repo.updateContact();

        Contact contactFromMemory =
                repo.getContact("Ram", "Bhai");

        Assertions.assertTrue(contactFromMemory.equals(contactFromDB));
    }
    
    @Test
    public void givenCity_WhenRetrieveContactCount_ShouldReturnCount() {

        AddressBookRepository repo = new AddressBookRepository();

        int count = repo.getContactCountByCity("Bhopal");

        Assertions.assertTrue(count >= 0);
    }
    
    @Test
    public void givenState_WhenRetrieveContactCount_ShouldReturnCount() {

        AddressBookRepository repo = new AddressBookRepository();

        int count = repo.getContactCountByState("MP");

        Assertions.assertTrue(count >= 0);
    }
    
    @Test
    public void givenNewContact_WhenAdded_ShouldBeInserted() {

        AddressBookRepository repo = new AddressBookRepository();

        repo.addContactWithTransaction();

        Assertions.assertTrue(repo.retrieveContacts() > 0);
    }
    
    @Test
    public void givenMultipleContacts_WhenAdded_ShouldSyncWithDB(){

        AddressBookRepository repo = new AddressBookRepository();

        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("Mahaveer","Rathour","Indore","Indore","MP","452001","9999999999","aman@gmail.com"));
	    contacts.add(new Contact("Raghunath","Vaishnav","Ujjain","Ujjain","MP","456001","8888888888","kanha@gmail.com"));

        repo.addMultipleContacts(contacts);

        Assertions.assertTrue(repo.retrieveContacts() >= 2);
    }
}
