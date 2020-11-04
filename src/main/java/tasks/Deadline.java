package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * This represents a Deadline Task. It has a LocalDateTime by object.
 * The "by" object will represent the time which the Deadline is supposed to finish.
 */

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        try{
            this.by = LocalDateTime.parse(by);
        }catch(DateTimeParseException e){
            this.by = LocalDateTime.now();
            System.out.println("Invalid Date Time set for /by. It will be set to the current time");
        }

    }

    public String getByDeadline(){
        return this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy H:m:s"));
    }


    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.getByDeadline() + ")";
    }
}
