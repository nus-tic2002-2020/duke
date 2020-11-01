package dukeui;

import dukeexceeption.DukeException;
import dukelist.*;
import duketask.*;

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
     *
     */
    public Ui(){
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
    }

    /**
     * To display a <code>horizontal line</code> in user UI.
     */
    public static void line(){
        System.out.print(ANSI_BLUE);
        for(int i = 0; i < 50; i++) System.out.print("_");
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
        System.out.println(ANSI_YELLOW + logo + "Hello! I'm Duke\n" + "What can I do for you?" + ANSI_RESET);
    }

    /**
     * To read a <code>command</code> input by user to user UI,
     * return the <code>command</code> if it is valid,
     * otherwise it will show error.
     */
    public String[] readCommand (){
        while(true) {
            Scanner input = new Scanner(System.in);
            userInput = input.nextLine().split("\\s", 2);
            userInput[0].toLowerCase();

            try {
                if (!validInput.contains(userInput[0])) {
                    line();
                    System.out.println(ANSI_RED + "   Invalid Input." + ANSI_RESET);
                    line();
                    throw new DukeException();
                } else if (userInput.length < 2 && !userInput[0].equals("bye") && !userInput[0].equals("list")) {
                    line();
                    System.out.println(ANSI_RED + "   Description cannot be empty." + ANSI_RESET);
                    line();
                    throw new DukeException();
                } else if (userInput[0].equals("deadline") && !userInput[1].contains("/by") && !userInput[1].contains("/takes")) {
                    line();
                    System.out.println(ANSI_RED + "   Missing /by or /takes schedule." + ANSI_RESET);
                    line();
                    throw new DukeException();
                } else if (userInput[0].equals("event") && !userInput[1].contains("/at") && !userInput[1].contains("/takes")) {
                    line();
                    System.out.println(ANSI_RED + "   Missing /at or /takes schedule." + ANSI_RESET);
                    line();
                    throw new DukeException();
                }
            }
            catch (DukeException d){
                continue;
            }

            return userInput;
        }
    }

    /**
     * To display the current <code>task list</code> to user UI.
     *
     * @param tasks the Task Arraylist which stores the tasks
     */
    public void showList(TaskList tasks) {
        line();
        System.out.println(ANSI_YELLOW + "   Here are the tasks in your list:" + ANSI_RESET);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i+1) + "." + tasks.getTask(i) + ANSI_RESET);
        }
        line();
    }

    /**
     * To display the <code>find result</code> of the key word to user UI.
     *
     * @param tasks the task list with the find result
     */
    public void findList(TaskList tasks) {
        line();
        System.out.println(ANSI_YELLOW + "   Here are the matching tasks in your list:" + ANSI_RESET);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i+1) + "." + tasks.getTask(i) + ANSI_RESET);
        }
        line();
    }

    /**
     * To display a <code>bye message</code> to user UI.
     */
    public void bye(){
        line();
        System.out.println(ANSI_YELLOW + "   Bye. Hope to see you again soon!" + ANSI_RESET);
        line();
    }

    /**
     * When user <code>add</code> a task, display a <code>message</code> to user UI.
     *
     * @param task the task added to the list
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
     * @param task the task deleted from the list
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

}
