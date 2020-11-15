import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


import java.io.IOException;

//TODO: CLEAN UP!
//TODO: Exceptions - Probably could double check more,
// Work on Individual Features more
// Double check on assertions

// Individual Features:
//Better Search - e.g., find items even if the keyword matches the item only partially.
// Snooze - Provide a way to easily snooze/postpone/reschedule tasks.
// 'Do within a period' task - Provide support for managing tasks that need to be done within a certain period e.g., collect certificate between Jan 15 and 25th.


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Used to create a new Duke Instance. Initialise the ui,storage and tasks.
     * tasks will load from the storage. If there is an error, it will initialise on its own.
     *
     * @param filePath The String filePath to storage
     * @throws IOException
     */

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Used to keep the Duke Chat bot running. Scan for input then Parse the input into a command
     * Afterwards, execute the command. The process will keep running until the user types "bye"
     *
     * @throws IOException
     */
    public void run() throws IOException {
        Command c;
        String input;
        ui.printIntro();

        boolean isRunning = true;
        while (isRunning) {
            try {
                input = ui.scanForInput();
                ui.showLine();
                c = parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.printBye();

    }


    public static void main(String[] args) throws IOException{
        new Duke("data/tasks_list.txt").run();
    }
}
