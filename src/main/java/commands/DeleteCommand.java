package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;


public class DeleteCommand extends Command{
    int option = 0;

    public DeleteCommand(int option){
        this.option = option;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.deleteFromList(option);
        list.printList();
    }
}
