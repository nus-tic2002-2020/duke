package dukecommand;

import dukelist.*;
import duketask.*;
import dukeui.*;

public class Command {
    private String[] command;
    private boolean exit;

    /**
     * Constructor of <code>Command</code> class, store the received command.
     *
     * @param command the String received as execution command
     *
     */
    public Command(String[] command) {
        this.command = command;
        exit = false;
    }

    /**
     * Return the boolean value of <code>exit</code>.
     *
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Execute the <code>command</code>.
     *
     * @param tasks the task list stores the tasks
     *
     * @param ui the ui used to display user ui
     */
    public void execute(TaskList tasks, Ui ui) {
        switch (command[0]) {
            case "bye":
                ui.bye();
                exit = true;
                break;
            case "list":
                ui.showList(tasks);
                break;
            case "done":
                tasks.doneTask(Integer.parseInt(command[1]) - 1);
                ui.doneTask(tasks.getTask(Integer.parseInt(command[1]) - 1));
                break;
            case "todo":
                tasks.addTask(new Todo(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "deadline":
                tasks.addTask(new Deadline(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "event":
                tasks.addTask(new Event(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "delete":
                ui.deleteTask(tasks.getTask(Integer.parseInt(command[1]) - 1), tasks.size() - 1);
                tasks.deleteTask(Integer.parseInt(command[1]) - 1);
                break;
            case "find":
                ui.findList(tasks.findKey(command[1]));
                break;
        }
    }
}
