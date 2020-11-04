package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class Command {
    boolean isExit = false;
    protected String description;

    public Command(boolean isExit, String description) {
        this.isExit = isExit;
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Storage.StorageOperationException {
        throw new DukeException("No such function yet.");
    }
}