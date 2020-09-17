import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
                    System.out.println(z+1+"."+task[z].toString());
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
            else if(command.startsWith("todo"))
            {

                String[] descInput = command.split(" ",2);
                if(descInput.length<2)
                {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    //System.out.println("Please ensure that input is in correct format. (e.g. deadline [description] /by [date/time]");
                    //continue;
                }
                String desc = descInput[1];
                System.out.println("Got it. I've added this task: ");
                task[i]=new Todo(desc);
                System.out.println(task[i].toString());
                i++;
                if(i==1) {
                    System.out.println("Now you have " + i + " task in the list.");
                }
                else
                {
                    System.out.println("Now you have " + i + " tasks in the list.");
                }
                //System.out.println("["+task[i].getStatusIcon()+"]"+task[option-1].description);
            }
            else if(command.startsWith("deadline"))
            {
                if(command.contains(" /by ")==false)
                {
                    throw new DukeException("Please ensure that input is in correct format. (e.g. deadline [description] /by [date/time]");
                    //System.out.println("Please ensure that input is in correct format. (e.g. deadline [description] /by [date/time]");
                    //continue;
                }
                String[] descInput = command.split(" ",2);
                String[] desc = descInput[1].split(" /by ");
                System.out.println("Got it. I've added this task: ");
                task[i]=new Deadline(desc[0],desc[1]);
                System.out.println(task[i].toString());
                i++;
                if(i==1) {
                    System.out.println("Now you have " + i + " task in the list.");
                }
                else
                {
                    System.out.println("Now you have " + i + " tasks in the list.");
                }
                //System.out.println("["+task[i].getStatusIcon()+"]"+task[option-1].description);
            }
            else if(command.startsWith("event"))
            {
                if(command.contains(" /at ")==false)
                {
                    throw new DukeException("Please ensure that input is in correct format. (e.g. event [description] /at [date/time]");
                    //System.out.println("Please ensure that input is in correct format. (e.g. event [description] /at [date/time]");
                }
                String[] descInput = command.split(" ",2);
                String[] desc = descInput[1].split(" /at ");
                System.out.println("Got it. I've added this task: ");
                task[i]=new Event(desc[0],desc[1]);
                System.out.println(task[i].toString());
                i++;
                if(i==1) {
                    System.out.println("Now you have " + i + " task in the list.");
                }
                else
                {
                    System.out.println("Now you have " + i + " tasks in the list.");
                }
                //System.out.println("["+task[i].getStatusIcon()+"]"+task[option-1].description);
            }
            else
            {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                //task[i]= new Task(command);
                //System.out.println(task[i].description);
                //i++;
            }
        }

    }

}
