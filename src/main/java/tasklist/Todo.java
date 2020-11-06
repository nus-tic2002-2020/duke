package tasklist;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }
}
