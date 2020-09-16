import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int i=0;
        String[] list = new String[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(true) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("What can I do for you?");
            String command = input.nextLine();  // Read user input
            if (command.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (command.equals("list"))
            {
                for(int z=0; z<i; z++)
                {
                    System.out.println(z+1+": "+list[z]);
                }
                //System.out.println(command);
            }
            else
            {
                list[i]=command;
                System.out.println(command);
                i++;
            }
        }
    }
}
