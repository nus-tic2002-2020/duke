package seedu.duke.commands;

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
     * To create AddTodoCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public AddTodoCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * To create Todo task, check if description is empty prior creating.
     * @param   taskList                            The array of tasks stored in an ArrayList.
     * @param   ui                                  The User Interface (UI) to handle interaction with user.
     * @param   storage                             The storage to handle storing and reading of tasks from a file.
     * @throws  DukeException                       If the user inputs an empty description, to handle error and exception.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (description.substring(4).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The description of todo cannot be empty.\n");
        }
        todo = new Todo(description.substring(5));
        taskList.setTaskList(todo);
        ui.showOutputToUser(TODO_MSG + todo.getDescription() + TODO_MSG1 + taskList.length() + TODO_MSG2);
        storage.save();
    }
}