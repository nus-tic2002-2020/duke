package tasks;

import tasks.Task;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task has detail date and time.
 */
public class Event extends Task {

    private LocalDate date;
    private LocalTime timing;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is event.
     * @param date Event date.
     * @param timing Event time.
     */
    public Event(String description, String taskType, LocalDate date, LocalTime timing){
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is event.
     * @param date Event date.
     * @param timing Event time.
     * @param isDone Whether task is completed or not.
     */
    public Event(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Return the date of the event in the correct format.
     *
     * @return Event date.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return the timing of the event in the correct format.
     *
     * @return Event time.
     */
    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Return the information of the task.
     *
     * @return Information of the task.
     */
    @Override
    public String getTaskListInfo(){
        return super.getTaskListInfo() + " at " + this.getDate() + " " + this.getTiming();
    }

    /**
     * Return the information of the task for storage in file.
     *
     * @return Information of the task.
     */
    public String formatForFile() {
        return super.formatForFile() + "|" + this.getDate() + "|" + this.getTiming();
    }

}
