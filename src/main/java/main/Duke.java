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
        //Task[] task = new Task[100];
        ArrayList<Task> task = new ArrayList<Task>();
        String input = "";
        int count = 0;
        //so long input is not bye
        while (!input.equals("bye")) {
            try {
                //get user input
                input = sc.nextLine();
                //split into array
                String[] arrValue;
                arrValue = input.split(" ");
                if (input.equals("list")) {
                    //loop everything in the array
                    System.out.println("_______________________________________________");
                    for (int i = 0; i < count; i++) {
                        System.out.println(i+1 + ". " + task.get(i));
                    }
                    System.out.println("_______________________________________________");
                } else if (arrValue[0].equals("done")) {
                    if (arrValue.length < 2) {
                        throw new EmptyDescriptionException("OOOPS!!! The description of a done cannot be empty");
                    }
                    int index = Integer.parseInt(arrValue[1]);
                    task.get(index-1).markAsDone();
                    System.out.println("_______________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("\t[" + task.get(index-1).getStatusIcon() + "] " + task.get(index-1));
                    System.out.println("_______________________________________________");
                } else if (arrValue[0].equals("todo")) {
                    if (arrValue.length < 2) {
                        throw new EmptyDescriptionException("OOOPS!!! The description of a todo cannot be empty");
                    }
                    String replaceString = input.replace("todo ", "");
                    ToDo t = new ToDo(replaceString, false);
                    task.add(t);
                    System.out.println("_______________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + task.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println("_______________________________________________");
                } else if (arrValue[0].equals("deadline")) {
                    if (arrValue.length < 2) {
                        throw new EmptyDescriptionException("OOOPS!!! The description of a deadline cannot be empty");
                    }
                    String replaceString = input.replace("deadline ", "");
                    String[] splitBy = replaceString.split(" /by ");
                    if(splitBy.length < 2)
                    {
                        throw new EmptyDescriptionException("OOOPS!!! The date of a event cannot be empty");
                    }
                    Deadline d = new Deadline(splitBy[0], false, splitBy[1]);
                    task.add(d);
                    System.out.println("_______________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + task.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println("_______________________________________________");
                } else if (arrValue[0].equals("event")) {
                    if (arrValue.length < 2) {
                        throw new EmptyDescriptionException("OOOPS!!! The description of a event cannot be empty");
                    }
                    String replaceString = input.replace("event", "");
                    String[] splitAt = replaceString.split(" /at ");
                    if (splitAt.length < 2) {
                        throw new EmptyDescriptionException("OOOPS!!! The date of a event cannot be empty");
                    }
                    else if(splitAt.length>=2) {
                        Event e = new Event(splitAt[0], false, splitAt[1]);
                        task.add(e);
                        System.out.println("_______________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + task.get(count));
                        count++;
                        System.out.println("Now you have " + count + " tasks in the list.");
                        System.out.println("_______________________________________________");
                    }
                    else
                    {
                        throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                else {

                    Task t = new Task(input, false);
                    task.add(t);
                    System.out.println("added: " + task.get(count));
                    count++;
                }

            } catch (EmptyDescriptionException | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Bye. Hope to see you again soon !");
    }
}