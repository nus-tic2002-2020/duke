package duke.task;

import duke.command.Duke;
import duke.command.DukeException;
import duke.io.Savable;

/**
 * Task class used by Duke application.
 * Implements {@link Savable} to be used with {@link duke.io.Storage}
 */
public class Task implements Savable {

    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected String separator = "|";

    /**
     * Default Constructor with no task description.
     */
    public Task() {
        this("");
    }

    /**
     * Constructor with task description
     * @param description
     */
    public Task(String description) {
        this.isDone = false;
        this.type = TaskType.DEFAULT;
        this.description = description;
    }

    /**
     * Getter for TaskType enum
     * @return TaskType enum
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Setter for task description
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for Status icon in UTF8
     * @return status icon string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set separator for {@link Savable} task
     * @param separator character(s) for splitting disk data into fields
     */
    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Get separator for {@link Savable} task
     * @return separator
     */
    @Override
    public String getSeparator() {
        return this.separator;
    }

    /**
     * Get display friendly string for {@link Savable} task.
     * @return display friendly string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Convert to savable string for {@link Savable} task.
     * @return savable string for disk storage
     */
    @Override
    public String toSavableString() {
        return String.format("%s|%d|%s", this.type.getCode(), isDone ? 1 : 0, this.description);
    }

    /**
     * Convert from raw task data format into Task object
     * @param savableString as raw data format
     * @throws DukeException if raw format is wrong
     */
    @Override
    public void fromSavableString(String savableString) throws DukeException {
        String[] arrString = savableString.split("\\" + separator);
        if (arrString.length < 3) {
            throw new DukeException(String.format("Rabak Sial, wrong data format! Length is %d.", arrString.length),
                    DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        try {
            int done = Integer.parseInt(arrString[1]);
            this.isDone = (done > 0);
        } catch (NumberFormatException ex) {
            throw new DukeException(String.format("Rabak Sial, wrong data format! %s", ex.getMessage()),
                    DukeException.DukeError.WRONG_DATA_FORMAT);
        }

        this.description = arrString[2].strip();

    }
}