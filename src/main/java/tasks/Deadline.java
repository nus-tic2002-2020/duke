package tasks;

import tasks.Task;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task has detail date and time.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime timing;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is Deadline.
     * @param date Deadline date.
     * @param timing Deadline time.
     */
    public Deadline(String description, String taskType, LocalDate date, LocalTime timing) {
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is Deadline.
     * @param date Deadline date.
     * @param timing Deadline time.
     * @param isDone Whether task is completed or not.
     */
    public Deadline(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Return the date of the deadline in the correct format.
     *
     * @return Deadline date.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return the timing of the deadline in the correct format.
     *
     * @return Deadline time.
     */
    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Set a new date to extend the previous date of deadline task.
     *
     * @param newDate New set date for postponed task.
     */
    public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

    /**
     * Return the information of the task.
     *
     * @return Information of the task.
     */
    @Override
    public String getTaskListInfo() {
        return super.getTaskListInfo() + " by " + this.getDate() + " " + this.getTiming();
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