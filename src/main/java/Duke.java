import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  "  ___    *   *    ____        *        \n"
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
            exit = task(input);
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

    public static int task(String task) {
        switch(task) {
            case "bye":
                bye();
                return 1;
            default:
                echo(task);
                return 0;
        }
    }

    public static void echo(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message);
        System.out.println("    ____________________________________________________________");
    }
}
