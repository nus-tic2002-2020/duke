package ui;

import tasklist.Task;

import java.util.ArrayList;


public class UI {

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void showWelcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        printLine();
    }

    public static void bye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void printUserList(ArrayList<Task> listTask) {
        printLine();

        for (int i = 0; i < listTask.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + listTask.get(i).toString());
        }
        printLine();
    }

    public static void printListEmpty() {
        System.out.println("\t☹ OOPS!!! List is empty");
    }

    public static void printFileSaved() {
        System.out.println("\tSuccessfully wrote to the file.");
    }

    public static void printNoOfTask(ArrayList<Task> listItems) {
        System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
        printLine();
    }

    public static void printResultSearch(ArrayList<Task> listItems) {
        System.out.println("\tHere are the matching tasks in your list:");
        printUserList(listItems);
    }

    public static void printEmptyToDoException() {
        printLine();
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        printLine();
    }

    public static void showError() {
        printLine();
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    public static void printDateTimeFormatError() {
        printLine();
        System.out.println("☹ OOPS!!! Format to key in -> dd/mm/yyyy HHmm.");
        printLine();
    }
}
