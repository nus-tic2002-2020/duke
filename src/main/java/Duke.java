import dukecommand.Command;
import dukeexception.DukeException;
import dukefile.Storage;
import dukelist.TaskList;
import dukeui.Ui;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean exit;

    /**
     * Constructor of <code>Duke</code> class, initialize task list, user ui, user list file path and value of exit.
     *
     * @param filePath the file path to save the task list file
     */
    public Duke(String filePath) {

        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList();
            exit = false;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Method to start the Duke program.
     */
    public void run() {
        ui.welcomeMessage();

        while (!exit) {
            try {
                String[] command = ui.readCommand();
                Command cmd = new Command(command);
                cmd.check(tasks);
                cmd.execute(tasks, ui, storage);
                exit = cmd.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
