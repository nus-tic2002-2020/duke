package task;

import java.time.LocalDateTime;

/**
 * Task-superclass. Has 3 subclass -> Todo, Deadline & Event
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    /**
     * Constructs Task-class object. Not allowed by itself, has to be implemented through subclass constructors
     *
     * @param description Description of task as a string
     */
    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    /**
     * @return Returns done-value of task as a string, either [✗] or [✓]
     */
    public String getDone() {
        if (!isDone) {
            return ("[✗]");
        }
            return ("[✓]");
    }

    /**
     * Sets the description parameter using a string input
     *
     * @param description Description of task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the description of task
     */
    public String getDescription() {
        return(description);
    }

    /**
     * Sets done parameter to true
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Empty methods utilized for subclass-overriding
     *
     * @return Empty return
     */
    public LocalDateTime getDateAndTime() {
        return null;
    };

    public LocalDateTime getStart() {
        return null;
    }

    public LocalDateTime getEnd() {
        return null;
    }

    /**
     * Abstract. Actual in subclasses.
     */
    public abstract String getSymbol();

}
