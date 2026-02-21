package com.snhu.cs320.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Baseline ContactService: uses an ArrayList and linear search.
 * This is intentionally simple (O(n) lookup/update/delete).
 */
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact must not be null");
        if (exists(contact.getContactId())) throw new IllegalArgumentException("contactId must be unique");
        contacts.add(contact);
    }

    public boolean deleteContact(String contactId) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getContactId().equals(contactId)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                if (firstName != null && !firstName.isBlank()) c.setFirstName(firstName);
                if (lastName != null && !lastName.isBlank()) c.setLastName(lastName);
                if (phone != null && !phone.isBlank()) c.setPhone(phone);
                if (address != null && !address.isBlank()) c.setAddress(address);
                return true;
            }
        }
        return false;
    }

    public Contact getById(String contactId) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) return c;
        }
        return null;
    }

    private boolean exists(String contactId) {
        return getById(contactId) != null;
    }
}
