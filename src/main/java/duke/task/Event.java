package duke.task;

import duke.command.DukeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    protected LocalDateTime at;

    public Event() {
        this("");
    }

    public Event(String description) {
        super(description);
        this.at  = LocalDateTime.now();
        this.type = TaskType.EVENT;
    }

    public Event(String description, String at) {
        super(description);
        this.setAt(at);
        this.type = TaskType.EVENT;
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public void setAt(String at) {

        DateTimeFormatter formatter;

        if (at.trim().endsWith("PM") || at.trim().endsWith("AM")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }

        try {
            this.at = LocalDateTime.parse(at, formatter);
        } catch (DateTimeParseException ex) {
            this.at = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%s", super.toSavableString(), at.toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public void fromSavableString(String savableString) throws DukeException {
        super.fromSavableString(savableString);

        String[] arrString = savableString.split("\\" + separator);
        if (arrString.length < 4) {
            throw new DukeException("Rabak Sial, wrong data format for Event!", DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        try {

            long epoch = Long.parseLong(arrString[3].trim());
            this.at = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC);

        } catch (NumberFormatException | DateTimeException ex) {
            throw new DukeException("Rabak Sial, wrong data format for Event! " + ex.getMessage(),
                    DukeException.DukeError.WRONG_DATA_FORMAT);
        }
    }
}