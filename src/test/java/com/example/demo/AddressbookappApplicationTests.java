package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
