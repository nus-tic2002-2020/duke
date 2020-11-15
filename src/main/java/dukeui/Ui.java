package dukeui;

import dukelist.TaskList;
import duketask.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

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
        for (int i = 0; i < 50; i++) System.out.print("─");
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
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "(Key in 'help' for available commands)");
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
        System.out.println("   " + error);
        line();
    }

    /**
     * To display the current <code>task list</code> to user UI.
     *
     * @param tasks the Task Arraylist which stores the tasks
     */
    public void showList(TaskList tasks) {
        line();
        System.out.println("   Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + String.valueOf(i + 1) + "." + tasks.getTask(i));
        }
        line();
    }

    /**
     * To display the <code>find result</code> of the key word to user UI.
     *
     * @param result the String ArrayList task list with the find result
     */
    public void findTask(ArrayList<String> result) {
        line();
        System.out.println("   Here are the matching tasks in your list:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("     " + result.get(i));
        }
        line();
    }

    /**
     * To display a <code>bye message</code> to user UI.
     */
    public void bye() {
        line();
        System.out.println("   Bye. Hope to see you again soon!");
        line();
    }

    /**
     * To display <code>valid commands</code> to user UI.
     */
    public void help() {
        line();
        System.out.println("   The valid commands are:\n" +
                "     'list', to list out the current tasks\n" +
                "     'todo description', or 'todo description /takes duration'.\n" +
                "     example: 'todo finish reports /takes 1 day'\n" +
                "     'deadline description /by date and time', or 'deadline description /takes duration'.\n" +
                "     example: 'deadline submit report /by 11/11/2020 1500' \n" +
                "     'event description /at date time', or 'event description /takes duration'.\n" +
                "     example: 'event prepare report /at 11/11/2020 1500' \n" +
                "     'delete index of the task', to delete a task.\n" +
                "     example: 'delete 2'\n" +
                "     'done index of the task', to change status to done.\n" +
                "     example: 'done 2'\n" +
                "     'undone index of the task', to change status to not done.\n" +
                "     example: 'undone 2'\n" +
                "     'copy index of the task', to copy a task.\n" +
                "     example: 'copy 2'\n" +
                "     'find keyword', to find a task based on the keyword.\n" +
                "     example: 'find Jason'\n" +
                "     'update index of th task /des /by /at /takes or /reset', to update a task.\n" +
                "     example: 'update 2 /des finalise report'\n" +
                "     example: 'update 3 /at 12/12/2020 1100'\n" +
                "     example: 'update 4 /reset mark report /by 12/12/2020 1100'\n" +
                "     'bye', to exit the program.");
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
        System.out.println("   Got it. I've added this task: \n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list.");
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
        System.out.println("   Noted. I've removed this task:\n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list.");
        line();
    }

    /**
     * When user <code>change status</code> a task to done, display a <code>message</code> to user UI.
     *
     * @param task the task changed status
     */
    public void doneTask(Task task) {
        line();
        System.out.println("   This task's status has been updated:\n"
                + "     " + task);
        line();
    }

    /**
     * When user <code>updates</code> a task information, display a <code>message</code> of the task has been updated.
     *
     * @param task the task has been updated
     */
    public void updateTask(Task task) {
        line();
        System.out.println("   This task's information has been updated:\n"
                + "     " + task);
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
        System.out.println("   This task has been copied:\n"
                + "     " + task
                + "\n   Now you have " + tasksSize + " tasks in the list.");
        line();
    }

}
