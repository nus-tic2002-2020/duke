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
 * Command to mark {@link Task} as done
 * @see duke.command.Command
 */
public class DoneCommand extends Command{

    /**
     * Constructor for instantiating new DoneCommand
     * @param args of raw user command for further processing in downstream classes
     */
    public DoneCommand(String[] args) {
        super(args);
    }

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.DONE;
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
     * Execute DoneCommand with args using task number in {@link TaskManager}
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

        List<Task> doneTasks = taskManager.done(super.args);

        List<Task> tasks = taskManager.getTasks();
        List<Savable> savables = new ArrayList<Savable>();
        for (Task t: tasks) {
            savables.add((Savable) t);
        }

        ui.echo("Power la! I've marked this task(s) as done:");
        outputs.add("Power la! I've marked this task(s) as done:");
        for (Task t: doneTasks) {
            ui.echo("   " + t.toString());
            outputs.add(t.toString());
        }

        try {
            storage.writeAll(savables);
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage(), DukeException.DukeError.WRITE_ERROR);
        }

        return true;
    }
}
