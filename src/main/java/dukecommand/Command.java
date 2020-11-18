package dukecommand;

import dukeexception.DukeException;
import dukefile.Storage;
import dukelist.TaskList;
import duketask.Deadline;
import duketask.Event;
import duketask.Todo;
import dukeui.Ui;

import java.util.ArrayList;

public class Command {
    private String[] command;
    private boolean exit;
    private ArrayList<String> validCommand;

    /**
     * Constructor of <code>Command</code> class, store the received command.
     *
     * @param command the String received as execution command
     */
    public Command(String[] command) {
        this.command = command;
        exit = false;
        validCommand = new ArrayList<>();
        validCommand.add("bye");
        validCommand.add("list");
        validCommand.add("help");
        validCommand.add("todo");
        validCommand.add("deadline");
        validCommand.add("event");
        validCommand.add("delete");
        validCommand.add("done");
        validCommand.add("undone");
        validCommand.add("update");
        validCommand.add("find");
        validCommand.add("copy");
    }

    /**
     * Check user issues command to <code>exit</code>.
     *
     * @return boolean value of exit
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Check if the number in the command is a valid <code>index</code> of task.
     *
     * @param s     the String represents the task index number in the command
     * @param range Number of the total tasks
     * @return true if the input String is a valid integer number or within the tasks range, otherwise false.
     */
    private boolean isIndex(String s, int range) {
        int i = 0;

        if (s == null) {
            return false;
        }

        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        if (i < 1 || i > range) {
            return false;
        }

        if (i > range && i < 1) {
            throw new AssertionError("Index check failed");
        }

        return true;
    }

    /**
     * Check if the command is <code>valid</code>.
     * If it is invalid, an Exception would be generated.
     */
    public void check(TaskList tasks) throws DukeException {
        if (!validCommand.contains(command[0])) {
            throw new DukeException("Invalid command,\n   Key in 'help' for valid commands.");
        } else if (!command[0].equals("bye") && !command[0].equals("list") && !command[0].equals("help") && command.length < 2) {
            throw new DukeException("Incomplete command.");
        } else if (command[0].equals("task") && !command[2].contains("/by")) {
            throw new DukeException("Todo task does not accept /by schedule.\n   Please create Deadline task.");
        } else if (command[0].equals("task") && !command[2].contains("/at")) {
            throw new DukeException("Todo task does not accept /at schedule.\n   Please create Event task.");
        } else if (command[0].equals("deadline") && !command[1].contains("/by") && !command[1].contains("/takes")) {
            throw new DukeException("Missing /by or /takes schedule.");
        } else if (command[0].equals("event") && !command[1].contains("/at") && !command[1].contains("/takes")) {
            throw new DukeException("Missing /at or /takes schedule.");
        } else if (command[0].equals("delete") && !isIndex(command[1], tasks.size())) {
            throw new DukeException("Invalid index number of the task.\n   Key in 'list' to check the task's index.");
        } else if (command[0].equals("done") && !isIndex(command[1], tasks.size())) {
            throw new DukeException("Invalid index number of the task.\n   Key in 'list' to check the task's index.");
        } else if (command[0].equals("undone") && !isIndex(command[1], tasks.size())) {
            throw new DukeException("Invalid index number of the task.\n   Key in 'list' to check the task's index.");
        } else if (command[0].equals("find") && command.length < 2) {
            throw new DukeException("Missing the key word to find the task.");
        } else if (command[0].equals("copy") && !isIndex(command[1], tasks.size())) {
            throw new DukeException("Invalid index number of the task.\n   Key in 'list' to check the task's index.");
        }else if (command[0].equals("update") && !isIndex(command[1].split("\\s", 2)[0], tasks.size())) {
            throw new DukeException("Invalid index number of the task.\n   Key in 'list' to check the task's index.");
        }
    }

    /**
     * Execute the <code>command</code>.
     *
     * @param tasks the task list stores the tasks
     * @param ui    the ui used to display user ui
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command[0]) {
            case "bye":
                ui.bye();
                exit = true;
                break;
            case "list":
                ui.showList(tasks);
                break;
            case "help":
                ui.help();
                break;
            case "done":
                tasks.doneTask(Integer.parseInt(command[1]) - 1);
                ui.doneTask(tasks.getTask(Integer.parseInt(command[1]) - 1));
                storage.updateTasks(tasks);
                break;
            case "undone":
                tasks.unDoneTask(Integer.parseInt(command[1]) - 1);
                ui.doneTask(tasks.getTask(Integer.parseInt(command[1]) - 1));
                storage.updateTasks(tasks);
                break;
            case "todo":
                tasks.addTask(new Todo(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                storage.writeTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "deadline":
                tasks.addTask(new Deadline(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                storage.writeTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "event":
                tasks.addTask(new Event(command[1]));
                ui.addTask(tasks.getTask(tasks.size() - 1), tasks.size());
                storage.writeTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
            case "delete":
                ui.deleteTask(tasks.getTask(Integer.parseInt(command[1]) - 1), tasks.size() - 1);
                tasks.deleteTask(Integer.parseInt(command[1]) - 1);
                storage.updateTasks(tasks);
                break;
            case "find":
                ui.findTask(tasks.findTask(command[1]));
                break;
            case "update":
                ui.updateTask(tasks.updateTask(command[1]));
                storage.updateTasks(tasks);
                break;
            case "copy":
                tasks.copyTask(Integer.parseInt(command[1]) - 1);
                ui.copyTask(tasks.getTask(Integer.parseInt(command[1]) - 1), tasks.size());
                storage.writeTask(tasks.getTask(tasks.size() - 1), tasks.size());
                break;
        }
        assert false;
    }
}
