import java.util.Scanner;

public class Duke {

    public static void printUserInput(){
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine();
        if(user_input.equals("bye")){
            System.out.println("\t____________________________________________________________");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t____________________________________________________________");
            System.exit(0);
        }
        else{
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + user_input);
            System.out.println("\t____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        while(true){
            printUserInput();
        }
    }
}
