package duke.command;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCommand extends Command{

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADD;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

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
