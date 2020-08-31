import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;

    private static String[] tasks = new String[MAX_TASKS];
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
            exit = task(input.trim());
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

    public static int task(String task) {
        if (!task.isBlank()) {
            switch (task) {
                case "list":
                    printTasks();
                    break;
                case "bye":
                    bye();
                    return 1;
                default:
                    //echo(task);
                    addTask(task);
            }
        }
        return 0;
    }

    public static void echo(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message);
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(String task) {
        if (countTasks < MAX_TASKS) {
            tasks[countTasks++] = task;
            echo("added: " + task);
        } else {
            errorTaskFull();
        }
    }
    public static void printTasks() {
        System.out.println("    ____________________________________________________________");
        for(int i = 0; i < countTasks; i++) {
            System.out.printf("     %d. %s\n", i+1, tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }
}