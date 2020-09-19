import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int countTasks = 0;

    public static void main(String[] args) {
        String logo = "  ___    *   *    ____        *        \n"
                        + "|  _  | | | | |  |  _ \\ _   _| | _____ \n"
                        + "| |_| | | |_| |  | | | | | | | |/ / _ \\\n"
                        + "|  _  | |  _  |  | |_| | |_| |   <  __/\n"
                        + "| | | | | | | |  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        int exit = 0;

        greet();

        while(exit <= 0) { // If exit code is 0, continue
            String input = in.nextLine();
            exit = call(input.trim());
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

    public static int call(String input) {
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
                case "todo":
                    text = readInputParameter(args, null);
                    addTask(new Todo(text));
                    break;
                case "deadline":
                    text = readInputParameter(args, "/by");
                    String by = readSlashParameter(args, "/by");
                    addTask(new Deadline(text, by));
                    break;
                case "event":
                    text = readInputParameter(args, "/at");
                    String at = readSlashParameter(args, "/at");
                    addTask(new Event(text, at));
                    break;
                case "bye":
                    bye();
                    return 1;
                default:
                    // Do nothing
            }
        }
        return 0;
    }

    private static String readInputParameter(String[] args, String until) {
        String value = "";
        int index = args.length;
        if (until != null && until.isBlank()) {
            index = indexOf(args, until);
        }
        for(int i = 1; i < args.length && i < index; i++) { // add strings between command to until
            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static String readSlashParameter(String[] args, String param) {
        String value = "";
        int index = indexOf(args, param);
        for(int i = index+1; i < args.length; i++) { // add strings between slash to end of args
            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static int indexOf(Object[] arr, Object o) {
        int index = -1;
        if(arr != null)  {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == o) {
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

    public static void doneTasks(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Power la! I've marked this task(s) as done:");
        for (int i = 1; i < args.length; i++) { // Skip first: command

            try {

                int intTask = Integer.parseInt(args[i]);

                if (intTask < MAX_TASKS && tasks[intTask] != null) { // Has task at list index
                    Task t = tasks[intTask];

                    t.markAsDone();

                    // Print done task
                    System.out.printf("       %s %s\n", t.getStatusIcon(), t);
                }

            } catch (NumberFormatException ex) {
                // Do nothing
            }

        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(Task t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        if (countTasks < MAX_TASKS) {
            //Task t = new Task(description);
            tasks[countTasks++] = t;
            //echo("added: " + t);
            System.out.printf("      %s\n", t);
        } else {
            errorTaskFull();
        }
        System.out.printf("    Now you have %d tasks in the list.\n", countTasks);
        System.out.println("    ____________________________________________________________");
    }
    public static void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for(int i = 0; i < countTasks; i++) {
            Task t = tasks[i];
            System.out.printf("     %d.%s\n", i+1, t);
        }
        System.out.println("    ____________________________________________________________");
    }
}