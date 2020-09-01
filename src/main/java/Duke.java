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
        String[] list = new String[10]; // create list!
        int index = 0;

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
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }

            } else {
                System.out.println("added: " + line); //add to the list
                list[index] = line;
                index++;
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
