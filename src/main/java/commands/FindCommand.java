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
    /**
     * This is the constructor for the FindCommand.
     * @param keyword This keyword is to be used for the searching afterwards during execution
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }


    /**
     * This executes the FindCommand. It will call the findList method with the keyword.
     * @param tasks This is the Task List that contains the list of tasks.
     * @param ui This is the ui, to be used for scanning and printing
     * @param storage This is the storage, used to read and write over the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.findList(keyword);
    }

}
