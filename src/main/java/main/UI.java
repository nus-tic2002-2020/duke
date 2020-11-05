package main;

import java.util.Scanner;

public class UI {

    Scanner sc = new Scanner(System.in);

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void printTaskList(int count, TaskList line) {
        System.out.println("_______________________________________________");
        for (int i = 0; i < count; i++) {

            System.out.println(i + 1 + ". " + line.get(i));

        }
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_______________________________________________");
    }

    public void printDone(TaskList line, int index) {
        System.out.println("_______________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t[" + line.get(index - 1));
        System.out.println("_______________________________________________");
    }

    public void printTodo(TaskList line, int count) {
        System.out.println("_______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + line.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_______________________________________________");

    }

    public void printDeadline(TaskList line, int count) {
        System.out.println("_______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + line.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_______________________________________________");
    }

    public void printEvent(TaskList line, int count) {
        System.out.println("_______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + line.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_______________________________________________");
    }

    public void printDelete(Task t, int count, int index) {
        System.out.println("_______________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(index + ". " + t);
        count--;

        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("_______________________________________________");

    }

    public void printInvalidCommandMessage() {
        System.out.println("Oops sorry, I don't know what that means :( ");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon !");
    }

    public String getUserInput() {
        String input = sc.nextLine();
        return input;
    }
}