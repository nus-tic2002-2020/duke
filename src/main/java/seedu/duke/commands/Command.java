package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class Command {
    boolean isExit = false;
    protected String description;

    /**
     * Creates Command and initialise the isExit boolean value and description according to user input.
     * @param   isExit              The boolean value if the exit condition is true.
     * @param   description         The task description/command from user.
     */
    public Command(boolean isExit, String description) {
        this.isExit = isExit;
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command according to user input and return the result.
     * @param   taskList            The array of tasks stored in as an ArrayList.
     * @param   ui                  The User Interface (UI) to handle the interaction with user.
     * @param   storage             The storage to handle storing and reading of task from the file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        throw new DukeException("No such function yet.");
    }
}