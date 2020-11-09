package seedu.duke.commands;

public class Todo extends Task {

    /**
     * To create a Todo with the task description from user.
     * @param description  The task description/command from user.
     * @return
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * To return the Todo as a string.
     * @return String   The Todo is returned as a string.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}