package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

public class DoneCommand extends Command{
    private int option = 0;
    public DoneCommand(int option){
        this.option = option;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        if(option <= list.getSize() && option >= 1){
            list.get(option - 1).changeCompletedTo(true);
            System.out.println(System.lineSeparator() + "Nice! I've marked this task as done:" + System.lineSeparator() + list.get(option - 1).toString());

        }else{
            System.out.println("Input Invalid");
        }
        return;
    }
}
