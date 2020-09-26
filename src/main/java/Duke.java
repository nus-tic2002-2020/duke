import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/

        /* A greeting message */

        String logo = " ____  _      ,-.___,-.     \n"
                     + "|  _ \\|_|     \\_/_ _\\_/ \n"
                     + "| |_| | |       )O_O(      \n"
                     + "|  __/| |      { (_) }     \n"
                     + "|_|   |_|       `-^-'      \n";

        System.out.println("Hello from\n" + logo + "I'm Pi,");
        System.out.println("What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];       //set a Task array
      
        int numberOfTask = 0;
        input = in.nextLine();

        while(!input.equals("bye")) {
            String[] inputs = input.split(" ");

            if (input.equals("list")){
                printList(tasks,numberOfTask);
            }else if (input.contains("done")){
                int finishedTask = Integer.valueOf(inputs[1]);
                tasks[finishedTask - 1].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[finishedTask - 1].getTaskListInfo());

            }else{
                tasks[numberOfTask] = new Task(input);
                numberOfTask++;

                System.out.println("added: " + input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }


    /*print out the user input from the list*/
    public static void printList(Task[] tasks, int numberOfTask) {
        System.out.println("Here are the tasks in your list:");
        if(numberOfTask == 0){
            System.out.println("Currently your list is empty.");
        }

        for (int i = 0; i < numberOfTask; i++ ) {
            System.out.println((i + 1) + ". " + tasks[i].getTaskListInfo());
        }
    }


}
