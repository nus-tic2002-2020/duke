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
 * Command to add new {@link Task}
 * @see duke.command.Command
 */
public class AddCommand extends Command{

    protected Task task;

    /**
     * Constructor for instantiating new AddCommand
     * @param task to be added
     * @param args of raw user command for further processing in downstream classes
     */
    public AddCommand(Task task, String[] args) {
        super(args);
        this.task = task;
    }

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.ADD;
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
     * Execute AddCommand with {@link Task}
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
        assert task != null : "Command task cannot be null";

        ui.echo("Got it. I've added this task:");
        outputs.add("Got it. I've added this task:");

        taskManager.add(task);

        //System.out.printf("      %s\n", t);
        ui.echo("  " + task.toString());
        outputs.add(task.toString());

        List<Task> tasks = taskManager.getTasks();
        ui.echo(String.format("Now you have %d tasks in the list.", tasks.size()));
        outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));

        // Convert to savable list
        Savable s = (Savable) task;

        // Write all to disk
        try {
            storage.appendln(s);
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage(), DukeException.DukeError.WRITE_ERROR);
        }

        return true;
    }
}
