import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithinPeriod extends Task{
    private LocalDate fromDate;
    private LocalDate toDate;

    public DoWithinPeriod(String description, String taskType, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, taskType, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getToDate() {
        return toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTaskListInfo() {
        return super.getTaskListInfo() + " between " + this.getFromDate() + " and " + this.getToDate();
    }

    public String formatForFile() {
        return super.formatForFile() + "|" + this.getFromDate() + "|" + this.getToDate();
    }

}
