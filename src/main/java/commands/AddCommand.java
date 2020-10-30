package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

public class AddCommand extends Command{
    String taskType;
    String taskDescription;
    String taskSecondPart;

    public AddCommand(String type, String description, String secPart){
        this.taskType = type;
        this.taskDescription = description;
        this.taskSecondPart = secPart;
    }


    public void execute(TaskList list, Ui ui, Storage storage){
        list.addToList();
    }

}
