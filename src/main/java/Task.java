package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     *constructor of Task parent class, initializes as Task is not complete
     *
     * @param description of task class
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *check whether task has been completed
     *
     * @return Tick for completed task, cross for not-completed task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     *change status of task to be true
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     *return status of task
     */
    public Boolean getStatus(){
        return this.isDone;
    }

    /**
     *getter of Task parent description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     *Abstract class of parent Task class
     */
    public abstract char getD();
    public abstract String getTime();

    /**
     *override print statement to return status and description of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
