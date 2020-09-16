package duke.command;
import duke.task.DukeException;

/**
 * <h2> Duke, your personalized task manager </h2>
 * <br>This task manager shall assist to add / remove various kinds of tasks that might include their estimated completion dates.
 * <br>It also allows you to view your current tasks at hand, check if they have been completed and check which tasks occur on any given date.
 * <p>
 * Its error-detection and user-input flexibility makes this task manager more functional and user-friendly.
 * <br>Hope you enjoy using this task manager !
 *
 * @author Roshan Kumar
 * @version 1.0
 * @since 15/09/2020
 */

public class Duke {

    /**
     * This is the main method which does 3 key things to get the program started.
     *<br>  1. It initializes many different formats of possible Date Time configurations which allows flexibility in user input.
     *<br>  2. It loads any previously stored Tasks List into the current program so you can continue from that point forward.
     *<br>  3. It loads the UI class that starts the interaction with the user.
     *
     * @param args Unused.
     * @throws DukeException Error thrown when user input is not complete or in an unreadable format.
     * @see DukeException
     */

    public static void main(String[] args) throws DukeException {
        DateTimeList.main(); //Initialize DateTimeFormats
        Storage.main(); //Initialize Stored Data into TaskList
        Ui.main(); //Start interaction with User
    }
}