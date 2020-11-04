package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;


/**
 * This is to represent the FindCommand. It is used to find the tasks
 * that contains a specific keyword
 *
 */

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.findList(keyword);
    }

}
