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
        if (args.length > 0 && !args[0].isBlank()) {
            String command = args[0];
            switch (command) {
                case "list":
                    printTasks();
                    break;
                case "done":
                    doneTasks(args);
                    break;
                case "bye":
                    bye();
                    return 1;
                default:
                    addTask(input);
            }
        }
        return 0;
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
            int intTask = Integer.parseInt(args[i]); //TODO: Exception handling

            if (intTask < MAX_TASKS && tasks[intTask] != null) { // Has task at list index
                Task t = tasks[intTask];

                t.markAsDone();

                // Print done task
                System.out.printf("       %s %s\n", t.getStatusIcon(), t);
            }

        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(String description) {
        if (countTasks < MAX_TASKS) {
            Task t = new Task(description);
            tasks[countTasks++] = t;
            echo("added: " + t);
        } else {
            errorTaskFull();
        }
    }
    public static void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for(int i = 0; i < countTasks; i++) {
            Task t = tasks[i];
            System.out.printf("     %d.%s %s\n", i+1, t.getStatusIcon(), t);
        }
        System.out.println("    ____________________________________________________________");
    }
}