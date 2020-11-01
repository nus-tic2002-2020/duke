package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Todo{

    protected String at;
    protected LocalDateTime atDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    public Event(String description) {
        super(description);
        at = this.description[1].substring(2).trim();
        atDateTime = LocalDateTime.parse(at, inputFormat);
        formattedDateTime = atDateTime.format(outputFormat);
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public String getAtDateTime() {
        return formattedDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.getDescription(), formattedDateTime);
    }
}
