package tasklist;

/**
 * Task inherit by To do, Event and Deadline task
 */
public class Task {
    public boolean isDone;
    public String Type = "Task";
    protected String description;
    protected int taskIndex;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(int i) {
        taskIndex = i;
    }

    public void editDone(boolean isItDone) {
        isDone = isItDone;
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

    public boolean find(String findDescription) {
        if (description.contains(findDescription)) {
            return true;
        } else {
            return false;
        }
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

