package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

/**
 * Command to list {@link Task} in {@link TaskManager}
 * @see duke.command.Command
 */
public class ListCommand extends Command{

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.LIST;
    }

    /**
     * Getter for exit behavior to be used by calling application
     * @return exit status, always false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Execute ListCommand on {@link TaskManager}
     * @param taskManager
     * @param ui
     * @param storage
     * @return boolean
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) {

        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";
        assert taskManager != null : "Command taskManager cannot be null";

        List<String> tasks = taskManager.getPrintableTasks();

        ui.echo("Here are the tasks in your list:");
        outputs.add("Here are the tasks in your list:");
        for (String t: tasks) {
            ui.echo(" " + t);
            outputs.add(t);
        }

        return true;
    }
}
