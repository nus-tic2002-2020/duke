package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ByeCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            ui.showGoodbyeMessage();
    }
}
