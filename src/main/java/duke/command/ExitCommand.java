package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskManager;

import java.util.List;

public class ExitCommand extends Command{

    @Override
    public CommandType getType() {
        return CommandType.EXIT;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";

        outputs.add(ui.bye());
        return true;
    }
}

