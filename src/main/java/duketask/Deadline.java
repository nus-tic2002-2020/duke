package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Todo {
    protected String by;
    protected LocalDateTime byDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    public Deadline(String description) {
        super(description);
        by = this.description[1].substring(2).trim();
        byDateTime = LocalDateTime.parse(by, inputFormat);
        formattedDateTime = byDateTime.format(outputFormat);
    }

    public void setBy(String by) {
        this.by = by;
        byDateTime = LocalDateTime.parse(by,inputFormat);
        formattedDateTime = byDateTime.format(outputFormat);
    }

    public String getBy() {
        return by;
    }

    public String getByDateTime() {
        return formattedDateTime;
    }

    /**
     * Represents a location in a 2D space. A <code>Point</code> object corresponds to
     * a coordinate represented by two integers e.g., <code>3,6</code>
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), formattedDateTime);
    }
}
