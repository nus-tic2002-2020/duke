package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.Todo;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String TODO_MSG = "Got it. I've added this task:\n\t";
    public static final String TODO_MSG1 = "\n\tNow you have ";
    public static final String TODO_MSG2 = " in task list.";
    protected Todo todo;

    /**
     * Creates AddTodoCommand and initialise the isExit boolean value and description according to user input.
     *
     * @param isExit      The boolean value if the exit condition is true.
     * @param description The task description/command from user.
     */
    public AddTodoCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Creates Todo task, check if the description is empty prior creating.
     *
     * @param taskList The array of tasks stored in as an ArrayList.
     * @param ui       The User Interface (UI) to handle the interaction with user.
     * @param storage  The storage to handle storing and reading of task from the file.
     * @throws DukeException To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (description.substring(4).equals("")) {
            throw new DukeException("\tThe description of todo cannot be empty.\n");
        }
        todo = new Todo(description.substring(5));
        taskList.setTaskList(todo);
        assert taskList.length() > 0; //taskList size should > 0 after adding new task
        ui.showOutputToUser(TODO_MSG + todo.getDescription() + TODO_MSG1 + taskList.length() + TODO_MSG2);
        storage.save();
    }
}