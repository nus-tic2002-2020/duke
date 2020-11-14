import java.util.Scanner;
import java.util.ArrayList;

import tasks.*;
import exception.DukeException;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String[] keywords = {"list", "bye", "done", "deadline", "event", "todo", "delete"};


    private static void addTask(String echo) {
        String description = "";
        String by = "";
        String[] splitMessage = echo.split(" ");
        int flagIndex = 0;

        for (int i = 1; i < splitMessage.length; i++) {
            if (!splitMessage[i].contains("/")) {
                description = description + " " + splitMessage[i];
            }
            else {
                flagIndex = i + 1;
                break;
            }
        }
        for (int i = flagIndex; i < splitMessage.length; i++) {
            by = by + " " + splitMessage[i];
        }

        if (splitMessage[0].contains("deadline")) {
            Task newTask = new Deadline(description, by);
            createNewTask(newTask);
        }
        else if (splitMessage[0].contains("event")) {
            Task newTask = new Event(description, by);
            createNewTask(newTask);
        }
        else if (splitMessage[0].contains("todo")) {
            Task newTask = new Todo(description);
            createNewTask(newTask);
        }
    }

    private static void createNewTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + numberOfTask() + " task in the list.");
    }

    private static void deleteTask(String index) {
        int numberIndex = Integer.parseInt(index);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + taskList.get(numberIndex - 1).toString());
        taskList.remove(numberIndex - 1);
        System.out.println("Now you have " + numberOfTask() + " task in the list.");
    }

    private static String[] messageSplitter(String echo) {
        return echo.split(" ");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i));
        }
    }

    private static void taskDone(String echo) {
        String[] splitMessage = echo.split(" ");
        int item = Integer.parseInt(splitMessage[1]) - 1;
        taskList.get(item).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(item).toString());
    }

    private static int numberOfTask() {
        return taskList.size();
    }

    private static void checkForError(String echo) throws DukeException {
        boolean flag = true;
        String[] splitMessage = echo.split(" ");

        for (int i = 2; i < keywords.length; i++) {
            if (splitMessage[0].equals(keywords[i])) {
                flag = false;
                break;
            }
        }

        if (flag) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        else if (splitMessage.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + splitMessage[0] + " cannot be empty.");
        }
    }

    public static void main(String[] args) throws DukeException {
        Scanner scan = new Scanner(System.in);
        String echo;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (true) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                System.out.println("See you!");
                break;
            }
            else if (echo.equals("list")) {
                printList();
            }
            else if (echo.contains("done")) {
                checkForError(echo);
                taskDone(echo);
            }
            else if (echo.contains("delete")) {
                String[] splitMessage = messageSplitter(echo);
                deleteTask(splitMessage[1]);
            }
            else {
                checkForError(echo);
                addTask(echo);
            }
        }
    }
}
