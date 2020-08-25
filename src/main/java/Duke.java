import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t--------------------------");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t--------------------------");

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            if(input.equals("bye")) {
                System.out.println("\t--------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t--------------------------");
                break;
            } else if(input.equals("list")) {
                for(int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ": " + tasks[i].toString());
                }
            } else {
                System.out.println("\t--------------------------");
                tasks[taskCount] = input;
                System.out.println("\tadded: " + tasks[taskCount]);
                taskCount++;
                System.out.println("\t--------------------------");
            }
        }
    }
}
