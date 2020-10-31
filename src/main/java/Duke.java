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

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("D:\\ZaZa's Programming\\TIC2002\\Clone\\data\\duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        Scanner scn = new Scanner(System.in);

        //public static void greet() {
        // System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");
        //}

        //  public static void printLine() {
        //  System.out.println("    ____________________________________________________________");
        // }

        // public static void bye() {
        //     System.out.println("   Bye. Hope to see you again soon!");
        // }

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
                    ui.showClosing();
                    //printLine();
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

                    try {
                        storage.updateTaskInFile(j + 1);
                    } catch (IOException e) {
                        System.out.println("Can't update task in the file");
                    }

                    System.out.println("   Nice! I've marked this task as done:\n " + currenttask.toString());

                } else if (line.contains("delete")) {

                    int delete = Integer.parseInt(line.substring(7)) - 1; //delete
                    Task currenttask = tasks.get(delete);
                    tasks.remove(delete);

                    try {
                        storage.deleteFromFile(delete + 1);
                        System.out.println("   Noted. I've removed this task:\n " + currenttask.toString());
                        System.out.println("   Now you have " + (tasks.size()) + " tasks in the list.");
                    } catch (IOException e) {
                        System.out.print("No saved files");
                    }

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

                    try {
                        storage.addToFile(tasks.get(tasks.size() - 1).toString());
                    } catch (IOException e) {
                        System.out.println("Cannot save new task in file");
                    }

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

                    try {
                        storage.addToFile(tasks.get(tasks.size() - 1).toString());
                    } catch (IOException e) {
                        System.out.println("Cannot save new task in file");
                    }


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

}





