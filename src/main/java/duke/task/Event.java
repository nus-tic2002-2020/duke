package duke.task;

import duke.command.Duke;
import duke.command.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Event extends Task implements DatedTask {

    protected LocalDateTime at;
    protected LocalDateTime end;

    public Event() {
        this("");
    }

    public Event(String description) {
        super(description);
        this.at  = LocalDateTime.now();
        this.end = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
        this.type = TaskType.EVENT;
    }

    public LocalDateTime getAt() {
        return at;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setDuration(String at, String end) throws DukeException {

        // Process start date time

        DateTimeFormatter formatterAt;

        if (at.trim().endsWith("PM") || at.trim().endsWith("AM")) {
            formatterAt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        } else {
            formatterAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }

        try {
            this.at = LocalDateTime.parse(at, formatterAt);
        } catch (DateTimeParseException ex) {
            this.at = LocalDateTime.now();
        }

        // Process end date time

        if (end == null || end.isBlank()) {
            this.end = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
            return;
        }

        DateTimeFormatter formatterEnd;

        if (end.trim().endsWith("PM") || end.trim().endsWith("AM")) {
            formatterEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        } else {
            formatterEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }

        try {
            this.end = LocalDateTime.parse(end, formatterEnd);
        } catch (DateTimeParseException ex) {
            this.end = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
        }

        // Check if start time is after end time

        if (this.at.isAfter(this.end)) {
            throw new DukeException("Event cannot end before it starts leh!",
                    DukeException.DukeError.ANOMALY_ARGUMENT);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[E]");
        builder.append(super.toString());
        builder.append(" (at: ");
        builder.append(at.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")));

        // Handle null and 0 date-time
        if (this.end != null) {
            long epochEnd = this.end.toEpochSecond(ZoneOffset.UTC);
            if (epochEnd > 0) {
                builder.append(", end: ");
                builder.append(end.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")));
            }
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%s|%s", super.toSavableString(),
                at.toEpochSecond(ZoneOffset.UTC), end.toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public void fromSavableString(String savableString) throws DukeException {
        super.fromSavableString(savableString);

        String[] arrString = savableString.split("\\" + separator);
        if (arrString.length < 5) {
            throw new DukeException("Rabak Sial, wrong data format for Event!", DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        try {

            long epochAt = Long.parseLong(arrString[3].trim());
            this.at = LocalDateTime.ofEpochSecond(epochAt, 0, ZoneOffset.UTC);

            long epochEnd = Long.parseLong(arrString[4].trim());
            this.end = LocalDateTime.ofEpochSecond(epochEnd, 0, ZoneOffset.UTC);

        } catch (NumberFormatException | DateTimeException ex) {
            throw new DukeException("Rabak Sial, wrong data format for Event! " + ex.getMessage(),
                    DukeException.DukeError.WRONG_DATA_FORMAT);
        }
    }

    @Override
    public LocalDateTime getComparableDateTime() {
        return this.at;
    }
}