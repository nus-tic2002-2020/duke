package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

/**
 * Command to exit {@link Duke}
 * @see duke.command.Command
 */
public class ExitCommand extends Command{

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.EXIT;
    }

    /**
     * Getter for exit behavior to be used by calling application
     * @return exit status, always true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Execute ExitCommand to exit application
     * @param taskManager
     * @param ui
     * @param storage
     * @return boolean
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) {
        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";

        outputs.add(ui.bye());
        return true;
    }
}

