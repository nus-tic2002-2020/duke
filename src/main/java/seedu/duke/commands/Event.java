package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime at;

    /**
     * Create a Event with the task description, date and time.
     * @param description   The task description/command from user.
     * @param at            The task event date and time from user.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * To return the Event in string format.
     * @return String   The event in a string format.
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (at: " + dateToString(at) + ")";
    }

    public LocalDateTime getDate(){
        return this.at ;
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