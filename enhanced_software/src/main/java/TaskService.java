import java.util.HashMap;
import java.util.Map;

/** Manages Task objects in memory. */
public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    /** Adds a Task if it is non-null and ID is unique. */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate Task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /** Deletes a Task by ID. No exception if not found. */
    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    /** Updates name for existing task; throws if not found or invalid. */
    public void updateName(String taskId, String newName) {
        Task t = tasks.get(taskId);
        if (t == null) {
            throw new IllegalArgumentException("Task not found");
        }
        t.setName(newName);
    }

    /** Updates description for existing task; throws if not found or invalid. */
    public void updateDescription(String taskId, String newDescription) {
        Task t = tasks.get(taskId);
        if (t == null) {
            throw new IllegalArgumentException("Task not found");
        }
        t.setDescription(newDescription);
    }
}
