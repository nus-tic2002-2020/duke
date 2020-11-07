package duke.command;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoneCommand extends Command{

    public DoneCommand(String[] args) {
        super(args);
    }

    @Override
    public CommandType getType() {
        return CommandType.DONE;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
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
