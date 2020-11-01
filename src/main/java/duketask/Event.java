package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Todo{
    protected LocalDateTime atDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    /**
     * Constructor of <code>Event</code> class, initialize event task description and schedule.
     *
     * @param description the String received as description of the task
     *
     */
    public Event(String description) {
        super(description);
        if(schedule[0].contains("at")) {
            atDateTime = LocalDateTime.parse(schedule[1], inputFormat);
            formattedDateTime = atDateTime.format(outputFormat);
        }
    }

    /**
     * Set <code>datetime</code> of the event task.
     *
     * @param at the String of the new datetime
     *
     */
    public void setAt(String at) {
        schedule[1] = at;
    }

    /**
     * Return event task <code>datetime</code> in "dd MMM yyyy, hh:mma" format.
     *
     * @return formatted datetime as a String
     */
    public String getAtDateTime() {
        return formattedDateTime;
    }

    /**
     * Convert and return the event task as a String.
     *
     * @return A String of the event task
     */
    @Override
    public String toString() {
        if(isDuration) return String.format("[E][%s] %s (takes: %s)", this.getStatusIcon(), this.getDescription(), this.getSchedule());
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.getDescription(), formattedDateTime);
    }
}
