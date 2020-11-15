package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.List;

/**
 * Command to find {@link Task} using keyword and display to user
 * @see duke.command.Command
 */
public class FindCommand extends Command{

    /**
     * Constructor for instantiating new FindCommand
     * @param args of raw user command for further processing in downstream classes
     */
    public FindCommand(String[] args) {
        super(args);
    }

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.FIND;
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
     * Execute FindCommand for matching {@link Task} using args keyword
     * @param taskManager
     * @param ui
     * @param storage
     * @return boolean
     * @throws DukeException if find command missing keyword argument
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";
        assert storage != null : "Command storage cannot be null";
        assert taskManager != null : "Command task cannot be null";

        if (super.args == null || super.args.length < 2) { // at least 1 command argument, else error
            throw new DukeException("Must provide keyword for find command leh!",
                    DukeException.DukeError.MISSING_ARGUMENT);
        }

        String keyword = String.valueOf(super.args[1]);

        List<Task> tasks = taskManager.findTasks(keyword);

        ui.echo("OK, this is what I found:");
        outputs.add("OK, this is what I found:");
        int i = 0;
        for (Task t: tasks) {
            ui.echo(String.format("%d.%s", ++i, t.toString()));
            outputs.add(String.format("%d.%s", ++i, t.toString()));
        }

        return true;
    }
}
