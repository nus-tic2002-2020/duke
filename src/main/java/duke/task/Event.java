package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String formattedDateTime;
    protected LocalDateTime localDateTime;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd y HH:mm");

    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
        this.formattedDateTime = localDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }
}
