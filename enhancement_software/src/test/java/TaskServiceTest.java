import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void addRejectsDuplicate() {
        TaskService service = new TaskService();
        Task t = new Task("t1", "N", "D");
        service.addTask(t);
        assertThrows(IllegalArgumentException.class, () -> service.addTask(t));
    }

    @Test
    void deleteNoThrow() {
        TaskService service = new TaskService();
        service.deleteTask("missing");
    }

    @Test
    void updateFields() {
        TaskService service = new TaskService();
        Task t = new Task("t2", "A", "B");
        service.addTask(t);
        service.updateName("t2", "C");
        service.updateDescription("t2", "D");
        assertEquals("C", t.getName());
        assertEquals("D", t.getDescription());
    }

    @Test
    void updateThrowsWhenMissing() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.updateName("nope", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("nope", "Y"));
    }
}
