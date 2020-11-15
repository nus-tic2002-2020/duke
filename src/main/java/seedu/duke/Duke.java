package seedu.duke;

import seedu.duke.base.Command;
import seedu.duke.base.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates Duke with specified filepath and initialised with storage, taskList and ui.
     * @param   filePath      The file path of the storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke and show the output accordingly.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parserCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parserCommand(input);
            c.execute(taskList, ui, storage);
            return ui.showOutput();
        } catch (Exception e) {
            return ui.showLoadingError(e.getMessage());
        }
    }

    /**
     * The main method to run Duke.
     * @param   args       The argument values provided by the user to run Duke.
     */
    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }
}