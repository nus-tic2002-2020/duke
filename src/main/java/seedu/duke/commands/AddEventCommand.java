package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EVENT_MSG = "Got it. I've added this task:\n\t";
    public static final String EVENT_MSG1 = "Now you have ";
    public static final String EVENT_MSG2 = " in task list.";
    protected Event event;

    public AddEventCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (description.substring(5).equals("")){ //if after keywords is empty
                throw new DukeException("\t☹ OOPS!!! The description of event cannot be empty.\n");
            }else if (description.contains("at  ")){ //if after keywords is empty
                throw new DukeException("\t☹ OOPS!!! The event date cannot be empty.\n");
            }
            event = new Event(description.substring(6, description.indexOf("at")-2), description.substring(description.indexOf("at")+3));
            TaskList.setTaskList(event);
            ui.showOutputToUser(EVENT_MSG  + EVENT_MSG1 + taskList.length() + EVENT_MSG2);
            storage.save();
        }catch (DukeException | Storage.StorageOperationException e){
            System.out.println("\t____________________________________________________________");
            System.out.println("\t☹ OOPS!!! The description of event cannot be empty.2");
            System.out.println("\t____________________________________________________________");
        }
    }
}