import storage.Storage;
import tasklist.Task;
import tasklist.TaskList;
import ui.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> listTask = new ArrayList<Task>();

    public static void main(String[] args) {

        UI.showWelcome();
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
