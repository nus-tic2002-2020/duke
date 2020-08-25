import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            if(input.equals("bye")) {
                System.out.println("\t--------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t--------------------------");
                break;
            } else {
                System.out.println("\t--------------------------");
                System.out.println("\t" + input);
                System.out.println("\t--------------------------");
            }
        }
    }
}
