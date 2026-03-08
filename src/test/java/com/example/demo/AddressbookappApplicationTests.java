package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
