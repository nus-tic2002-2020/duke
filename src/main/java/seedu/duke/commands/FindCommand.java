package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.Task;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FIND_MSG = "Here are the tasks in your list:";
    public static final String FIND_MSG2 = "You have no matched task.";

    /**
     * Creates ScheduleCommand and initialise the isExit boolean value and description according to user input.
     *
     * @param isExit      The boolean value if the exit condition is true.
     * @param description The task description/command from user.
     */
    public FindCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Searches the task if contains user input, and prints the matched tasks in a list format.
     *
     * @param taskList The array of tasks stored in as an ArrayList.
     * @param ui       The User Interface (UI) to handle the interaction with user.
     * @param storage  The storage to handle storing and reading of task from the file.
     * @throws DukeException To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean isMatch = false;
        List<String> matchedTasksList = new ArrayList<>();

        if (description.substring(4).equals("")) {
            throw new DukeException("The task description of find cannot be empty.");
        }
        description = description.substring(4);
        for (int i = 0; i < taskList.length(); i++) {
            Task task = taskList.getATask(i);
            if (task.getDescription().contains(description)) {
                matchedTasksList.add(task.getDescription());
                isMatch = true;
            }
        }
        if (isMatch) {
            ui.showOutputToUser(FIND_MSG + getIndexedList(matchedTasksList));
        } else {
            ui.showOutputToUser(FIND_MSG2);
        }
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
        String output = "";
        int index = 1;
        for (String task : tasks){
            output += "\n\t" + getTaskString(index, task);
            index++;
        }
        return output;
    }
}