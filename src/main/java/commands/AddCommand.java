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


    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        int size = 0;
        list.addToList(taskType,taskDescription,taskSecondPart);
        size = list.getSize();
        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + list.get(size - 1).toString());

        list.get(size - 1).printTotalTasks();
    }

}
