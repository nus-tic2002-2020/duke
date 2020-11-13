package tasklist;

//import java.sql.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDate;
    protected String outputDate;
    protected DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern(("MM dd yyyy HH:mm"));

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
        outputDate = byDate.format(outputDateFormat);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(inputDateFormat) + ")";

    }

    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + "|" + outputDate;
    }

}

