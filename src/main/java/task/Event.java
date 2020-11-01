package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Event-subclass of Task-superclass
 */
public class Event extends Task {
    private LocalDateTime dateAndTime;

    /**
     * Constructs Event-class object
     *
     * @param description Description of event
     * @param dateAndTime Date and time of event, two different initialization statements depending whether used
     *                    for adding tasks or loading from file
     * @throws DateTimeParseException Error if date is wrong format
     */
    public Event(String description, String dateAndTime) throws DateTimeParseException {
        super(description);
        this.symbol = "[E]";
        try {
            this.dateAndTime = LocalDateTime.parse(dateAndTime, formatter());
        } catch (DateTimeParseException e) {
            this.dateAndTime = LocalDateTime.parse(dateAndTime, formatterLoad());
        }
    }

    /**
     * @return Returns symbol of task
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return Returns LocalDateTime of event
     */
    @Override
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Date formatter used when adding events
     */
    /**
     * Date formatter used when adding deadlines
     */
    private DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    }

    /**
     * Date formatter used when loading from file
     */
    private DateTimeFormatter formatterLoad() {
        return DateTimeFormatter.ofPattern("d/M/yyyy, H:mm", Locale.ENGLISH);
    }
}
