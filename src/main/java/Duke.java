import java.util.Scanner;

public class Duke {
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
        numberOfTask++;
    }

    //list
    public static void getTaskList(){
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i< numberOfTask; i++){
            System.out.println("\t" + (i+1) + ". ["  +taskList[i].getStatusIcon()+ "]" +taskList[i].getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void itIsDone(int thatTask){
        taskList[thatTask].setDone();
    }

    public static void ifBye(){
        Scanner getInput = new Scanner(System.in);
        String input = getInput.nextLine();
        //next() place cursor within same line after reading input. nextLine() reads input including space between the words till\n

        while (input != null) {
            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                getTaskList();
                break;
            } else if (input.split(" ")[0].equals("done") ){
                String part2 = input.split(" ")[1];
                //itIsDone(Integer.parseInt(input.split(" ")[1])-1); //have to minus 1, because added 1 before
                itIsDone(Integer.parseInt(part2)-1); //easier to see
                break;
            }
            //none of the above
            setTaskList(new Task(input));
            System.out.println("\t____________________________________________________________");
            System.out.print("\tadded: ");
            System.out.println(input);
            System.out.println("\t____________________________________________________________");

            input = getInput.nextLine();
        }
    }
}