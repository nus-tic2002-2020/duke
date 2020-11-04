package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;


/**
 * This represents the list command. It is meant for printing out a list
 * of tasks.
 */

public class ListCommand extends Command {
    public ListCommand(){
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
