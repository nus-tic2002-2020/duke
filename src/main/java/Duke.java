import java.util.Scanner;

public class Duke {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void line(){
        System.out.print(ANSI_BLUE);
        for(int i = 0; i < 50; i++) System.out.print("_");
        System.out.print(ANSI_RESET);
        System.out.print("\n");
    }

    public static void dukeInput(Task task, int taskCounter){
        line();
        System.out.println(ANSI_YELLOW + "   Got it. I've added this task: \n"
                + "     " + task
                + "\n   Now you have " + (taskCounter + 1) + " tasks in the list." + ANSI_RESET);
        line();
    }

    public static void dukeList(Task[] tasks, int taskCounter){
        line();
        System.out.println(ANSI_YELLOW + "   Here are the tasks in your list:" + ANSI_RESET);
        for(int i = 0; i < taskCounter; i++){
            System.out.println(ANSI_YELLOW + "   " + String.valueOf(i+1) + "." + tasks[i] + ANSI_RESET);
        }
        line();
    }

    public static void dukeDone(Task task){
        line();
        task.markAsDone();
        System.out.println(ANSI_YELLOW + "   This task's status has been updated:\n"
                + "   " + task + ANSI_RESET);
        line();
    }

    public static void dukeInvalid(String s){

        line();

        switch (s){
            case "deadline":
                System.out.println(ANSI_RED + "   Invalid input, please provide /by schedule." + ANSI_RESET);
                break;
            case "event":
                System.out.println(ANSI_RED + "   Invalid input, please provide /at schedule." + ANSI_RESET);
                break;
            default:
                System.out.println(ANSI_RED + "   Invalid input, please type again." + ANSI_RESET);
        }

        line();
    }

    public static void main(String[] args) {
        int taskCounter = 0;
        Task[] tasks = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(ANSI_YELLOW + logo + "Hello! I'm Duke\n" + "What can I do for you?" + ANSI_RESET);

        while(true){
            Scanner input = new Scanner(System.in);
            String[] userInput = input.nextLine().split("\\s", 2);

            switch (userInput[0].toLowerCase()){
                case "bye":
                    line();
                    System.out.println(ANSI_YELLOW + "   Bye. Hope to see you again soon!" + ANSI_RESET);
                    line();
                    return;
                case "list":
                    dukeList(tasks, taskCounter);
                    break;
                case "done":
                    dukeDone(tasks[Integer.parseInt(userInput[1]) - 1]);
                    break;
                case "todo":
                    tasks[taskCounter] = new Todo(userInput[1]);
                    dukeInput(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                case "deadline":
                    if(!userInput[1].contains("/by")){
                        dukeInvalid("deadline");
                        break;
                    }
                    tasks[taskCounter] = new Deadline(userInput[1]);
                    dukeInput(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                case "event":
                    if(!userInput[1].contains("/at")){
                        dukeInvalid("event");
                        break;
                    }
                    tasks[taskCounter] = new Event(userInput[1]);
                    dukeInput(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                default:
                    dukeInvalid("exit");
            }

        }
    }
}
