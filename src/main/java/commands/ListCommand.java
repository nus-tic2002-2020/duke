package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;


/**
 * This represents the list command. It is meant for printing out a list
 * of tasks.
 */

public class ListCommand extends Command {
    /**
     * This is the constructor for the ListCommand.
     */
    public ListCommand() {
    }

    /**
     * This executes the ListCommand. It will call the printList method from the TaskList class.
     *
     * @param tasks   This is the list of Tasks
     * @param ui      This is the ui, to be used for scanning and printing
     * @param storage This is the storage, used to read and write over the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    public static void printHelp() {
        System.out.println("To print the list of tasks: list");
    }
}
