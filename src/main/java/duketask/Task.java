package duketask;

public class Task {
    protected String[] buffer;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of <code>Task</code> class, initialize task description and stats of done.
     *
     * @param taskData the String received as description of the task
     */
    public Task(String taskData) {
        buffer = taskData.split("\\/", 2);
        description = this.buffer[0].trim();
        isDone = false;
    }


    /**
     * Change task status to <code>done</code>.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Return task <code>done</code> status as tick or cross.
     *
     * @return tick or cross based on the value of isDone
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Return task <code>description</code>.
     *
     * @return A String of the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change the task <code>description</code>.
     *
     * @param description the new description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Find <code>keyword</code> in the description.
     *
     * @return true if found, otherwise false
     */
    public boolean find(String key) {
        if (description.contains(key)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change the task <code>schedule</code>.
     *
     * @param schedule A String of the new schedule
     */
    public void setSchedule(String schedule) {

    }

    /**
     * Change the task <code>schedule</code>.
     *
     * @param input A String of the new schedule
     */
    public void reset(String input) {

    }

    /**
     * Copy the task <code>information</code>.
     */
    public String copy() {
        return null;
    }

    /**
     * Convert and return the task as a String.
     *
     * @return A String of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}