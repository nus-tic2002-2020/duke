package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

public class ListCommand extends Command {
    public ListCommand(){
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.printList();
    }
}
