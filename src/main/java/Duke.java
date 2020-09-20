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

        String[] tasks = new String[100];
        int numberOfTask = 0;

        input = in.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            if (input.equals("list")){
                printList(tasks,numberOfTask);
            }else{
                tasks[numberOfTask] = input;
                numberOfTask++;

                System.out.println("added: " + input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }


    /*print out the user input from the list*/
    public static void printList(String[] tasks, int numberOfTask) {
        for (int i = 0; i < numberOfTask; i++ ) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }


}
