package seedu.duke.commands;

import java.time.LocalDateTime;

public class Task {
    public String description;
    public boolean isDone;

    /**
     * Create new Task with the task description from user and initialised the task progression status as not complete.
     * @param description   The task description/command from user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To return the task progression status as an icon.
     * @return String   The task progression status as a tick icon as done or a cross icon as not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * To return the task in string format.
     * @return String   The task progression status and task description in a string format.
     */
    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public void setDone() {
        isDone = true;
    }

    public LocalDateTime getDate(){
        return null;
    }
}