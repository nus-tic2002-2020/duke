package duke.task;

import duke.command.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline() {
        this("");
    }

    public Deadline(String description) {
        super(description);
        this.by = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        this.type = TaskType.DEADLINE;
    }

    public Deadline(String description, String by) {
        super(description);
        this.setBy(by);
        this.type = TaskType.DEADLINE;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public void setBy(String by) {

        DateTimeFormatter formatter;

        if (by.trim().toUpperCase().endsWith("PM") || by.trim().toUpperCase().endsWith("AM")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }

        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException ex) {
            this.by = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%s", super.toSavableString(), by.toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public void fromSavableString(String savableString) throws DukeException {
        super.fromSavableString(savableString);

        String[] arrString = savableString.split("\\" + separator);
        if (arrString.length < 4) {
            throw new DukeException("Rabak Sial, wrong data format for Deadline!", DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        try {

            long epoch = Long.parseLong(arrString[3].trim());
            this.by = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC);

        } catch (NumberFormatException | DateTimeException ex) {
            throw new DukeException("Rabak Sial, wrong data format for Deadline! " + ex.getMessage(),
                    DukeException.DukeError.WRONG_DATA_FORMAT);
        }
    }
}