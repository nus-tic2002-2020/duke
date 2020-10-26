package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    public static final String DELETE_MSG = "Noted. I've removed this task: \n";
    public static final String DELETE_MSG1 = "\n\tNow you have ";
    public static final String DELETE_MSG2 = " in task list.\n";


    public DeleteCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        try {
            if (description.substring(7).equals("")){ //if after keywords is empty
                throw new DukeException("\t☹ OOPS!!! Which task to delete? \n");
            }
            index = Integer.parseInt(description.substring(7))-1;
            ui.showOutputToUser(DELETE_MSG + "\t" + taskList.getATask(index));
            taskList.toDelete(index);
            //ui.showOutputToUser(DELETE_MSG1 + taskList.length() + DELETE_MSG2);
            storage.save();
        }catch (DukeException | Storage.StorageOperationException e){
            System.out.println("\t____________________________________________________________");
            System.out.println("\t☹ OOPS!!! Provide a task number.2");
            System.out.println("\t____________________________________________________________");
        }
    }
}