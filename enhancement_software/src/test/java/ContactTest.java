import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    @DisplayName("creates contact when all fields valid")
    void createsContactWhenValid() {
        Contact c = new Contact("id123", "John", "Doe", "1112223333", "123 Elm St");
        assertNotNull(c);
        assertEquals("id123", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1112223333", c.getPhone());
        assertEquals("123 Elm St", c.getAddress());
    }

    @Test
    void rejectsNullOrTooLongId() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A", "B", "1112223333", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("01234567890", "A", "B", "1112223333", "Addr")); // 11 chars
    }

    @Test
    void firstAndLastNameConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id1", null, "B", "1112223333", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id2", "ABCDEFGHIJK", "B", "1112223333", "Addr")); // 11
        assertThrows(IllegalArgumentException.class, () -> new Contact("id3", "A", null, "1112223333", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id4", "A", "ABCDEFGHIJK", "1112223333", "Addr"));
    }

    @Test
    void phoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id5", "A", "B", null, "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id6", "A", "B", "123456789", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id7", "A", "B", "12345678901", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id8", "A", "B", "12345ABCDE", "Addr"));
    }

    @Test
    void addressConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("id9", "A", "B", "1234567890", null));
        assertThrows(IllegalArgumentException.class, () -> new Contact("id10", "A", "B", "1234567890", "X".repeat(31))); // 31 chars
    }

    @Test
    void settersEnforceConstraints() {
        Contact c = new Contact("id11", "John", "Doe", "1112223333", "Addr");
        c.setFirstName("Jane");
        c.setLastName("Roe");
        c.setPhone("9998887777");
        c.setAddress("New Address");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Roe", c.getLastName());
        assertEquals("9998887777", c.getPhone());
        assertEquals("New Address", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("bad"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}
