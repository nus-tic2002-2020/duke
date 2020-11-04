package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;


/**
 * This represents the done command. It is used to change the task from the task list to its
 * done "state".
 */

public class DoneCommand extends Command{
    private int option = 0;
    public DoneCommand(int option){
        this.option = option;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        if(option <= tasks.getSize() && option >= 1){
            tasks.get(option - 1).changeDoneTo(true);
            System.out.println(System.lineSeparator() + "Nice! I've marked this task as done:" + System.lineSeparator() + tasks.get(option - 1).toString());

        }else{
            System.out.println("Input Invalid");
        }
        return;
    }
}
