package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import enumerations.SymbolEnum;

/**
 * This is the Within class. It is a subclass of Task.
 */
public class Within extends Task {

    /**
     * These are the start and end date/time variables. They are of LocalDateTime format.
     */
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * This method creates the Event-class. It calls the superclass constructor, as well as sets the symbol to "W"
     * and sets the date and time. It has two cases of usage (thus two formatter methods), normal creation and when
     * loading from the text file.
     *
     * @param description This is the description of the task as input by user in string format.
     * @param start This is the start date and time of the task as input by user in string format.
     * @param end This is the end date and time of the task as input by user in string format.
     * @throws DateTimeParseException This exception is thrown when the format of the date and time as input by user
     *                                is invalid.
     */
    public Within(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.symbol = SymbolEnum.valueOf("W");
        try {
            this.start = LocalDateTime.parse(start, formatter());
            this.end = LocalDateTime.parse(end, formatter());
        } catch (DateTimeParseException e) {
            this.start = LocalDateTime.parse(start, formatterLoad());
            this.end = LocalDateTime.parse(end, formatterLoad());
        }
    }

    /**
     * This method returns the symbol of the task.
     *
     * @return Returns symbol of task.
     */
    public SymbolEnum getSymbol() {
        return symbol;
    }

    /**
     * This method returns the start date and time of the task.
     *
     * @return Returns LocalDateTime of Within task.
     */
    @Override
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * This method returns the end date and time of the task.
     *
     * @return Returns LocalDateTime of Within task.
     */
    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * This formatter method is used during normal creation of within task.
     */
    private DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    }

    /**
     * This formatter method is used during loading of within task from the text file.
     */
    private DateTimeFormatter formatterLoad() {
        return DateTimeFormatter.ofPattern("d/M/yyyy, H:mm", Locale.ENGLISH);
    }
}
