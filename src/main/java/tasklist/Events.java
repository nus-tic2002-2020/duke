package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *  user key in event and description.
 *  adds to list of task and prints out
 *  the event description, date and time.
 */

public class Events extends Task {

    protected LocalDateTime byDateTime;
    protected String outputDate;
    protected DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern(("MM dd yyyy HH:mm"));

    public Events(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
        outputDate = byDateTime.format(outputDateFormat);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + byDateTime.format(inputDateFormat) + ")";
    }

    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + "|" + outputDate;
    }

}
