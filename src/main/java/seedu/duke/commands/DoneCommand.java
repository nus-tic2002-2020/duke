package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String DONE_MSG = "Nice! I have marked this task as done: \n\t";

    /**
     * To create DoneCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public DoneCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * To mark the task as done, check the task number if empty prior marking it as done.
     * @param   taskList                            The array of tasks stored in an ArrayList.
     * @param   ui                                  The User Interface (UI) to handle interaction with user.
     * @param   storage                             The storage to handle storing and reading of tasks from a file.
     * @throws  DukeException                       If the user inputs an empty description, to handle error and exception.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        int index = 0;
        if (description.substring(4).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The description of todo cannot be empty.\n");
        }
        index = Integer.parseInt(description.substring(5)) - 1;
        (taskList.getATask(index)).setDone();
        ui.showOutputToUser(DONE_MSG + taskList.getATask(index).getDescription());
        storage.save();
    }
}