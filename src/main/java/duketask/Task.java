package duketask;

public class Task {
    protected String description[];
    protected boolean isDone;

    public Task(String description) {
        this.description = description.split("\\/", 2);
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description[0].trim();
    }
    /**
     * Represents a location in a 2D space. A <code>Point</code> object corresponds to
     * a coordinate represented by two integers e.g., <code>3,6</code>
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}