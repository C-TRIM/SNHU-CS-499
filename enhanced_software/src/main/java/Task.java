/**
 * Represents a Task with immutable ID, updatable name and description.
 * Constraints: ID ≤10 chars (non-null), name ≤20 chars (non-null), description ≤50 chars (non-null).
 */
public class Task {
    private final String taskId;
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Invalid taskId");
        }
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() { return taskId; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    /** Updates name with non-null and length constraint */
    public void setName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    /** Updates description with non-null and length constraint */
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.description = description;
    }
}
