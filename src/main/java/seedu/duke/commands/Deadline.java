package seedu.duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + dateToString(by) + ")";
    }

    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYY HHmm");
        return dateTime.format(formatter);
    }
}