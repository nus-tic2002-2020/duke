package duke.command;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends Command{

    public DeleteCommand(String[] args) {
        super(args);
    }

    @Override
    public CommandType getType() {
        return CommandType.DELETE;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
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

        int i = 1;
        for (Task t: removedTasks) {
            ui.echo(String.format("   %s", t));
        }

        ui.echo(String.format("Now you have %d tasks in the list.", tasks.size()));

        return true;
    }
}
