package duke.command;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import duke.task.*;

public class Duke {
    private static final int MAX_TASKS = 100;

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = "  ___    *   *    ____        *\n"
                        + "|  _  | | | | |  |  _ \\ _   _| | _____\n"
                        + "| |_| | | |_| |  | | | | | | | |/ / _ \\\n"
                        + "|  _  | |  _  |  | |_| | |_| |   <  __/\n"
                        + "| | | | | | | |  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        boolean exit = false;

        greet();

        while(!exit && in.hasNextLine()) { // If no error, continue
            String input = in.nextLine();
            try {
                exit = call(input.trim());
            } catch (DukeException e) {
                e.printError();
            }
        }
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Oei! I'm Ah Duke");
        System.out.println("    What can I do for you leh?");
        System.out.println("    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void errorTaskFull() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Peiseh, my task list is full!");
        System.out.println("    ____________________________________________________________");
    }

    public static boolean call(String input) throws DukeException {
        String[] args = input.split(" ");
        String text = "";
        if (args.length > 0 && !args[0].isBlank()) {
            String command = args[0];
            switch (command) {
                case "list":
                    printTasks();
                    break;
                case "done":
                    doneTasks(args);
                    break;
                case "delete":
                    deleteTasks(args);
                    break;
                case "todo":
                    text = readInputParameter(args, null);
                    if (text.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    addTask(new Todo(text));
                    break;
                case "deadline":
                    text = readInputParameter(args, "/by");
                    if (text.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String by = readSlashParameter(args, "/by");
                    if (by.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    addTask(new Deadline(text, by));
                    break;
                case "event":
                    text = readInputParameter(args, "/at");
                    if (text.isBlank()) {
                        throw new DukeException("The description of an event cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String at = readSlashParameter(args, "/at");
                    if (at.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    addTask(new Event(text, at));
                    break;
                case "bye":
                    bye();
                    return true;
                default:
                    throw new DukeException("Sorli, but I dunno what that means :-(",
                            DukeException.DukeError.UNKNOWN_COMMAND);
            }
        }
        return false;
    }

    private static String readInputParameter(String[] args, String until) {
        String value = "";
        int index = args.length;
        if (until != null && !until.isBlank()) {
            int new_index = indexOf(args, until);
            index = new_index >= 0 ? new_index : index; // If new_index is negative, revert to use args length
        }
        for(int i = 1; i < args.length && i < index; i++) { // add strings between command to until
            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static String readSlashParameter(String[] args, String param) throws DukeException {
        String value = "";
        int index = indexOf(args, param);
        if (index < 0) {
            throw new DukeException(String.format("Cannot find required %s in args. (index = %d)!", param, index),
                    DukeException.DukeError.MISSING_ARGUMENT);
        }
        for(int i = index+1; i < args.length; i++) { // add strings between slash to end of args
            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static int indexOf(Object[] arr, Object o) {
        int index = -1;
        if (arr != null)  {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i].equals(o)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public static void echo(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message);
        System.out.println("    ____________________________________________________________");
    }

    public static void deleteTasks(String[] args) throws DukeException {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Song la! Lim peh help you remove this task(s):");

        ArrayList<Task> tasksToRemove = new ArrayList<Task>();
        ArrayList<Integer> errorIndices = new ArrayList<Integer>();

        // Create a collection of index to delete
        for (int i = 1; i < args.length; i++) { // Skip first: "delete" command

            try {
                int intTask = Integer.parseInt(args[i]) - 1;
                if (intTask < MAX_TASKS && tasks.size() > intTask) { // Has task at list index
                    Task t = tasks.get(intTask);
                    tasksToRemove.add(t);
                } else {
                    errorIndices.add(intTask); // add index to error collection
                }
            } catch (NumberFormatException ex) {
                // Do nothing, skip number
            }

        }

        // Print deleted task
        for (Task t: tasksToRemove) {
            System.out.printf("       %s\n", t.toString());
        }

        // Delete all tasks in collection
        tasks.removeAll(tasksToRemove);

        System.out.printf("    Now you have %d tasks in the list.\n", tasks.size());

        if (errorIndices.size() > 0) { // raise exception for wrong index
            throw new DukeException(String.format("Err... cannot find these task(s) leh - %s", errorIndices.toString()),
                    DukeException.DukeError.TASK_NOT_FOUND);
        }

        System.out.println("    ____________________________________________________________");
    }

    public static void doneTasks(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Power la! I've marked this task(s) as done:");
        for (int i = 1; i < args.length; i++) { // Skip first: command

            try {

                int intTask = Integer.parseInt(args[i])-1;

                if (intTask < MAX_TASKS && tasks.size() > intTask) { // Has task at list index
                    Task t = tasks.get(intTask);

                    t.markAsDone();

                    // Print done task
                    System.out.printf("       %s\n", t.toString());
                }

            } catch (NumberFormatException ex) {
                // Do nothing, skip number
            }

        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(Task t) throws DukeException {
        boolean ok = true;
        int intCount = tasks.size();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        if (intCount < MAX_TASKS) {
            //tasks[countTasks++] = t;
            tasks.add(t);
            intCount = tasks.size();
            //countTasks++;
            System.out.printf("      %s\n", t);
        } else {
            ok = false;
        }
        System.out.printf("    Now you have %d tasks in the list.\n", intCount);
        System.out.println("    ____________________________________________________________");

        if (!ok) {
            throw new DukeException("Peiseh, my task list is full!", DukeException.DukeError.MEMORY_FULL);
        }
    }
    public static void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        int intCount = tasks.size();
        for(int i = 0; i < intCount; i++) {
            Task t = tasks.get(i);
            System.out.printf("     %d.%s\n", i+1, t);
        }
        System.out.println("    ____________________________________________________________");
    }
}