package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasks.*;

/**
 * This represents the AddCommand class which adds a specific task to the task list.
 *
 */
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int size = 0;
        tasks.addToList(taskType,taskDescription,taskSecondPart);

        size = tasks.getSize();
        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + tasks.get(size - 1).toString());


        tasks.printTotalTasks();
    }

}
