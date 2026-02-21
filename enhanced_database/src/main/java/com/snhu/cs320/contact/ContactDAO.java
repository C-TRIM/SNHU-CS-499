package com.snhu.cs320.contact;

import java.util.List;

/**
 * Data Access Object (DAO) abstraction for Contact persistence.
 * This allows ContactService to remain storage-agnostic.
 */
public interface ContactDAO {

    void create(Contact contact);

    Contact findById(String contactId);

    List<Contact> findAll();

    void update(Contact contact);

    boolean deleteById(String contactId);
}
