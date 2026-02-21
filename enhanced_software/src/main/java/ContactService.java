import java.util.HashMap;
import java.util.Map;

/**
 * In-memory service for managing Contact objects.
 */
public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Duplicate Contact ID");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public boolean deleteContact(String contactId) {
        return contacts.remove(contactId) != null;
    }

    private Contact getRequiredContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact not found");
        }
        return contact;
    }

    public void updateFirstName(String contactId, String firstName) {
        getRequiredContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getRequiredContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getRequiredContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getRequiredContact(contactId).setAddress(address);
    }
}
