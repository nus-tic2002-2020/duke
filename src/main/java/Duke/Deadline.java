package Duke;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * Represents a deadline task.
 * @param description description of the task
 * @param by when the task is due by
 * @param isDone whether the task is done
 * @param priority priority of the deadline task
 */

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, Boolean isDone, int priority) {
        super(description,isDone,priority);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public LocalDateTime getBy() {
        return by;
    }
}