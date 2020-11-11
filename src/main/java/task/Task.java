package task;

import java.time.LocalDateTime;

import enumerations.PriorityEnum;
import enumerations.SymbolEnum;

/**
 * This is the Task class. It is a superclass.
 */
public abstract class Task {

    /**
     * These are the variables of a Task class. Priority of the task is an enumeration (HIGH, LOW, MEDIUM, NA),
     * description is a string, status of the task (isDone) is a boolean, and the symbol of the task is a enum.
     * (T, D, E, W).
     */
    protected PriorityEnum priority;
    protected String description;
    public boolean isDone;
    protected SymbolEnum symbol;

    /**
     * This creates the Task-class object. This constructor is never called by itself, it is only called through it's
     * subclass-constructors. Description is set according to user input, status is defaulted to false and priority-
     * level is defaulted to "NA".
     *
     * @param description This is the description of task as a string.
     */
    public Task(String description) {
        setDescription(description);
        this.isDone = false;
        priority = PriorityEnum.valueOf("NA");
    }

    /**
     * This method edits the priority-level of the task.
     *
     * @param priority This is the new priority-level in string format. It is changed to PriorityEnum.
     */
    public void setPriority(String priority) {
        this.priority = PriorityEnum.valueOf(priority.toUpperCase());
    }

    /**
     * This method returns the priority-level of the task.
     *
     * @return Returns the priority-level of the task.
     */
    public PriorityEnum getPriority() {
        return this.priority;
    }

    /**
     * This method returns the status of the task.
     *
     * @return Returns the status of task as a string, either [✗] or [✓].
     */
    public String getDone() {
        if (!isDone) {
            return ("Completed: NO");
        }
            return ("Completed: YES");
    }

    /**
     * This method sets the description parameter using a string input by the user.
     *
     * @param description This is the description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the description of the task.
     *
     * @return Returns the description of task as a string.
     */
    public String getDescription() {
        return(description);
    }

    /**
     * This method sets the status of the task to "true".
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * These are empty methods utilized for subclass-overriding.
     *
     * @return Empty returns.
     */
    public LocalDateTime getDateAndTime() {
        return null;
    }
    public LocalDateTime getStart() {
        return null;
    }
    public LocalDateTime getEnd() {
        return null;
    }

    /**
     * This is an abstract method. Actual method in subclasses.
     */
    public abstract SymbolEnum getSymbol();

}
