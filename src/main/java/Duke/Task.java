package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int priority;

    public Task(String description, boolean isDone, int priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getStatusIcon() {
        return (isDone ? "\u221a" : "X"); //return tick or X symbols
    }
    public void markAsDone() {
        isDone = true;
    }
    public String toString() {
        return "["+this.getStatusIcon()+"]"+"[Priority: "+this.getPriority()+"]"+this.description;
    }
    public String getDescription() {
        return this.description;
    }
    public int getPriority() {
        return this.priority;
    }
}