package com.snhu.cs320.contact;

import java.util.*;

/**
 * Enhanced ContactService (Algorithms & Data Structures):
 *
 * Data structures:
 *  - HashMap<String, Contact> byId: O(1) average lookup/update/delete by contactId
 *  - TreeMap<String, Set<String>> lastNameIndex: supports prefix search of last names
 *      * lookup for a prefix is O(log n) to find the range + O(k) to iterate results
 *
 * This allows the service to demonstrate intentional trade-offs: extra memory for
 * better performance and additional querying capabilities.
 */
public class ContactService {

    private final Map<String, Contact> byId = new HashMap<>();
    private final NavigableMap<String, Set<String>> lastNameIndex = new TreeMap<>();

    public void addContact(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact must not be null");
        String id = contact.getContactId();

        if (byId.containsKey(id)) {
            throw new IllegalArgumentException("contactId must be unique");
        }
        byId.put(id, contact);
        indexLastName(contact.getLastName(), id);
    }

    public boolean deleteContact(String contactId) {
        Contact removed = byId.remove(contactId);
        if (removed == null) return false;

        deindexLastName(removed.getLastName(), contactId);
        return true;
    }

    public boolean updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact c = byId.get(contactId);
        if (c == null) return false;

        if (firstName != null && !firstName.isBlank()) c.setFirstName(firstName);
        if (phone != null && !phone.isBlank()) c.setPhone(phone);
        if (address != null && !address.isBlank()) c.setAddress(address);

        if (lastName != null && !lastName.isBlank()) {
            // Update the index in a consistent way: remove old, set new, add new.
            String oldLast = c.getLastName();
            deindexLastName(oldLast, contactId);
            c.setLastName(lastName);
            indexLastName(c.getLastName(), contactId);
        }
        return true;
    }

    public Contact getById(String contactId) {
        return byId.get(contactId);
    }

    /**
     * Prefix search by last name (case-insensitive).
     * Example: prefix "tr" returns "Trimble", "Trujillo", etc.
     */
    public List<Contact> searchByLastNamePrefix(String prefix) {
        String p = Validators.normalizedLower(prefix);
        if (p.isEmpty()) return List.of();

        // Upper bound for prefix range: prefix + highest possible char
        String upper = p + '￿';

        NavigableMap<String, Set<String>> sub = lastNameIndex.subMap(p, true, upper, true);

        List<Contact> results = new ArrayList<>();
        for (Set<String> ids : sub.values()) {
            for (String id : ids) {
                Contact c = byId.get(id);
                if (c != null) results.add(c);
            }
        }

        // Stable, predictable ordering for UI / tests
        results.sort(Comparator.comparing(Contact::getLastName).thenComparing(Contact::getFirstName));
        return results;
    }

    public int size() {
        return byId.size();
    }

    private void indexLastName(String lastName, String contactId) {
        String key = Validators.normalizedLower(lastName);
        lastNameIndex.computeIfAbsent(key, k -> new HashSet<>()).add(contactId);
    }

    private void deindexLastName(String lastName, String contactId) {
        String key = Validators.normalizedLower(lastName);
        Set<String> ids = lastNameIndex.get(key);
        if (ids == null) return;

        ids.remove(contactId);
        if (ids.isEmpty()) lastNameIndex.remove(key);
    }
}
