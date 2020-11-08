package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDateTime byDateTime;
    protected String formattedDateTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    /**
     * Constructor of <code>Deadline</code> class, initialize Deadline task description and schedule.
     *
     * @param taskData the String received as description of the task
     */
    public Deadline(String taskData) {
        super(taskData);
        if (buffer[1].contains("by")) {
            byDateTime = LocalDateTime.parse(schedule, inputFormat);
            formattedDateTime = byDateTime.format(outputFormat);
        }
    }

    /**
     * Format Deadline <code>datetime</code> to the "dd MMM yyyy, hh:mma" format.
     */
    private void formatDateTime() {
        byDateTime = LocalDateTime.parse(schedule, inputFormat);
        formattedDateTime = byDateTime.format(outputFormat);
    }

    /**
     * Return Deadline task <code>datetime</code> in "dd MMM yyyy, hh:mma" format.
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
        return description + " /by " + schedule;
    }

    /**
     * Convert and return the Deadline task as a String.
     *
     * @return A String of the Deadline task
     */
    @Override
    public String toString() {
        if (isDuration) {
            return String.format("[D][%s] %s (takes: %s)", getStatusIcon(), getDescription(), getSchedule());
        }
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getDescription(), getFormattedDateTime());
    }
}
