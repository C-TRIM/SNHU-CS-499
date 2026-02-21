package com.snhu.cs320.contact;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    @Test
    void addAndLookupIsO1AverageById() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));
        service.addContact(new Contact("A2", "Ana", "Trimble", "1112223333", "2 Main St"));

        assertEquals(2, service.size());
        assertNotNull(service.getById("A2"));
        assertEquals("Ana", service.getById("A2").getFirstName());
    }

    @Test
    void duplicateIdThrows() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(new Contact("A1", "Other", "Person", "0987654321", "2 Main St")));
    }

    @Test
    void deleteUpdatesIndex() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));
        service.addContact(new Contact("A2", "Ana", "Trujillo", "1112223333", "2 Main St"));

        assertEquals(2, service.searchByLastNamePrefix("tr").size());
        assertTrue(service.deleteContact("A2"));
        assertEquals(1, service.searchByLastNamePrefix("tr").size());
        assertNull(service.getById("A2"));
    }

    @Test
    void updateLastNameReindexesCorrectly() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));

        assertEquals(1, service.searchByLastNamePrefix("tri").size());
        assertTrue(service.updateContact("A1", null, "Turner", null, null));
        assertEquals(0, service.searchByLastNamePrefix("tri").size());
        assertEquals(1, service.searchByLastNamePrefix("tur").size());
    }

    @Test
    void prefixSearchIsCaseInsensitiveAndSorted() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));
        service.addContact(new Contact("A2", "Ana", "trujillo", "1112223333", "2 Main St"));
        service.addContact(new Contact("A3", "Ben", "Trent", "2223334444", "3 Main St"));

        List<Contact> results = service.searchByLastNamePrefix("TR");
        assertEquals(3, results.size());
        assertEquals("Trent", results.get(0).getLastName());
        assertEquals("Trimble", results.get(1).getLastName());
        assertEquals("trujillo", results.get(2).getLastName()); // preserves original case in entity
    }

    @Test
    void validationGuardsInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("A1", "Chris", "Trimble", "123", "1 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("A1", "Chris", "Trimble", "123456789X", "1 Main St"));
    }
}
