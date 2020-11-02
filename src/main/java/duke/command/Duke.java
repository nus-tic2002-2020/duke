package duke.command;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.io.Savable;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.*;

public class Duke {

    private static final String FILE_PATH = "data/tasks.ssv";
    private static final String DATA_SEPARATOR = "|";

    private Storage storageTasks;
    private TaskManager taskManager;
    private Ui ui;

    public Duke(String filePath) {
        storageTasks = new Storage(filePath);
        ui = new Ui();

        try {
            List<String> entries = storageTasks.load();
            taskManager = new TaskManager();
            taskManager.setTasksFromRaw(entries, DATA_SEPARATOR);
        } catch (DukeException | IOException e) {
            ui.loadError();
            taskManager = new TaskManager();
        }
    }

    public void run() {
        ui.welcome();

        boolean exit = false;
        boolean printEndLine = false;
        ui.greet();

        while(!exit) { // If no error, continue
            try {
                String fullCommand = ui.readCommand();

                if (fullCommand == null || fullCommand.isBlank()) { // Do not parse command
                    continue;
                } else {
                    printEndLine = true;
                }

                ui.echoLine();
                Command command = Command.parse(fullCommand);
                command.execute(taskManager, ui, storageTasks);
                exit = command.isExit();

            } catch (DukeException e) {
                e.printError();
            } finally {
                if (printEndLine) {
                    ui.echoLine();
                    printEndLine = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

}