import duketask.*;
import dukefile.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void line(){
        System.out.print(ANSI_BLUE);
        for(int i = 0; i < 50; i++) System.out.print("_");
        System.out.print(ANSI_RESET);
        System.out.print("\n");
    }

    public static void dukeInput(Task task, int taskCounter){
        line();
        System.out.println(ANSI_YELLOW + "   Got it. I've added this task: \n"
                + "     " + task
                + "\n   Now you have " + (taskCounter + 1) + " tasks in the list." + ANSI_RESET);
        line();
    }

    /**
     * To display a list of current tasks <code>Point</code>
     */
    public static void dukeList(ArrayList<Task> tasks, int taskCounter){
        line();
        System.out.println(ANSI_YELLOW + "   Here are the tasks in your list:" + ANSI_RESET);
        for(int i = 0; i < taskCounter; i++){
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i+1) + "." + tasks.get(i) + ANSI_RESET);
        }
        line();
    }

    public static void dukeDone(Task task){
        line();
        task.markAsDone();
        System.out.println(ANSI_YELLOW + "   This task's status has been updated:\n"
                + "   " + task + ANSI_RESET);
        line();
    }

    public static void dukeSearchDate(String date){
        
    }

    public static void dukeDelete(ArrayList<Task> tasks, String input){
        int index = Integer.parseInt(input) - 1;
        line();
        System.out.println(ANSI_YELLOW + "   Noted. I've removed this task:\n"
                + "     " + tasks.get(index)
                + "\n   Now you have " + (tasks.size() - 1) + " tasks in the list." + ANSI_RESET);
        line();
        tasks.remove(index);
    }

    public static void main(String[] args) throws DukeException {
        int taskCounter = 0;
        Dukelist taskList = new Dukelist("data\\duke.txt");

        ArrayList<String> validInput = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        validInput.add("bye");
        validInput.add("list");
        validInput.add("todo");
        validInput.add("deadline");
        validInput.add("event");
        validInput.add("delete");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(ANSI_YELLOW + logo + "Hello! I'm Duke\n" + "What can I do for you?" + ANSI_RESET);

        while(true){
            Scanner input = new Scanner(System.in);
            String[] userInput = input.nextLine().split("\\s", 2);
            userInput[0].toLowerCase();

            try {
                if (!validInput.contains(userInput[0])) {
                    line();
                    System.out.println("   Invalid Input.");
                    line();
                    throw new DukeException();
                } else if (userInput.length < 2 && !userInput[0].equals("bye") && !userInput[0].equals("list")) {
                    line();
                    System.out.println("   Description cannot be empty.");
                    line();
                    throw new DukeException();
                } else if (userInput[0].equals("deadline") && !userInput[1].contains("/by")) {
                    line();
                    System.out.println("   Missing /by schedule.");
                    line();
                    throw new DukeException();
                } else if (userInput[0].equals("event") && !userInput[1].contains("/at")) {
                    line();
                    System.out.println("   Missing /at schedule.");
                    line();
                    throw new DukeException();
                }
            }
            catch (DukeException d){
                continue;
            }

            switch (userInput[0]){
                case "bye":
                    line();
                    System.out.println(ANSI_YELLOW + "   Bye. Hope to see you again soon!" + ANSI_RESET);
                    line();
                    return;
                case "list":
                    dukeList(tasks, taskCounter);
                    break;
                case "done":
                    dukeDone(tasks.get(Integer.parseInt(userInput[1]) - 1));
                    break;
                case "todo":
                    tasks.add(new Todo(userInput[1]));
                    dukeInput(tasks.get(tasks.size() - 1), tasks.size() - 1);
                    taskCounter++;
                    break;
                case "deadline":
                    tasks.add(new Deadline(userInput[1]));
                    dukeInput(tasks.get(tasks.size() - 1), tasks.size() - 1);
                    taskCounter++;
                    break;
                case "event":
                    tasks.add(new Event(userInput[1]));
                    dukeInput(tasks.get(tasks.size() - 1), tasks.size() - 1);
                    taskCounter++;
                    taskList.writeTask(tasks.get(taskCounter - 1).toString());
                    break;
                case "delete":
                    taskList.deleteTask(tasks.get(Integer.parseInt(userInput[1]) - 1).toString());
                    dukeDelete(tasks, userInput[1]);
                    taskCounter--;
                    break;

            }

        }
    }
}
