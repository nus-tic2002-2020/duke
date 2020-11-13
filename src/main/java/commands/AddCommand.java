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

    /**
     * This is a constructor for the AddCommand
     * @param type This indicates the type or subclass of Task
     * @param description This describes the activity of the Task
     * @param secPart This is mainly for deadlines or events whereby they have a /at or /by. The time or location.
     */
    public AddCommand(String type, String description, String secPart){
        this.taskType = type;
        this.taskDescription = description;
        this.taskSecondPart = secPart;
    }


    /**
     * This executes the AddCommand. It will add the Task to the TaskList by calling the
     * addToList method.
     * @param tasks This is the Task List that contains the list of tasks.
     * @param ui This is the ui, to be used for scanning and printing
     * @param storage This is the storage, used to read and write over the file.
     * @throws DukeException When the taskType is not a Task or its subclasses.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskType,taskDescription,taskSecondPart);

        int size = tasks.getSize();
        assert size > 0: "Size should be more than 0 after adding a task";
        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + tasks.get(size - 1).toString());


        tasks.printNumberOfTasks();
    }

    public static void printHelp(){
        System.out.println("Adding a generic task: [description]");
        System.out.println("Adding a ToDo task: todo [description]");
        System.out.println("Adding a Deadline task: deadline [description] /by [Date and Time]");
        System.out.println("Adding a Deadline task: event [description] /at [location]");
    }

    public String getTaskType(){
        return this.taskType;
    }

    public String getTaskDescription(){
        return this.taskDescription;
    }

    public String getTaskSecondPart(){
        return this.taskSecondPart;
    }
}
