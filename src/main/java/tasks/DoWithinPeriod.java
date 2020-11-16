package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Do within the period task has a period of date.
 */
public class DoWithinPeriod extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is doWithinPeriod.
     * @param fromDate 'From date' of doWithinPeriod task.
     * @param toDate 'To date' of doWithinPeriod task.
     * @param isDone Whether task is completed or not.
     */
    public DoWithinPeriod(String description, String taskType, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, taskType, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Return the from date of the doWithinPeriod in the correct format.
     *
     * @return From date of doWithinPeriod task.
     */
    public String getFromDate() {
        return fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return the to date of the doWithinPeriod in the correct format.
     *
     * @return To date of doWithinPeriod task.
     */
    public String getToDate() {
        return toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return the information of the task.
     *
     * @return Information of the task.
     */
    public String getTaskListInfo() {
        return super.getTaskListInfo() + " between " + this.getFromDate() + " and " + this.getToDate();
    }

    /**
     * Return the information of the task for storage in file.
     *
     * @return Information of the task.
     */
    public String formatForFile() {
        return super.formatForFile() + "|" + this.getFromDate() + "|" + this.getToDate();
    }

}
