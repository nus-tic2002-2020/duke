import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int i=0;
        Task task[] = new Task[100];
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
                System.out.println("Here are the tasks in your list: ");
                for(int z=0; z<i; z++)
                {
                    System.out.println(z+1+"."+"["+task[z].getStatusIcon()+"]"+task[z].description);
                }
                //System.out.println(command);
            }
            else if(command.startsWith("done"))
            {
                String[] optionInput = command.split(" ");
                int option = Integer.parseInt(optionInput[1]);
                task[option-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("["+task[option-1].getStatusIcon()+"]"+task[option-1].description);
            }
            else
            {
                task[i]= new Task(command);
                System.out.println(task[i].description);
                i++;
            }
        }

    }

}
