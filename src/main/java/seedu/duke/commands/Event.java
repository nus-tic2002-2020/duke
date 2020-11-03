package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (at: " + dateToString(at) + ")";
    }

    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYY HHmm");
        return dateTime.format(formatter);
    }
}