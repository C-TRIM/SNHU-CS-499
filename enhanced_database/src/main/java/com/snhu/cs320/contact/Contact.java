package com.snhu.cs320.contact;

/**
 * Baseline Contact entity from CS-320 style requirements.
 * - contactId: required, <= 10 chars, not null, not updatable
 * - firstName: required, <= 10 chars
 * - lastName: required, <= 10 chars
 * - phone: required, exactly 10 digits
 * - address: required, <= 30 chars
 */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = requireNonBlankMax(contactId, 10, "contactId");
        this.firstName = requireNonBlankMax(firstName, 10, "firstName");
        this.lastName = requireNonBlankMax(lastName, 10, "lastName");
        this.phone = requirePhone(phone);
        this.address = requireNonBlankMax(address, 30, "address");
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setFirstName(String firstName) { this.firstName = requireNonBlankMax(firstName, 10, "firstName"); }
    public void setLastName(String lastName) { this.lastName = requireNonBlankMax(lastName, 10, "lastName"); }
    public void setPhone(String phone) { this.phone = requirePhone(phone); }
    public void setAddress(String address) { this.address = requireNonBlankMax(address, 30, "address"); }

    private static String requireNonBlankMax(String value, int maxLen, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " must not be null/blank");
        if (value.length() > maxLen) throw new IllegalArgumentException(field + " must be <= " + maxLen + " characters");
        return value;
    }

    private static String requirePhone(String value) {
        if (value == null) throw new IllegalArgumentException("phone must not be null");
        if (value.length() != 10) throw new IllegalArgumentException("phone must be exactly 10 digits");
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) throw new IllegalArgumentException("phone must contain digits only");
        }
        return value;
    }
}
