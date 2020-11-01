package task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Within extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Within(String description, String start, String end) {
        super(description);
        this.symbol = "[W]";
        try {
            this.start = LocalDateTime.parse(start, formatter());
            this.end = LocalDateTime.parse(end, formatter());
        } catch (DateTimeParseException e) {
            this.start = LocalDateTime.parse(start, formatterLoad());
            this.end = LocalDateTime.parse(end, formatterLoad());
        }
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
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

    public String getSymbol() {
        return symbol;
    }
}
