package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DEADLINE_MSG = "Got it. I've added this task:\n\t";
    public static final String DEADLINE_MSG1 = "\n\tNow you have ";
    public static final String DEADLINE_MSG2 = " in task list.";
    protected Deadline deadline;

    public AddDeadlineCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Storage.StorageOperationException {
        if (description.substring(8).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The description of deadline cannot be empty.\n");
        } else if (description.contains("by  ")) {
            throw new DukeException("\t☹ OOPS!!! The event date cannot be empty.\n");
        }
        String taskDescription = description.substring(9, description.indexOf("by") - 1);
        String taskDate = description.substring(description.indexOf("by") + 3);
        deadline = new Deadline(taskDescription, stringToDate(taskDate));
        taskList.setTaskList(deadline);
        ui.showOutputToUser(DEADLINE_MSG + deadline.getDescription() + DEADLINE_MSG1 + taskList.length() + DEADLINE_MSG2);
        storage.save();
    }

    private LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time must be in this format: DD/MM/YYYY HHmm");
        }
    }
}