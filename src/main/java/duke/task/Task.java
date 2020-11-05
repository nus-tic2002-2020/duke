package duke.task;

import duke.command.Duke;
import duke.command.DukeException;
import duke.io.Savable;

public class Task implements Savable {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected String separator = "|";

    public Task() {
        this("");
    }

    public Task(String description) {
        this.isDone = false;
        this.type = TaskType.DEFAULT;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public String getSeparator() {
        return this.separator;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public String toSavableString() {
        return String.format("%s|%d|%s", this.type.getCode(), isDone ? 1 : 0, this.description);
    }

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