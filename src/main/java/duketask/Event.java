package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Todo {
    private LocalDateTime atDateTime;
    private String formattedDateTime;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    /**
     * Constructor of <code>Event</code> class, initialize Event task description and schedule.
     *
     * @param taskData the String received as description of the task
     */
    public Event(String taskData) {
        super(taskData);

        assert !schedule.isEmpty() : "Schedule is not provided";

        if (taskData.contains("/at")) {
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
        if(!isDuration) {
            formatDateTime();
        }
    }

    /**
     * Change both description and schedule of the Event task.
     *
     * @param input a String of the new task information
     */
    @Override
    public void reset(String input) {
        buffer = input.split("\\/", 2);
        if (input.contains("/takes")) {
            isDuration = true;
        }
        description = buffer[0].trim();
        schedule = buffer[1].split("\\s", 2)[1].trim();
        if(!isDuration) {
            formatDateTime();
        }
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
