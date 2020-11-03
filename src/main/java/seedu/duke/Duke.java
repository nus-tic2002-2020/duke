package seedu.duke;

import seedu.duke.commands.*;
import seedu.duke.exception.*;
import seedu.duke.parser.*;
import seedu.duke.storage.*;
import seedu.duke.ui.*;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                //Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parserCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showLoadingError(e.getMessage());
            } //finally {
            //Ui.showLine();
            //}
        }
    }

    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }
}