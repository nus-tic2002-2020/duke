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

    public static void ifBye(){
        Scanner getInput = new Scanner(System.in);
        String input = getInput.next();

        while (input != null) {
            if (input.equals("bye")){
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }
            //else just parrot
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
            input = getInput.next();
        }
    }
}
