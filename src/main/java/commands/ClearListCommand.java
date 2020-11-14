package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class ClearListCommand extends Command {
    public ClearListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.clearAllTasks();
        tasks.printList();
    }

    public static void printHelp() {
        System.out.println("Clearing the task list: /clear");
    }
}
