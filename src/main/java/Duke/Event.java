package Duke;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * Represents a event task.
 * @param description description of the task
 * @param at when the task is scheduled at
 * @param isDone whether the task is done
 * @param priority priority of the deadline task
 */

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, boolean isDone, int priority) {
        super(description,isDone,priority);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
    public LocalDateTime getAt() {
        return at;
    }
}