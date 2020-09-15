package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String formattedDateTime;
    protected LocalDateTime localDateTime;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd y HH:mm");

    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
        this.formattedDateTime = localDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}
