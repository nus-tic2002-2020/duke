package duketask;

public class Todo extends Task {
    protected String[] schedule;
    protected boolean isDuration;

    /**
     * Constructor of <code>Todo</code> class, initialize task description of schedule.
     *
     * @param description the String received as description of the task
     *
     */
    public Todo(String description) {
        super(description);
        isDuration = false;
        if(description.contains("/at") || description.contains("/by") || description.contains("/takes")) {
            schedule = this.description[1].split("\\s", 2);
            schedule[1].trim();
            if(description.contains("/takes")) isDuration = true;
        }
    }

    /**
     * Return task <code>schedule</code> in the description.
     *
     * @return A String of the schedule in the description
     */
    public String getSchedule() {
        return schedule[1];
    }

    /**
     * Convert and return the Todo task as a String.
     *
     * @return A String of the task
     */
    @Override
    public String toString() {
        if(isDuration) return String.format("[T][%s] %s (takes: %s)", this.getStatusIcon(), this.getDescription(), this.getSchedule());
        else return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }
}