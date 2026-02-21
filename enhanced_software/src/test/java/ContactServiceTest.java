import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    @Test
    void addAndRejectDuplicates() {
        ContactService service = new ContactService();
        Contact c1 = new Contact("id1", "Alice", "Smith", "1234567890", "456 Park Ave");
        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c1));
    }

    @Test
    void deleteByIdNoThrowWhenMissing() {
        ContactService service = new ContactService();
        service.deleteContact("missing"); // should not throw
    }

    @Test
    void updateAllFieldsHappyPath() {
        ContactService service = new ContactService();
        Contact c = new Contact("id2", "John", "Doe", "1112223333", "Addr");
        service.addContact(c);
        service.updateFirstName("id2", "Jane");
        service.updateLastName("id2", "Roe");
        service.updatePhone("id2", "9998887777");
        service.updateAddress("id2", "New");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Roe", c.getLastName());
        assertEquals("9998887777", c.getPhone());
        assertEquals("New", c.getAddress());
    }

    @Test
    void updateThrowsWhenContactMissing() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("nope", "A"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("nope", "B"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("nope", "1234567890"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("nope", "Addr"));
    }
}
