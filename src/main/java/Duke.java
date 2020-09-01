import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        printLine();
        String endconvo = "bye";

        if (line.equals(endconvo)) {
            bye();
            printLine();
            return;

        } else {
            System.out.println(line);
            printLine();
            echo();
        }

    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");

        greet();
        printLine();
        echo();

    }
}
