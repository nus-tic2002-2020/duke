package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String TODO_MSG = "Got it. I've added this task:\n\t";
    public static final String TODO_MSG1 = "Now you have ";
    public static final String TODO_MSG2 = " in task list.\n";
    protected Todo todo;



    public AddTodoCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            try {
                if (description.substring(4).equals("")){ //if after keywords is empty
                    throw new DukeException("\t☹ OOPS!!! The description of todo cannot be empty.\n");
                }
                TaskList.setTaskList(new Todo(description.substring(5)));
                ui.showOutputToUser(TODO_MSG + todo.getDescription() + TODO_MSG1 + taskList.length() + TODO_MSG2);
                storage.save();
            }catch (DukeException | Storage.StorageOperationException e){
                System.out.println("\t____________________________________________________________");
                System.out.println("\t☹ OOPS!!! The description of todo cannot be empty.2");
                System.out.println("\t____________________________________________________________");
            }
    }
}