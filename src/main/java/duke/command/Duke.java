package duke.command;
import duke.task.DukeException;

public class Duke {

    public static void main(String[] args) throws DukeException {
        DateTimeList.main(); //Initialize DateTimeFormats
        Storage.main(); //Initialize Stored Data into TaskList
        Ui.main(); //Start interaction with User
    }
}