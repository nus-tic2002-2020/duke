package tasklist;

import java.text.ParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    public String Type = "Task";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }

    public String saveFormat() {
        if (this.isDone == Boolean.parseBoolean("true")) {
            return ("|1|" + this.description);
        } else {
            return ("|0|" + this.description);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}

