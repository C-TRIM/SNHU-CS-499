import java.util.HashMap;
import java.util.Map;

/**
 * Manages Appointment objects in memory.
 * Supports adding and deleting by appointmentId.
 */
public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    /** Adds an Appointment if non-null and ID unique. */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("Duplicate Appointment ID");
        }
        appointments.put(appointment.getAppointmentId(), appointment);
    }

    /** Deletes an Appointment by ID. No exception if not found. */
    public void deleteAppointment(String appointmentId) {
        appointments.remove(appointmentId);
    }
}
