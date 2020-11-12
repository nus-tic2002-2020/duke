package seedu.duke.base;

public class Todo extends Task {

    /**
     * Creates new Todo with the task description from user.
     *
     * @param description The task description/command from user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo as a string type.
     *
     * @return String       The Todo in a string type.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}