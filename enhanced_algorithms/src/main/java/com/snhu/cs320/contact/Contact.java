package com.snhu.cs320.contact;

import java.util.Objects;

/**
 * Enhanced Contact entity.
 * Validation rules match CS-320 requirements and are centralized for reuse.
 */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = Validators.requireNonBlankMax(contactId, 10, "contactId");
        this.firstName = Validators.requireNonBlankMax(firstName, 10, "firstName");
        this.lastName = Validators.requireNonBlankMax(lastName, 10, "lastName");
        this.phone = Validators.requirePhone(phone);
        this.address = Validators.requireNonBlankMax(address, 30, "address");
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setFirstName(String firstName) { this.firstName = Validators.requireNonBlankMax(firstName, 10, "firstName"); }
    public void setLastName(String lastName) { this.lastName = Validators.requireNonBlankMax(lastName, 10, "lastName"); }
    public void setPhone(String phone) { this.phone = Validators.requirePhone(phone); }
    public void setAddress(String address) { this.address = Validators.requireNonBlankMax(address, 30, "address"); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}
