import java.util.Scanner;

public class Duke {
    static Scanner getInput = new Scanner(System.in);
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        greeting();
        ifBye();
    }

    public static void greeting(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    private static Task[] taskList = new Task[100]; //use Task file
    private static int numberOfTask = 0;

    //add
    public static void setTaskList(Task description){
        taskList[numberOfTask] = description;
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + taskList[numberOfTask].getDescription() );
        numberOfTask++;
        System.out.println("\tNow you have " + numberOfTask + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    //list
    public static void getTaskList(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i< numberOfTask; i++){
            System.out.println("\t" + (i+1) + ". " +taskList[i].getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void itIsDone(int thatTask){
        taskList[thatTask].setDone();
    }
    public static void ifBye(){
        String input = getInput.nextLine();

        while (input != null) { //only "bye" will end the loop
            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                getTaskList();
                input = getInput.nextLine();
            } else if (input.split(" ")[0].equals("done") ){
                String part2 = input.split(" ")[1];
                //itIsDone(Integer.parseInt(input.split(" ")[1])-1); //have to minus 1, because added 1 before
                itIsDone(Integer.parseInt(part2)-1); //easier to see
                input = getInput.nextLine();
            }else if (input.split(" ")[0].equals("todo") ){
                setTaskList(new Todo(input.substring(4)));
                input = getInput.nextLine();
            }else if (input.split(" ")[0].equals("deadline") ){
                setTaskList(new Deadline(input.substring(8, input.indexOf("by")-2), input.substring(input.indexOf("by")+3)));
                input = getInput.nextLine();
            }else if (input.split(" ")[0].equals("event") ){
                setTaskList(new Event(input.substring(5, input.indexOf("at")-2), input.substring(input.indexOf("at")+3)));
                input = getInput.nextLine();
            }
        }
    }
}