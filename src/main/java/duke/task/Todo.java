package duke.task;

import duke.io.Savable;

/**
 * To do Task format
 */
public class Todo extends Task {

    /**
     * Default Constructor with no task description.
     */
    public Todo() {
        this("");
    }

    /**
     * Constructor with to-do description
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    /**
     * Get display friendly string for to-do.
     * @return display friendly string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
