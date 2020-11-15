package tasks;

import java.util.ArrayList;
import exception.DukeException;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static final String[] keywords = {"list", "bye", "done", "deadline", "event", "todo", "delete", "undone"};

    public static void addTask(String echo) {
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

    public static void createNewTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + numberOfTask() + " task in the list.");
        System.out.println("-----------------------------------------------");
    }

    public static void deleteTask(String index) {
        int numberIndex = Integer.parseInt(index);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + tasks.get(numberIndex - 1).toString());
        tasks.remove(numberIndex - 1);
        System.out.println("Now you have " + numberOfTask() + " task in the list.");
    }

    public static String[] messageSplitter(String echo) {
        return echo.split(" ");
    }

    public static int numberOfTask() {
        return tasks.size();
    }

    public static void checkForError(String echo) throws DukeException {
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

    public static Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
