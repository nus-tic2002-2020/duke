package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String DELETE_MSG = "Noted. I've removed this task: \n";
    public static final String DELETE_MSG1 = "\n\tNow you have ";
    public static final String DELETE_MSG2 = " in task list.\n";

    /**
     * To create DeleteCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public DeleteCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * To delete the task from the taskList, check the task number if empty prior deleting.
     * @param   taskList                            The array of tasks stored in an ArrayList.
     * @param   ui                                  The User Interface (UI) to handle interaction with user.
     * @param   storage                             The storage to handle storing and reading of tasks from a file.
     * @throws  DukeException                       If the user inputs an empty description, to handle error and exception.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        int index = 0;
        if (description.substring(7).equals("")) {
            throw new DukeException("\t☹ OOPS!!! Which task to delete? \n");
        }
        index = Integer.parseInt(description.substring(7)) - 1;
        ui.showOutputToUser(DELETE_MSG + "\t" + taskList.getATask(index).getDescription());
        taskList.toDelete(index);
        ui.showOutputToUser(DELETE_MSG1 + taskList.length() + DELETE_MSG2);
        storage.save();
    }
}