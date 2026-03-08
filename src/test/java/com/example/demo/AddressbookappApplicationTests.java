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
                repo.getContact("Kanha", "Vaishnav");

        Assertions.assertTrue(contactFromMemory.equals(contactFromDB));
    }
}
