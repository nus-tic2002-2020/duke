package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskManager;

import java.util.List;

public class ListCommand extends Command{

    @Override
    public CommandType getType() {
        return CommandType.LIST;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) {
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
