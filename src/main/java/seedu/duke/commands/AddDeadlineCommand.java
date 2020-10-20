package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DEADLINE_MSG = "Got it. I've added this task:\n\t";
    public static final String DEADLINE_MSG1 = "Now you have ";
    public static final String DEADLINE_MSG2 = " in task list.\n";
    protected Deadline deadline;

    public AddDeadlineCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (description.substring(8).equals("")){ //if after keywords is empty
                throw new DukeException("\t☹ OOPS!!! The description of deadline cannot be empty.\n");
            }else if (description.contains("by  ")){ //if after keywords is empty
                throw new DukeException("\t☹ OOPS!!! The event date cannot be empty.\n");
            }
            TaskList.setTaskList(new Deadline(description.substring(9, description.indexOf("by")-2), description.substring(description.indexOf("by")+3)));
            ui.showOutputToUser(DEADLINE_MSG + deadline.getDescription() + DEADLINE_MSG1 + taskList.length() + DEADLINE_MSG2);
            storage.save();
        }catch (DukeException | Storage.StorageOperationException e){
            System.out.println("\t____________________________________________________________");
            System.out.println("\t☹ OOPS!!! The description of deadline cannot be empty.2");
            System.out.println("\t____________________________________________________________");
        }
    }
}