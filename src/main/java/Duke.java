import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

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

    private static void printList(String echo) {
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String echo = "";

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (!echo.equals("bye")) {
            echo = scan.nextLine();

            if (echo.equals("list")) {
                printList(echo);
            }
            else if (echo.contains("done")) {
                taskDone(echo);
            }
            else {
                addTask(echo);
            }
        }
    }
}
