import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void validTaskConstructs() {
        Task t = new Task("t1", "DoThing", "This is fine");
        assertEquals("t1", t.getTaskId());
        assertEquals("DoThing", t.getName());
        assertEquals("This is fine", t.getDescription());
    }

    @Test
    void constructorValidations() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "N", "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("01234567890", "N", "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("t2", null, "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("t2", "X".repeat(21), "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("t3", "N", null));
        assertThrows(IllegalArgumentException.class, () -> new Task("t3", "N", "Y".repeat(51)));
    }

    @Test
    void settersValidate() {
        Task t = new Task("t4", "A", "B");
        t.setName("B");
        t.setDescription("C");
        assertEquals("B", t.getName());
        assertEquals("C", t.getDescription());
        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));
    }
}
