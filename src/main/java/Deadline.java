public class Deadline extends Task {
    private String by;

    public Deadline(String description, String taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    public Deadline(String description, String taskType, String by, boolean isDone) {
        super(description, taskType, isDone);
        this.by = by;
    }

    @Override
    public String getTaskListInfo() {
        return "[D]" + super.getTaskListInfo() + " (by: " + by + ")";
    }

    public String formatForFile() {
        return "D" + super.formatForFile() + super.getDescription();
    }
}