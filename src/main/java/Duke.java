import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Please Enter Command: ");
            String command = input.nextLine();  // Read user input
            if (command.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else{
                System.out.println(command);
            }
        }
    }
}
