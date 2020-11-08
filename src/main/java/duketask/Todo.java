package duketask;

public class Todo extends Task {
    protected String schedule;
    protected boolean isDuration;

    /**
     * Constructor of <code>Todo</code> class, initialize task description and schedule.
     *
     * @param taskData the String received as description of the task
     */
    public Todo(String taskData) {
        super(taskData);
        if (taskData.contains("/at") || taskData.contains("/by") || taskData.contains("/takes")) {
            schedule = buffer[1].split("\\s", 2)[1].trim();
            if (taskData.contains("/takes")) isDuration = true;
        }
    }

    /**
     * Return task <code>schedule</code> in the description.
     *
     * @return A String of the schedule in the description
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Change the task <code>schedule</code>.
     *
     * @param schedule A String of the new schedule
     */
    @Override
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Change the Todo task <code>schedule</code>.
     *
     * @param input A String of the new schedule
     */
    @Override
    public void reset(String input) {
        buffer = input.split("\\/", 2);
        description = buffer[0];
        if (!buffer[1].isEmpty()) {
            schedule = buffer[1].split("\\s", 2)[1].trim();
            isDuration = true;
        }
    }

    /**
     * Copy the task <code>information</code>.
     *
     * @return the String of the Todo task information
     */
    public String copy() {
        if (isDuration) {
            return description + " /takes " + schedule;
        }
        return description;
    }

    /**
     * Convert and return the Todo task as a String.
     *
     * @return A String of the task
     */
    @Override
    public String toString() {
        if (isDuration) {
            return String.format("[T][%s] %s (takes: %s)", getStatusIcon(), getDescription(), getSchedule());
        }
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }
}