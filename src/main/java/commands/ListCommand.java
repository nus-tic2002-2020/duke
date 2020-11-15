package commands;

import tasks.TaskList;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    public static void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.numberOfTask(); i++) {
            System.out.println(i+1 + "." + tasks.getTask(i));
        }
    }
}
