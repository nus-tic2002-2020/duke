package tasks;

/**
 * Parent class, it presents the tasks that need to be saved.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Different type of tasks.
     */
    public Task(String description,String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;

    }

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Different type of tasks.
     * @param isDone Task status.
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    /**
     * Return a symbol to show a task status.
     *
     * @return Return Y if task is done or X if not.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return symbols (✓ : ✘)
    }

    /**
     * Return description of task.
     *
     * @return Task description.
     */
    public String getDescription() {

        return description;
    }

    /**
     * Mark a task as completed.
     */
    public void markAsDone(){

        isDone = true;
    }

    /**
     * Return the type of a task.
     *
     * @return Type of task.
     */
    public String getTaskType(){
        return taskType;
    }

    /**
     * Return the information of the task.
     *
     * @return Information of the task.
     */
    public String getTaskListInfo() {
        return "[" + this.getTaskType() + "][" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Return the information of the task for storage in file.
     *
     * @return Information of the task.
     */
    public String formatForFile() {
        return this.getTaskType() + "|" + this.getStatusIcon() + "|" + this.getDescription();
    }

}
