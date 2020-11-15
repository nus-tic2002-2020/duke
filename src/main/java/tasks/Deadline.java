package tasks;

import tasks.Task;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime timing;

    public Deadline(String description, String taskType, LocalDate date, LocalTime timing) {
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    public Deadline(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
        this.date = date;
        this.timing = timing;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String getTaskListInfo() {
        return super.getTaskListInfo() + " by " + this.getDate() + " " + this.getTiming();
    }

    public String formatForFile() {
        return super.formatForFile() + "|" + this.getDate() + "|" + this.getTiming();
    }

}