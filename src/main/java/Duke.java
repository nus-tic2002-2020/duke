import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Duke {

    public static void greet() {
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("   Bye. Hope to see you again soon!");
    }

    public static void command() {

        String[] list = new String[10]; // create list!
        ArrayList<Task> tasks = new ArrayList<Task>(); //change to Array
        int pointer = 0;

        Scanner in = new Scanner(System.in);
        String line;
        //printLine();
        String endconvo = "bye";


        while (true) {

            try {

                line = in.nextLine();
                if (line.equals(endconvo)) { //if user enter bye
                    bye();
                    printLine();
                    return;

                } else if (line.equals("list")) {  //number of list

                    System.out.println("   Here are the tasks in your lists: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString()); //shows tasklist

                    }//printLine();

                } else if (line.contains("done")) {

                    int j = Integer.parseInt(line.substring(5)) - 1; //refer to which list is done and mark it
                    Task currenttask = tasks.get(j);
                    currenttask.markDone();
                    System.out.println("   Nice! I've marked this task as done:\n " + currenttask.toString());

                } else if (line.contains("delete")) {

                    int delete = Integer.parseInt(line.substring(7)) - 1; //delete
                    Task currenttask = tasks.get(delete);
                    tasks.remove(delete);

                    System.out.println("   Noted. I've removed this task:\n " + currenttask.toString());
                    System.out.println("   Now you have " + (tasks.size()) + " tasks in the list.");
                    pointer++;

                } else if (line.contains("todo")) { //try to combine all three classes

                    tasks.add(new ToDo(line.substring(5)));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
                    pointer++;
                    //System.out.println("added: " + line);

                } else if (line.contains("event")) {

                    int separator = line.indexOf('/');

                    String dateAndTime = line.substring(separator + 4);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                    tasks.add(new Event(line.substring(6, separator - 1), dateTime));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
                    pointer++;



                } else if (line.contains("deadline")) {

                    int separator = line.indexOf("/");

                    String dateAndTime = line.substring(separator + 4);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                    tasks.add(new Deadline(line.substring(9, separator - 1), dateTime));


                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
                    pointer++;


                } else {

                    System.out.println("   added: " + line); //add to the list
                    Task newtask = new Task(line);
                    newtask.description = line;
                    //tasks.get(pointer) = newtask;
                    pointer++;

                }


            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");

            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date and time format\n"
                        + "Please enter date and time as 'dd/MM/yyyy HHmm'");
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
