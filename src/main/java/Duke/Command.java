package Duke;

/**
 * Represents a command.
 */
public abstract class Command {
    public boolean isExit;

    public Command() {
        isExit = false;
    }
    public boolean isExit() {
        return isExit;
    }
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}