package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String DONE_MSG = "Nice! I have marked this task as done: \n\t";

    /**
     * Creates DoneCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit              The boolean value if the exit condition is true.
     * @param   description         The task description/command from user.
     */
    public DoneCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Marks the task as done, check the task number if empty prior marking it as done.
     * @param   taskList            The array of tasks stored in as an ArrayList.
     * @param   ui                  The User Interface (UI) to handle the interaction with user.
     * @param   storage             The storage to handle storing and reading of task from the file.
     * @throws  DukeException       To handle error and exception, if the user inputs an empty description.
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