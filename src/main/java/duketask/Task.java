package duketask;

public class Task {
    protected String description[];
    protected boolean isDone;

    /**
     * Constructor of <code>Task</code> class, initialize task description and stats of done.
     *
     * @param description the String received as description of the task
     *
     */
    public Task(String description) {
        this.description = description.split("\\/", 2);
        this.isDone = false;
    }

    /**
     * Change task status to <code>done</code>.
     *
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
        return description[0].trim();
    }

    /**
     * Find <code>keyword</code> in the description.
     *
     * @return true if found, otherwise false
     */
    public boolean find(String key) {
        if(description[0].contains(key)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Convert and return the task as a String.
     *
     * @return A String of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}