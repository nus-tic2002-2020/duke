package dukeui;

import dukeexception.DukeException;
import dukelist.TaskList;
import duketask.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected String[] userInput;
    protected ArrayList<String> validInput;

    /**
     * Constructor of <code>Ui</code> class, initialize user input String Array and valid input String Array.
     */
    public Ui() {
        userInput = new String[2];
        validInput = new ArrayList<>();
        validInput.add("bye");
        validInput.add("list");
        validInput.add("todo");
        validInput.add("deadline");
        validInput.add("event");
        validInput.add("delete");
        validInput.add("done");
        validInput.add("update");
        validInput.add("find");
        validInput.add("copy");
    }

    /**
     * To display a <code>horizontal line</code> in user UI.
     */
    private static void line() {
        System.out.print(ANSI_BLUE);
        for (int i = 0; i < 50; i++) System.out.print("─");
        System.out.print(ANSI_RESET);
        System.out.print("\n");
    }

    /**
     * To display a <code>welcome message</code> in user UI.
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        line();
        System.out.println(ANSI_YELLOW + logo + "Hello! I'm Duke\n" + "What can I do for you?" + ANSI_RESET);
        line();
    }

    /**
     * To read a <code>command</code> input by user to user UI,
     * separate the command and content and return them.
     *
     * @return the String Array of the command
     */
    public String[] readCommand() {
        while (true) {
            Scanner input = new Scanner(System.in);
            userInput = input.nextLine().split("\\s", 2);
            userInput[0].toLowerCase();

            return userInput;
        }
    }

    /**
     * To display the <code>error</code> while checking.
     *
     * @param error detail of the error
     */
    public void showError(String error) {
        line();
        System.out.println(ANSI_RED + "   " + error + ANSI_RESET);
        line();
    }

    /**
     * To display the current <code>task list</code> to user UI.
     *
     * @param tasks the Task Arraylist which stores the tasks
     */
    public void showList(TaskList tasks) {
        line();
        System.out.println(ANSI_YELLOW + "   Here are the tasks in your list:" + ANSI_RESET);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i + 1) + "." + tasks.getTask(i) + ANSI_RESET);
        }
        line();
    }

    /**
     * To display the <code>find result</code> of the key word to user UI.
     *
     * @param tasks the task list with the find result
     */
    public void findTask(TaskList tasks) {
        line();
        System.out.println(ANSI_YELLOW + "   Here are the matching tasks in your list:" + ANSI_RESET);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i + 1) + "." + tasks.getTask(i) + ANSI_RESET);
        }
        line();
    }

    /**
     * To display a <code>bye message</code> to user UI.
     */
    public void bye() {
        line();
        System.out.println(ANSI_YELLOW + "   Bye. Hope to see you again soon!" + ANSI_RESET);
        line();
    }

    /**
     * To display a <code>bye message</code> to user UI.
     */
    public void help() {
        line();
        System.out.println(ANSI_YELLOW + "   The valid commands are:\n" +
                "   todo, deadline, event, delete, done, update, find,\ncopy, bye" + ANSI_RESET);
        line();
    }

    /**
     * When user <code>add</code> a task, display a <code>message</code> to user UI.
     *
     * @param task      the task added to the list
     * @param tasksSize the size of the task list
     */
    public void addTask(Task task, int tasksSize) {
        line();
        System.out.println(ANSI_YELLOW + "   Got it. I've added this task: \n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list." + ANSI_RESET);
        line();
    }

    /**
     * When user <code>delete</code> a task, display a <code>message</code> to user UI.
     *
     * @param task      the task deleted from the list
     * @param tasksSize the size of the task list
     */
    public void deleteTask(Task task, int tasksSize) {
        line();
        System.out.println(ANSI_YELLOW + "   Noted. I've removed this task:\n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list." + ANSI_RESET);
        line();
    }

    /**
     * When user <code>change status</code> a task to done, display a <code>message</code> to user UI.
     *
     * @param task the task changed status
     */
    public void doneTask(Task task) {
        line();
        System.out.println(ANSI_YELLOW + "   This task's status has been updated:\n"
                + "   " + task + ANSI_RESET);
        line();
    }

    /**
     * When user <code>updates</code> a task information, display a <code>message</code> of the task has been updated.
     *
     * @param task the task has been updated
     */
    public void updateTask(Task task) {
        line();
        System.out.println(ANSI_YELLOW + "   This task's information has been updated:\n"
                + "   " + task + ANSI_RESET);
        line();
    }

    /**
     * When user <code>copies</code> a task information, display a <code>message</code> of the task has been copied and added.
     *
     * @param task      the task which is copied and added
     * @param tasksSize the size of the task list after copy
     */
    public void copyTask(Task task, int tasksSize) {
        line();
        System.out.println(ANSI_YELLOW + "   This task has been copied:\n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list." + ANSI_RESET);
        line();
    }

}
