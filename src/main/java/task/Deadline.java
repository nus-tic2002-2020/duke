package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Deadline-subclass of Task-superclass
 */
public class Deadline extends Task {
    private LocalDateTime dateAndTime;


    /**
     * Constructs Deadline-class object
     *
     * @param description description of deadline
     * @param dateAndTime date and time of deadline, two different initialization statements depending whether used
     *                    for adding tasks or loading from file
     * @throws DateTimeParseException error if date is wrong format
     */
    public Deadline(String description, String dateAndTime) throws DateTimeParseException {
        super(description);
        this.symbol = "[D]";
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
     * @return Returns LocalDateTime of deadline
     */
    @Override
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

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
