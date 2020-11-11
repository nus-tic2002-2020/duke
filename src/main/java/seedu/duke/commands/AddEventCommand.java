package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String EVENT_MSG = "Got it. I've added this task:\n\t";
    public static final String EVENT_MSG1 = "\n\tNow you have ";
    public static final String EVENT_MSG2 = " in task list.";
    protected Event event;

    /**
     * Creates AddEventCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit              The boolean value if the exit condition is true.
     * @param   description         The task description/command from user.
     */
    public AddEventCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Creates Event task, check if date and description is empty prior creating.
     * @param   taskList            The array of tasks stored in as an ArrayList.
     * @param   ui                  The User Interface (UI) to handle the interaction with user.
     * @param   storage             The storage to handle storing and reading of task from the file.
     * @throws  DukeException       To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (description.substring(5).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The description of event cannot be empty.\n");
        } else if (description.contains("at  ")) {
            throw new DukeException("\t☹ OOPS!!! The event date cannot be empty.\n");
        }
        String taskDescription = description.substring(6, description.indexOf("at") - 1);
        String taskDate = description.substring(description.indexOf("at") + 3);
        event = new Event(taskDescription, stringToDate(taskDate));
        TaskList.setTaskList(event);
        ui.showOutputToUser(EVENT_MSG + event.getDescription() + EVENT_MSG1 + TaskList.length() + EVENT_MSG2);
        storage.save();
    }

    /**
     * Converts the date and time stated by user into the LocalDateTime format: (d/MM/yyyy HHmm).
     * @param   date                The date stated by user.
     * @return  LocalDateTime       The date and time in a LocalDateTime format.
     * @throws  DukeException       To handle error and exception, if the input from user is not the format (dd/mm/yyyy HHmm).
     */
    private LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time must be in this format: DD/MM/YYYY HHmm");
        }
    }
}