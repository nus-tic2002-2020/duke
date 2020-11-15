package duke.command;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to delete {@link Task} from {@link TaskManager}
 * @see duke.command.Command
 */
public class DeleteCommand extends Command{

    /**
     * Constructor for instantiating new DeleteCommand
     * @param args of raw user command for further processing in downstream classes
     */
    public DeleteCommand(String[] args) {
        super(args);
    }

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.DELETE;
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
     * Execute DeleteCommand with using args task number in {@link TaskManager}
     * @param taskManager
     * @param ui
     * @param storage
     * @return boolean
     * @throws DukeException if error writing to disk
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";
        assert storage != null : "Command storage cannot be null";
        assert taskManager != null : "Command task cannot be null";

        List<Task> removedTasks = taskManager.delete(super.args);

        List<Task> tasks = taskManager.getTasks();

        // Convert to savable list
        List<Savable> savables = new ArrayList<Savable>();
        for (Task t: tasks) {
            savables.add((Savable) t);
        }

        // Write all to disk
        try {
            storage.writeAll(savables);
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage(), DukeException.DukeError.WRITE_ERROR);
        }

        ui.echo("Song la! Lim peh help you remove this task(s):");
        outputs.add("Song la! Lim peh help you remove this task(s):");

        int i = 1;
        for (Task t: removedTasks) {
            ui.echo(String.format("   %s", t));
            outputs.add(String.format("   %s", t));
        }

        ui.echo(String.format("Now you have %d tasks in the list.", tasks.size()));
        outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));

        return true;
    }
}
