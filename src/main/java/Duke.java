import dukecommand.Command;
import dukeexceeption.*;
import dukelist.*;
import dukefile.*;
import dukeui.*;

public class Duke {

    /*public static void dukeSearchDate(String date){
        
    }}*/

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean exit;

    /**
     * Constructor of <code>Duke</code> class, initialize task list, user ui, user list file path and value of exit.
     *
     * @param filePath the file path to save the task list file
     *
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        exit = false;
        /*try {
            tasks = new TaskList();
        } catch (DukeException e) {
            //ui.showLoadingError();
        }*/
    }

    /**
     * Method to start the Duke program.
     *
     */
    public void run() {
        ui.welcomeMessage();

        while (!exit) {
                String[] command = ui.readCommand();
                Command cmd = new Command(command);
                cmd.execute(tasks, ui);
                exit = cmd.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
