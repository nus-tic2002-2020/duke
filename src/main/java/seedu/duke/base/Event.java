package seedu.duke.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime at;

    /**
     * Creates Event with the task description, date and time according to user input.
     * @param description       The task description/command from user.
     * @param at                The task deadline date and time from user.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the Event as a string.
     * @return String       The event in a string type.
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (at: " + dateToString(at) + ")";
    }

    /**
     * Returns the date and time as a LocalDateTime format.
     * @return String       The date and time in a LocalDateTime format.
     */
    public LocalDateTime getDate(){
        return this.at ;
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