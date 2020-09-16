package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This program is a child of the Task program. It helps add an additional classification to the Task program.
 * <br>This form of program includes a task that includes both a description and its associated completion date.
 */

public class Event extends Task{
    protected String formattedDateTime;
    protected LocalDateTime localDateTime;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd y HH:mm");

    /**
     * This method initialized an event task that takes in 2 parameters and creates the task.
     * @param description Description of task
     * @param localDateTime Scheduled Date / Time of task completion
     */

    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
        this.formattedDateTime = localDateTime.format(formatter);
    }

    /**
     * This method provides a String format return for the event task.
     * @return Returns a String in the unique format specified in the method.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }
}
