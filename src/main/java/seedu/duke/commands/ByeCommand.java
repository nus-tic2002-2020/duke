package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * To create ByeCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public ByeCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * To print the bye message through UI.
     * @param   taskList                            The array of tasks stored in an ArrayList.
     * @param   ui                                  The User Interface (UI) to handle interaction with user.
     * @param   storage                             The storage to handle storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showGoodbyeMessage();
    }
}
