package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDateTime byDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    /**
     * Constructor of <code>Deadline</code> class, initialize deadline task description and schedule.
     *
     * @param description the String received as description of the task
     *
     */
    public Deadline(String description) {
        super(description);
        if(schedule[0].contains("by")) {
            byDateTime = LocalDateTime.parse(schedule[1], inputFormat);
            formattedDateTime = byDateTime.format(outputFormat);
        }
    }

    /**
     * Set <code>datetime</code> of the deadline task.
     *
     * @param by the String of the new datetime
     *
     */
    public void setBy(String by) {
        schedule[1] = by;
        byDateTime = LocalDateTime.parse(by,inputFormat);
        formattedDateTime = byDateTime.format(outputFormat);
    }

    /**
     * Return deadline task <code>datetime</code> in "dd MMM yyyy, hh:mma" format.
     *
     * @return formatted datetime as a String
     */
    public String getByDateTime() {
        return formattedDateTime;
    }

    /**
     * Convert and return the deadline task as a String.
     *
     * @return A String of the deadline task
     */
    @Override
    public String toString() {
        if(isDuration) return String.format("[D][%s] %s (takes: %s)", this.getStatusIcon(), this.getDescription(), this.getSchedule());
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), formattedDateTime);
    }
}
