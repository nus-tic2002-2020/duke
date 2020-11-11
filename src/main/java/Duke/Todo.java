package Duke;


/**
 * Represents a todo task.
 * @param description description of the task
 * @param isDone whether the task is done
 * @param priority priority of the deadline task
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone, int priority) {
        super(description, isDone, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}