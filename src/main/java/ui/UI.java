package ui;

import java.util.ArrayList;
import java.util.Scanner;


public class UI {

	//ArrayList<Task> listTask = new ArrayList<Task>();
	
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

    public static void printListEmpty() {
        System.out.println("List is empty");
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
}
	
		