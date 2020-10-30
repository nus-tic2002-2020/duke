package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(TaskList list, Ui ui, Storage storage){

    }

}
