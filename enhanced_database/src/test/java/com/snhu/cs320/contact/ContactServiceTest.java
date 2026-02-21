package com.snhu.cs320.contact;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private ContactService newServiceWithTempDb() throws Exception {
        Path dbFile = Files.createTempFile("cs499-contacts-", ".db");
        dbFile.toFile().deleteOnExit();
        String url = "jdbc:sqlite:" + dbFile.toAbsolutePath();
        return new ContactService(new SqliteContactDAO(url));
    }

    @Test
    void testAddAndGetPersists() throws Exception {
        ContactService service = newServiceWithTempDb();

        Contact c = new Contact("1", "John", "Doe", "5551234567", "123 Main St");
        service.addContact(c);

        Contact fetched = service.getById("1");
        assertNotNull(fetched);
        assertEquals("John", fetched.getFirstName());
        assertEquals("Doe", fetched.getLastName());
    }

    @Test
    void testDuplicateIdThrows() throws Exception {
        ContactService service = newServiceWithTempDb();

        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                service.addContact(new Contact("1", "Jane", "Doe", "5559990000", "456 Oak Ave"))
        );
        assertTrue(ex.getMessage().toLowerCase().contains("unique"));
    }

    @Test
    void testUpdateContact() throws Exception {
        ContactService service = newServiceWithTempDb();

        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));

        boolean updated = service.updateContact("1", "Johnny", null, null, "999 New St");
        assertTrue(updated);

        Contact fetched = service.getById("1");
        assertEquals("Johnny", fetched.getFirstName());
        assertEquals("Doe", fetched.getLastName());
        assertEquals("999 New St", fetched.getAddress());
    }

    @Test
    void testDeleteContact() throws Exception {
        ContactService service = newServiceWithTempDb();

        service.addContact(new Contact("1", "John", "Doe", "5551234567", "123 Main St"));
        assertNotNull(service.getById("1"));

        assertTrue(service.deleteContact("1"));
        assertNull(service.getById("1"));

        assertFalse(service.deleteContact("1"));
    }
}
