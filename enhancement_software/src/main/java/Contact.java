/**
 * Represents a Contact with an immutable ID and updatable firstName, lastName, phone, and address.
 * Enforces non-null constraints, maximum length limits, and strict phone formatting.
 */
public class Contact {

    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_ADDRESS_LENGTH = 30;
    private static final String PHONE_REGEX = "\\d{10}";

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateId(contactId);
        this.contactId = contactId;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    private void validateId(String id) {
        if (id == null || id.length() > MAX_ID_LENGTH) {
            throw new IllegalArgumentException("Invalid contactId");
        }
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid firstName");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid lastName");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException("Invalid phone");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }
}
