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
            String[] arrValue;
            arrValue = input.split(" ");
            if (input.equals("list"))
            {
                //loop everything in the array
                System.out.println("_______________________________________________");
                for (int i = 0; i < count; i++)
                {
                    System.out.println(i+1 +".[" +task[i].getStatusIcon() +"] " + task[i]);
                }
                System.out.println("_______________________________________________");
            }
            else if (arrValue[0].equals("done"))
            {
                int index = Integer.parseInt(arrValue[1]);
                task[index-1].markAsDone();
                System.out.println("_______________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t["+task[index-1].getStatusIcon() + "] "+ task[index-1]);
                System.out.println("_______________________________________________");
            }
            else if (arrValue[0].equals("todo"))
            {
                String replaceString = input.replace("todo ", "");
                task[count] = new ToDo(replaceString, false);
                System.out.println("_______________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + task[count]);
                count ++;
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else if (arrValue[0].equals("deadline"))
            {
                String replaceString = input.replace("deadline ","");
                String [] splitBy = replaceString.split(" /by ");
                task[count] = new Deadline(splitBy[0], false, splitBy[1]);
                System.out.println("_______________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + task[count]);
                count ++;
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else if (arrValue[0].equals("event"))
            {
                String replaceString = input.replace("event", "");
                String [] splitAt = replaceString.split(" /at ");
                task [count] = new Event(splitAt[0], false, splitAt[1]);
                System.out.println("_______________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + task[count]);
                count ++;
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else {

                task[count] = new Task(input,false);
                System.out.println("added: " + task[count]);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon !");
    }
}