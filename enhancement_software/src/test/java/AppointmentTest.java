import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class AppointmentTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
    }

    @Test
    void validAppointment() {
        Appointment a = new Appointment("a1", futureDate(), "Dentist");
        assertEquals("a1", a.getAppointmentId());
        assertNotNull(a.getAppointmentDate());
        assertEquals("Dentist", a.getDescription());
    }

    @Test
    void constructorValidations() {
        Date past = new Date(System.currentTimeMillis() - 1000);
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, futureDate(), "D"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("01234567890", futureDate(), "D"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("a2", null, "D"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("a3", past, "D"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("a4", futureDate(), null));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("a5", futureDate(), "X".repeat(51)));
    }

    @Test
    void descriptionSetterValidates() {
        Appointment a = new Appointment("a6", futureDate(), "ok");
        a.setDescription("new");
        assertEquals("new", a.getDescription());
        assertThrows(IllegalArgumentException.class, () -> a.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> a.setDescription("X".repeat(51)));
    }
}
