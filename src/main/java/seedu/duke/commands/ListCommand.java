package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String LIST_MSG = "Here are the tasks in your list:\n\t";

    /**
     * To create ListCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public ListCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * To print all the tasks stored in the taskList.
     * @return   String   The task and its progression status.
     */
    public static void getTaskList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i < TaskList.length(); i++) {
            System.out.println("\t" + (i + 1) + ". " + TaskList.getATask(i).getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * To print the task list through UI.
     * @param   taskList                            The array of tasks stored in an ArrayList.
     * @param   ui                                  The User Interface (UI) to handle interaction with user.
     * @param   storage                             The storage to handle storing and reading of tasks from a file.
     * @throws  DukeException                       If the task list is empty description, to handle error and exception.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (TaskList.length() == 0) {
            throw new DukeException("The task list is empty!");
        }
        getTaskList();
    }
}