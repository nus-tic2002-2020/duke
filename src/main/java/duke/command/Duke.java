package duke.command;
import duke.task.DukeException;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Storage.main();
        Ui.main();
    }
}