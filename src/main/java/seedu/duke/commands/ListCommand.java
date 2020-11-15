package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String LIST_MSG = "Here are the tasks in your list:";

    /**
     * Creates ListCommand and initialise the isExit boolean value and description according to user input.
     *
     * @param isExit      The boolean value if the exit condition is true.
     * @param description The task description/command from user.
     */
    public ListCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Returns the task with index and description accordingly.
     * @param indexToShow   The index of the task.
     * @param task          The task description.
     * @return String       The task with index in string type.
     */
    public static String getTaskString(int indexToShow, String task){
        return String.format( "%1$d. %2$s", indexToShow, task);
    }

    /**
     * Returns the task in the list with index.
     * @param tasks The task in the task list.
     * @return String The task with index in string type.
     */
    public static String getIndexedList(List<String> tasks){
        int index = 1;
        String output = "";
        for (String task : tasks){
            output += "\n\t" + getTaskString(index, task);
            index++;
        }
        return output;
    }

    /**
     * Prints all the task stored in the taskList.
     *
     * @return String     The task and its progression status.
     */
    public String getTaskList(TaskList taskList) {
        List<String> indexedList = new ArrayList<>();
        for (int i = 0; i < taskList.length(); i++) {
            indexedList.add(taskList.getATask(i).getDescription());
        }
        return getIndexedList(indexedList);
    }

    /**
     * Prints the task list through User Interface (UI).
     *
     * @param taskList The array of tasks stored in as an ArrayList.
     * @param ui       The User Interface (UI) to handle the interaction with user.
     * @param storage  The storage to handle storing and reading of task from the file.
     * @throws DukeException To handle error and exception, if the task list is empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.length() == 0) {
            throw new DukeException("The task list is empty!");
        }
        ui.showOutputToUser(LIST_MSG + getTaskList(taskList));
    }
}