package duke.task;

import duke.command.DukeException;
import duke.io.Savable;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Deadline Task format
 * Implements {@link DatedTask} to be used with {@link duke.command.ScheduleCommand}
 */
public class Deadline extends Task implements DatedTask {

    protected LocalDateTime by;

    /**
     * Default Constructor with no task description.
     */
    public Deadline() {
        this("");
    }

    /**
     * Constructor with task description
     * @param description
     */
    public Deadline(String description) {
        super(description);
        this.by = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        this.type = TaskType.DEADLINE;
    }

    /**
     * Constructor with task description
     * @param description
     * @param by in string format (e.g. yyyy-MM-dd HH:mm or yyyy-MM-dd hh:mm a)
     */
    public Deadline(String description, String by) {
        super(description);
        this.setBy(by);
        this.type = TaskType.DEADLINE;
    }

    /**
     * Getter for /by date time
     * @return by date time as LocalDateTime
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Setter for /by date time
     * @param by date time as LocalDateTime
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Setter for /by date time in string format. Automatically parsed to LocalDateTime.
     * @param by in string format (e.g. yyyy-MM-dd HH:mm or yyyy-MM-dd hh:mm a)
     */
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

    /**
     * Get display friendly string for {@link Savable} deadline.
     * @return display friendly string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }

    /**
     * Convert to savable string for {@link Savable} deadline.
     * @return savable string for disk storage
     */
    @Override
    public String toSavableString() {
        return String.format("%s|%s", super.toSavableString(), by.toEpochSecond(ZoneOffset.UTC));
    }

    /**
     * Convert from raw task data format into Deadline object
     * @param savableString as raw data format
     * @throws DukeException if raw format is wrong
     */
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

    /**
     * Getter to work with {@link Comparable} using /by date time.
     * @return by as LocalDateTime
     */
    @Override
    public LocalDateTime getComparableDateTime() {
        return this.by;
    }
}
