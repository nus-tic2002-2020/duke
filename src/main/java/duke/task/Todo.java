package duke.task;

public class Todo extends Task {

    public Todo() {
        this("");
    }

    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}