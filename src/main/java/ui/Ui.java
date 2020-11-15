package ui;

import java.util.Scanner;
import tasks.TaskList;
import commands.*;

public class Ui {

    public Ui() {}

    /**
     * Welcome message to show to the user when starting up Duke
     */
    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to Duke!\n" + logo +
                "What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------");
    }

    public static String readCommand(){
        String command;
        Scanner echo = new Scanner(System.in);
        command = echo.nextLine();
        return command;
    }

    public static void printTasks(TaskList tasks) {
        ListCommand.printTasks(tasks);
    }

    public static void taskDone(TaskList tasks, String echo) {
        String[] splitMessage = echo.split(" ");
        int item = Integer.parseInt(splitMessage[1]) - 1;
        tasks.getTask(item).markAsDone();
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(item).toString());
        System.out.println("-----------------------------------------------");
    }

    public static void taskUndone(TaskList tasks, String echo) {
        String[] splitMessage = echo.split(" ");
        int item = Integer.parseInt(splitMessage[1]) - 1;
        tasks.getTask(item).markAsUndone();
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + tasks.getTask(item).toString());
        System.out.println("-----------------------------------------------");
    }
}
