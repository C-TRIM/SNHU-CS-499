import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class AppointmentServiceTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
    }

    @Test
    void addRejectsDuplicate() {
        AppointmentService service = new AppointmentService();
        Appointment a = new Appointment("a1", futureDate(), "Desc");
        service.addAppointment(a);
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(a));
    }

    @Test
    void deleteNoThrow() {
        AppointmentService service = new AppointmentService();
        service.deleteAppointment("missing");
    }
}
