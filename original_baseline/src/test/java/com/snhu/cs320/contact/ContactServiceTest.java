package com.snhu.cs320.contact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    @Test
    void addAndGetById() {
        ContactService service = new ContactService();
        Contact c = new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St");
        service.addContact(c);

        assertNotNull(service.getById("A1"));
        assertEquals("Chris", service.getById("A1").getFirstName());
    }

    @Test
    void duplicateIdThrows() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(new Contact("A1", "Other", "Person", "0987654321", "2 Main St")));
    }

    @Test
    void deleteContact() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));

        assertTrue(service.deleteContact("A1"));
        assertNull(service.getById("A1"));
        assertFalse(service.deleteContact("A1"));
    }

    @Test
    void updateContact() {
        ContactService service = new ContactService();
        service.addContact(new Contact("A1", "Chris", "Trimble", "1234567890", "1 Main St"));

        assertTrue(service.updateContact("A1", "Chris", "T", "1112223333", "99 Broadway"));
        assertEquals("T", service.getById("A1").getLastName());
        assertEquals("1112223333", service.getById("A1").getPhone());
        assertEquals("99 Broadway", service.getById("A1").getAddress());
    }
}
