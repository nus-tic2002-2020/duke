package seedu.duke.commands;

import seedu.duke.base.Command;
import seedu.duke.base.Task;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";
    public static final String SCHEDULE_MSG = "Here are the schedule tasks in your list:";
    public static final String SCHEDULE_MSG2 = "You have nothing on that day yet.";

    /**
     * Creates ScheduleCommand and initialise the isExit boolean value and description according to user input.
     *
     * @param isExit      The boolean value if the exit condition is true.
     * @param description The task description/command from user.
     */
    public ScheduleCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    /**
     * Searches the task if the task date matched with user input date, and prints the matched tasks in a list format.
     *
     * @param taskList The array of tasks stored in as an ArrayList.
     * @param ui       The User Interface (UI) to handle the interaction with user.
     * @param storage  The storage to handle storing and reading of task from the file.
     * @throws DukeException To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        LocalDate date;
        boolean isMatch = false;
        List<String> matchedTasksList = new ArrayList<>();

        if (description.substring(8).equals("")) {
            throw new DukeException("The date of schedule cannot be empty.");
        }
        description = description.substring(9);
        date = stringToDate(description);  //date from user
        for (int i = 0; i < taskList.length(); i++) {
            Task task = taskList.getATask(i);
            if (task.getDate().toLocalDate().equals(date)) {
                matchedTasksList.add(task.getDescription());
                isMatch = true;
            }
        }
        if (isMatch) {
            ui.showOutputToUser(SCHEDULE_MSG + getIndexedList(matchedTasksList));
        } else {
            ui.showOutputToUser(SCHEDULE_MSG2);
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

    /**
     * Converts the date stated by user into the LocalDate format: (dd/MM/yyyy).
     *
     * @param date The date stated by user.
     * @return LocalDate           The date in a LocalDateTime format.
     * @throws DukeException To handle error and exception, if the input from user is not the format (dd/mm/yyyy HHmm).
     */
    public LocalDate stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time is not DD/MM/YYYY");
        }
    }
}