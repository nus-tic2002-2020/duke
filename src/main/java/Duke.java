import java.util.Scanner;

public class Duke {
    /* initial the variables */
//    private static Task[] tasks = new Task[100];
//    private static int numberOfTask = 0;

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

        System.out.println("Hello,\n" + logo + "I'm Pi,\n" + "What can I do for you?");


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
                //tasks[numberOfTask] = new Task(input);
                addTask(input, inputs, tasks, numberOfTask);
                numberOfTask++;

                System.out.println("Now you have " + numberOfTask + " task(s) in the list.");

                //System.out.println("added: " + input);
            }
              input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }


    /*Add new Task*/
    public static void addTask(String userInput,String[] inputs, Task[] tasks, int numTasks){

        if (inputs[0].startsWith("deadline")) {
            int findPosition = userInput.indexOf("/by");
            String taskName = userInput.substring(9, findPosition);
            String taskTime = userInput.substring(findPosition + 4, userInput.length());
            tasks[numTasks] = new Deadline(taskName, taskTime);
            //addTask(new Deadline(taskName, taskTime));
        }else if(inputs[0].startsWith("event")){
            int findPosition = userInput.indexOf("/at");
            String taskName = userInput.substring(6, findPosition);
            String taskTime = userInput.substring(findPosition + 4, userInput.length());
            tasks[numTasks] = new Event(taskName, taskTime);

        }else{
            /* ToDo task */
            String taskName = userInput.substring(5);
            tasks[numTasks] = new ToDo(taskName);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[numTasks].getTaskListInfo());
        
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
