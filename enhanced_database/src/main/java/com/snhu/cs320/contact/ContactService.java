package com.snhu.cs320.contact;

import java.util.List;

/**
 * Database-enhanced ContactService:
 * - Business rules live here and in Contact validation.
 * - Persistence is handled via ContactDAO (SQLite implementation provided).
 */
public class ContactService {

    private final ContactDAO dao;

    /**
     * Default constructor uses a local SQLite file (contacts.db).
     * For tests or custom deployments, use the ContactDAO constructor.
     */
    public ContactService() {
        this(new SqliteContactDAO("jdbc:sqlite:contacts.db"));
    }

    public ContactService(ContactDAO dao) {
        if (dao == null) throw new IllegalArgumentException("dao must not be null");
        this.dao = dao;
    }

    public void addContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact must not be null");
        // DAO will enforce uniqueness at DB level; we also keep a clear error message.
        dao.create(contact);
    }

    public boolean deleteContact(String contactId) {
        return dao.deleteById(contactId);
    }

    /**
     * Updates any non-blank fields. Returns true if updated; false if not found.
     */
    public boolean updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact existing = dao.findById(contactId);
        if (existing == null) return false;

        if (firstName != null && !firstName.isBlank()) existing.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) existing.setLastName(lastName);
        if (phone != null && !phone.isBlank()) existing.setPhone(phone);
        if (address != null && !address.isBlank()) existing.setAddress(address);

        dao.update(existing);
        return true;
    }

    public Contact getById(String contactId) {
        return dao.findById(contactId);
    }

    public List<Contact> getAll() {
        return dao.findAll();
    }
}
