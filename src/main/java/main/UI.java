package main;

import java.util.Scanner;

public class UI {

    Scanner sc = new Scanner(System.in);

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "(づ｡◕‿‿◕｡)づ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("To add a new todo task, please input: todo <taskname>");
        System.out.println("To mark a task as done, please input : done <task number>");
        System.out.println("To add a new deadline task, please input : deadline <taskname> /by yyyy-mm-dd  hh:mm<AM/PM> e.g. 2020-12-12 00:00AM");
        System.out.println("To add a new event task, please input: event <taskname> /at yyyy-mm-dd hh:mm<AM/PM> e.g. 2020-12-12 00:00AM");
        System.out.println("To add a recurring event task, please input: event <taskname> /at yyyy-mm-dd hh:mm<AM/PM> e.g. 2020-12-12 00:00AM /repeat <no. of days in between> /times <no. of times>");
        System.out.println("To add a recurring deadline task, please input: deadline <taskname> /by yyyy-mm-dd hh:mm<AM/PM> e.g. 2020-12-12 00:00AM /repeat <no. of days in between> /times <no. of times>");
        System.out.println("To print out task list, please input: list ");
        System.out.println("To delete, please input: delete <task number> e.g. delete 1");
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

    public void printDateTimeExceptionMessage() {
        System.out.println("date not recognized. Please input in this format: yyyy-mm-dd hh:mm<AM/PM>");
    }

    public void printInvalidIndex(){
        System.out.println("It doesn't exist. Please enter a valid selection!!!");
    }
}