package duke.task;

/**
 * This Task program defines the basic structure for a Task class. It contains the following key components:
 *  1. Description
 *  2. isDone Status (Whether task is completed / not completed)
 * It also contains the basic constructor, a getter and a setter method.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * This method is used to initialize the Task Class using a String description as a variable.
     * @param description Registers the description of the Task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to retrieve the status of a task (Completed / Not completed).
     * @return Returns either a tick or X symbol representing the task completion status.
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method is used to set the status of a task (Completed / Not completed).
     * @param isDone Set the status of a task.
     */

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * This method is used to return the String output of the Task is a specified manner.
     * @return Returns the String that includes the getStatus and description of the task.
     */

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
