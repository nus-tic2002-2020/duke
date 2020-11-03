package DukeUtils;

import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.UI;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


/**
 * Add new reminder, event, todo, deadline into list
 * Ability to mark done and delete task from list
 * Date printed out will be in localdatetime format when user input for e.g 12/12/2020
 */
public class Duke {

    protected static TaskList tasks;
    public TaskList tasklist;
    public static UI ui;

    public static void main(String[] args) throws DukeException, FileNotFoundException {
     
        tasks = new TaskList();
        
        Storage.loadFile();
        ui = new UI();
        ui.showWelcome();
        Scanner scn = new Scanner(System.in);

        String[] list = new String[10];
        int pointer = 0;

        Scanner in = new Scanner(System.in);
        String line;
        String endconvo = "bye";

        while (true) {
            try {
                line = in.nextLine();
                if (line.equals(endconvo)) {
                    ui.showClosing();
                    //printLine();
                    return;
                } else if (line.equals("list")) {
                    System.out.println("   Here are the tasks in your lists: ");
                    for (int i = 0; i < tasks.taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                } else if (line.equals("reminder")) {
                    System.out.println("   Here are the tasks in your reminder list: ");
                    char r = 'R';
                    for (int i = 0; i < tasks.taskList.size(); i++) {
                        System.out.println(tasks.get(i).toString().charAt(2));
                        if (Character.compare(tasks.get(i).toString().charAt(1),r)==0){
                            System.out.println(i + 1 + ". " + tasks.get(i).toString());
                        }

                    }
                } else if (line.contains("done")) {
               int i = Integer.parseInt(line.substring(5)) - 1;
                    Task currentTask = tasks.taskList.get(i);
                    currentTask.markDone();
                    System.out.println("   Nice! I've marked this task as done:\n " + currentTask.toString());
                } else if (line.contains("delete")) {
                    int delete = Integer.parseInt(line.substring(7)) - 1;
                    Task currentTask = tasks.taskList.get(delete);
                    tasks.removeTask(delete);
                    pointer++;
                } else if (line.contains("todo")) {
                    Task todo = new ToDo(line.substring(5));
                    tasks.addTask(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.taskList.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.taskList.size()) + " tasks in the list.");
                    pointer++;
                } else if (line.contains("event")) {
                    int separator = line.indexOf('/');
                    String dateAndTime = line.substring(separator + 4);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                    Task event = new Event(line.substring(6, separator - 1), dateTime);
                    tasks.addTask(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.taskList.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.taskList.size()) + " tasks in the list.");
                    pointer++;
                } else if (line.contains("reminder")) {
                    int separator = line.indexOf('/');
                    String dateAndTime = line.substring(separator + 4);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                    Task reminder = new Reminder(line.substring(9, separator - 1), dateTime);
                    tasks.addTask(reminder);
                    System.out.println("Got it. I've added this reminder:");
                    System.out.println(tasks.get(tasks.taskList.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.taskList.size()) + " reminder in the list.");
                    pointer++;
                } else if (line.contains("deadline")) {
                    int separator = line.indexOf("/");
                    String dateAndTime = line.substring(separator + 4);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                    Task deadline = new Deadline(line.substring(9, separator - 1), dateTime);
                    tasks.addTask(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.taskList.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.taskList.size()) + " tasks in the list.");
                    pointer++;
                } else if (line.equals("save")) {
                    save();
                } else {
                    Task newtask = new Task(line);
                    //newtask.description = line;
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

    /**
     *
     * @throws FileNotFoundException
     * Save tasks to txt file in String for
     */
    static void save() throws FileNotFoundException {
        String list = "";
        for (int i = 0; i < tasks.taskList.size(); i++) {
            list += tasks.get(i).toSaveString() + "\n";
        }
        Storage.writeToFile(list);
        System.out.println("Saved to file!");
    }

}





