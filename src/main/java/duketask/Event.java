package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Todo {
    protected LocalDateTime atDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    /**
     * Constructor of <code>Event</code> class, initialize Event task description and schedule.
     *
     * @param taskData the String received as description of the task
     */
    public Event(String taskData) {
        super(taskData);
        if (buffer[1].contains("at")) {
            atDateTime = LocalDateTime.parse(schedule, inputFormat);
            formattedDateTime = atDateTime.format(outputFormat);
        }
    }

    /**
     * Format Event <code>datetime</code> to the "dd MMM yyyy, hh:mma" format.
     */
    private void formatDateTime() {
        atDateTime = LocalDateTime.parse(schedule, inputFormat);
        formattedDateTime = atDateTime.format(outputFormat);
    }

    /**
     * Return Event task <code>datetime</code> in "dd MMM yyyy, hh:mma" format.
     *
     * @return formatted datetime as a String
     */
    private String getFormattedDateTime() {
        return formattedDateTime;
    }

    /**
     * Change the task <code>schedule</code>.
     *
     * @param schedule A String of the new schedule
     */
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
        formatDateTime();
    }

    /**
     * Change the task <code>schedule</code>.
     *
     * @param input a String of the new schedule
     */
    @Override
    public void reset(String input) {
        buffer = input.split("\\s", 2);
        if (buffer[1].equals("/takes")) {
            isDuration = true;
        }
        description = buffer[0];
        schedule = buffer[1].split("\\s", 2)[1].trim();
        formatDateTime();
    }

    /**
     * Copy the Event task <code>information</code>.
     *
     * @return the String of the Event task information
     */
    public String copy() {
        if(isDuration = true){
            return description + " /takes " + schedule;
        }
        return description + " /at " + schedule;
    }

    /**
     * Convert and return the Event task as a String.
     *
     * @return A String of the Event task
     */
    @Override
    public String toString() {
        if (isDuration) {
            return String.format("[E][%s] %s (takes: %s)", getStatusIcon(), getDescription(), getSchedule());
        }
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getDescription(), getFormattedDateTime());
    }
}
