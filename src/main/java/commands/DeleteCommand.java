package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

/**
 * This represents the command to delete a specific task from the task list.
 *
 */
public class DeleteCommand extends Command{
    int option = 0;

    public DeleteCommand(int option){
        this.option = option;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteFromList(option);
        tasks.printList();
    }
}
