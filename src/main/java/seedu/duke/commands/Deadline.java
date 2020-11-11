package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime by;

    /**
     * Create a Deadline with the task description, date and time.
     * @param description   The task description/command from user.
     * @param by            The task deadline date and time from user.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * To return the Deadline in string format.
     * @return String   The deadline in a string format.
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + dateToString(by) + ")";
    }

    public LocalDateTime getDate(){
        return this.by ;
    }

    /**
     * To convert the LocalDateTime object of Deadline to a string object with the format (d/MM/yyyy HHmm).
     * @param dateTime  The date and time of a deadline as a LocalDateTime object.
     * @return String   The date and time of a deadline as a string object.
     */
    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}