package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskManager;

import java.util.List;

public class DoneCommand extends Command{

    @Override
    public CommandType getType() {
        return CommandType.DONE;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) {
        List<String> tasks = taskManager.getPrintableTasks();

        ui.echo("Power la! I've marked this task(s) as done:");
        for (String t: tasks) {
            ui.echo(t);
        }

        return true;
    }
}
