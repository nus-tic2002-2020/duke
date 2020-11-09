package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class Command {
    boolean isExit = false;
    protected String description;

    /**
     * Create Command and initialise the specified isExit boolean value and description by the user.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public Command(boolean isExit, String description) {
        this.isExit = isExit;
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * To execute the command from user and return the result.
     * @param   taskList    An arraylist to store the array of tasks.
     * @param   ui          A User Interface (UI) to deal with the interaction with user.
     * @param   storage     The storage to allow storing and reading of tasks from a file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        throw new DukeException("No such function yet.");
    }
}