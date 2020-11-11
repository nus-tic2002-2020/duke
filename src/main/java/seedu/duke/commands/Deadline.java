package seedu.duke.commands;

import seedu.duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime by;

    /**
     * Creates Deadline with the task description, date and time according to user input.
     * @param description       The task description/command from user.
     * @param by                The task deadline date and time from user.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the Deadline as a string.
     * @return String       The deadline in a string type.
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + dateToString(by) + ")";
    }

    /**
     * Returns the date and time as a LocalDateTime format.
     * @return String       The date and time in a LocalDateTime format.
     */
    public LocalDateTime getDate(){
        return this.by ;
    }

    /**
     * Converts the LocalDateTime object to a string object with the format (d/MM/yyyy HHmm).
     * @param   dateTime            The date and time as a LocalDateTime object.
     * @return  String              The date and time as a string object.
     */
    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}