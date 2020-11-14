package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.Deadline;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DEADLINE_MSG = "Got it. I've added this task:\n\t";
    public static final String DEADLINE_MSG1 = "\n\tNow you have ";
    public static final String DEADLINE_MSG2 = " in task list.";
    protected Deadline deadline;

    /**
     * Creates AddDeadlineCommand and initialise the isExit boolean value and description according to user input.
     *
     * @param isExit      The boolean value if the exit condition is true.
     * @param description The task description/command from user.
     */
    public AddDeadlineCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Creates Deadline task, check if date and description is empty prior creating.
     *
     * @param taskList          The array of tasks stored in as an ArrayList.
     * @param ui                The User Interface (UI) to handle the interaction with user.
     * @param storage           The storage to handle storing and reading of task from the file.
     * @throws DukeException    To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (description.substring(8).equals("")) {
            throw new DukeException("\tThe description of deadline cannot be empty.\n");
        } else if (description.contains("by  ")) {
            throw new DukeException("\tThe event date cannot be empty.\n");
        }
        String taskDescription = description.substring(9, description.indexOf("by") - 1);
        String taskDate = description.substring(description.indexOf("by") + 3);
        deadline = new Deadline(taskDescription, stringToDate(taskDate));
        taskList.setTaskList(deadline);
        assert taskList.length() > 0; //taskList size should > 0 after adding new task
        ui.showOutputToUser(DEADLINE_MSG + deadline.getDescription() + DEADLINE_MSG1 + taskList.length() + DEADLINE_MSG2);
        storage.save();
    }

    /**
     * Converts the date and time stated by user into the LocalDateTime format: (d/MM/yyyy HHmm).
     *
     * @param date The date stated by user.
     * @return LocalDateTime       The date and time in a LocalDateTime format.
     * @throws DukeException To handle error and exception, if the input from user is not the format (dd/mm/yyyy HHmm).
     */
    private LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time is not DD/MM/YYYY HHmm");
        }
    }
}