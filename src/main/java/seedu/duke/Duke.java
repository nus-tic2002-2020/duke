package seedu.duke;

import seedu.duke.commands.*;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(filePath));
    }

    public void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                //Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parserCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            } //finally {
            //Ui.showLine();
            //}
        }
    }

    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }
}