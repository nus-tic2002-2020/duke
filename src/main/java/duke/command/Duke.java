package duke.command;
import duke.task.DukeException;

import java.io.IOException;

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
     *<br>  3. It loads the UI class that starts the interaction with the user.
     *
     * @param args Unused.
     * @throws DukeException Error thrown when user input is not complete or in an unreadable format.
     * @see DukeException
     */

    public static void main(String[] args) throws DukeException, IOException {
        DateTimeList.main(); //Initialize DateTimeFormats
        Ui.main(); //Start interaction with User
    }
}