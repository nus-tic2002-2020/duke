package main;
import java.util.ArrayList;
import java.util.Scanner;
//new branch named addintro 20200920

public class Duke {
    //private static final String OUTPUT_DELIMITER = "\\|";
    //private static final String INPUT_DELIMITER = " ";
    //ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        Task[] task = new Task[100];
        String input = "";
        int count = 0;
        //so long input is not bye
        while (!input.equals("bye"))
        {
            //get user input
            input = sc.nextLine();
            //split into array
            String[] arrValue = input.split(" ");
            if (input.equals("list"))
            {
                //loop everything in the array
                for (int i = 0; i < count; i++)
                {
                    System.out.println(i+1 +".[" +task[i].getStatusIcon() +"] " + task[i]);
                }
            }
            else if (arrValue[0].equals("done"))
            {
                int index = Integer.parseInt(arrValue[1]);
                task[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t["+task[index-1].getStatusIcon() + "] "+ task[index-1]);
            }
            else {
                //not done
                task[count] = new Task(input,false);
                System.out.println("added: " + task[count]);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon !");
    }
}