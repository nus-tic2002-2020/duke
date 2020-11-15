import exception.DukeException;
import storage.Storage;
import tasklist.Task;
import tasklist.TaskList;
import ui.UI;
import ui.helpPage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Create Duke class
 * The program begins here..
 */
public class Duke {

    public static ArrayList<Task> listTask = new ArrayList<Task>();

    public static void main(String[] args) throws DukeException {

        UI.showWelcome();
        helpPage.printHelpPage1();
        try {
            Storage.loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            TaskList.printUserInput(listTask);

        }
    }
}
