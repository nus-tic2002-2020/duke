import java.util.Scanner;


public class Duke {

    public static void greet() {
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void command() {
        String[] list = new String[5]; // create list!
        Task[] tasks = new Task[5];
        int pointer = 0;

        Scanner in = new Scanner(System.in);
        String line;
        //printLine();
        String endconvo = "bye";

        while (true) {
            line = in.nextLine();
            if (line.equals(endconvo)) { //if user enter bye
                bye();
                printLine();
                return;

            } else if (line.equals("list")) {  //number of list
                System.out.println("Here are the tasks in your lists: ");
                for (int i = 0; i < pointer; i++) {
                    System.out.println(i + 1 + ". " + "[" +tasks[i].symbols() + "] "
                            + tasks[i].description); //shows tasklist

}
                }else if (line.contains("done")){
                int j = Integer.parseInt(line.substring(5)) - 1; //refer to which list is done and mark it
                    Task currenttask = tasks[j];
                    currenttask.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + currenttask.symbols() + "] " + currenttask.description);


                } else {
                System.out.println("added: " + line); //add to the list
                list[pointer] = line;
                Task newtask = new Task(line);
                newtask.description = line;
                tasks[pointer] = newtask;
                pointer++;
            }

        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");

        greet();
        printLine();
        command();

    }
}
