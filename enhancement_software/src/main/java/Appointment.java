import java.util.Date;

/**
 * Represents an Appointment with:
 * - immutable appointmentId (max 10 chars, non-null),
 * - appointmentDate that must not be in the past,
 * - updatable description (max 50 chars, non-null).
 */
public class Appointment {
    private final String appointmentId;
    private final Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Invalid appointmentId");
        }
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("appointmentDate must be in the future");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() { return appointmentId; }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() { return description; }

    /** Updates the description; enforces non-null and max length constraint. */
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.description = description;
    }
}
