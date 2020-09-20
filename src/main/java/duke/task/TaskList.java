package duke.task;

import duke.command.Undo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is used for evaluating the actions of the multiple user commands such as add / delete tasks etc.
 * <br> It contains the following approved actions:
 * <ol>
 *     <li>List: Allows to list all the tasks inside TaskList</li>
 *     <li>Bye: Exits the Task Manager</li>
 *     <li>Done: Marks a task as completed</li>
 *     <li>Delete: Deletes a task from the TaskList</li>
 *     <li>Todo / Event / Deadline: Adds one of the following tasks into the TaskList</li>
 *     <li>Occurrence: Lists which tasks fall on a specified date</li>
 * </ol>
 */

public class TaskList {
    protected static int count;
    protected static ArrayList<Task> list;

    /**
     * This method initializes the TaskList ArrayList.
     * @param list Provides the list of tasks
     * @param count Provides how many tasks have been added
     */

    public TaskList(ArrayList<Task> list, int count) {
        TaskList.count = count;
        TaskList.list = list;
    }

    /**
     * This method is to retrieve the updated TaskList.
     * @return Returns the latest updated TaskList.
     */

    public static ArrayList<Task> UpdatedList() { return list;}

    /**
     * This method provides a list of all the tasks currently inside the TaskList.
     * @throws DukeException Throws an error if the list is empty.
     */

    public static void List() throws DukeException {
        if (count == 0) throw new DukeException("There are no items currently in the list\n");
        System.out.println("Here are the tasks in your list:\n");
        int seq = 1;
        for (Task l : list) {
            System.out.println(seq + ". " + l);
            seq++;
        }
    }

    /**
     * This method helps to exit from the Task Manager.
     */

    public static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * This task helps to mark a task as completed.
     * @param line Provide details into which task is to be marked as completed.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Done(String line) throws DukeException {
        int m = line.toLowerCase().indexOf("done");
        String num = line.substring(m + 4).trim();
        if (num.length() < 1) throw new DukeException("Error: Please enter which task is done\n");
        int n = Integer.parseInt(num);
        if (n > count) throw new DukeException("Error: Please enter a valid task number\n");
        if (list.get(n - 1).getStatusIcon().equals("\u2713"))
            throw new DukeException("Error: Task has already been completed\n");
        else {
            System.out.println("Nice! I've marked this task as done:");
            list.get(n - 1).setStatus(true);
            System.out.println(list.get(n - 1));
            list = new ArrayList<>(list);
        }
    }

    /**
     * This task helps to delete a task.
     * @param line Provide details into which task is to be deleted.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Delete(String line) throws DukeException {
        int m = line.toLowerCase().indexOf("delete");
        String num = line.substring(m + 6).trim();
        if (num.length() < 1) throw new DukeException("Error: Please enter which task to be deleted\n");
        int n = Integer.parseInt(num);
        if (n > count) throw new DukeException("Error: Please enter a valid task number\n");
        else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(list.get(n - 1));
            list.remove(n - 1);
            count--;
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    /**
     * This method is to add a Todo task.
     * @param line Provides the description of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Todo(String line) throws DukeException {
        if (line.trim().length() < 5) throw new DukeException("Error: Description of task cannot be empty.\n");
        int m = line.toLowerCase().indexOf("todo");
        String description = line.substring(m + 4).trim();
        for (Task l : list) {
            if (l.description.equals(description))
                throw new DukeException("Error: Task has already been added previously\n");
        }
        list.add(new Todo(description));
        UpdateStatus();
    }

    /**
     * This method is to add a Deadline task.
     * @param line Provides the description of the task.
     * @param localDateTime Provides the scheduled Date / Time of the completion of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Deadline(String line, LocalDateTime localDateTime) throws DukeException {
        if (line.trim().length() < 9) throw new DukeException("Error: Description of task cannot be empty.\n");
        if (!line.contains("/")) throw new DukeException("Error: Please specify time.\n");
        int m = line.toLowerCase().indexOf("deadline");
        int n = line.indexOf('/');
        String description = line.substring(m + 8, n).trim();
        for (Task l : list) {
            if (l.description.equals(description))
                throw new DukeException("Error: Task has already been added previously\n");
        }
        list.add(new Deadline(description, localDateTime));
        UpdateStatus();
    }

    /**
     * This method is to add an Event task.
     * @param line Provides the description of the task.
     * @param localDateTime Provides the scheduled Date / Time of the completion of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Event(String line, LocalDateTime localDateTime) throws DukeException {
        if (line.trim().length() < 6) throw new DukeException("Error: Description of task cannot be empty.\n");
        if (!line.contains("/")) throw new DukeException("Error: Please specify time.\n");
        int m = line.toLowerCase().indexOf("event");
        int n = line.indexOf('/');
        String description = line.substring(m + 5, n).trim();
        for (Task l : list) {
            if (l.description.equals(description))
                throw new DukeException("Error: Task has already been added previously\n");
        }
        list.add(new Event(description, localDateTime));
        UpdateStatus();
    }

    /**
     * This method is to provide a list of all the tasks that have scheduled deadlines on a particular date.
     * @param localDate Provides the input for the tasks that are supposed to be completed on that particular date.
     */

    public static void Occurrence(String line, LocalDate localDate) throws DukeException {
        if (!line.contains("/")) { throw new DukeException("Error: Please specify time.\n"); }
        boolean match = false;
        System.out.println("Here are the tasks that fall within this date\n");
        for (Task l : list) {
            if (l.toString().contains("[D]")) {
                LocalDate target = (((Deadline) l).localDateTime).toLocalDate();
                if (target.equals(localDate)) {
                    System.out.println(l);
                    match = true;
                }
            }
            if (l.toString().contains("[E]")) {
                assert l instanceof Event;
                LocalDate target = (((Event) l).localDateTime).toLocalDate();
                if (target.equals(localDate)) {
                    System.out.println(l);
                    match = true;
                }
            }
        }
        if (!match) {
            System.out.print("Sorry no tasks fall on this day\n");
        }
    }

    /**
     * This method helps to list which task has been added to the TaskList and provide the count for the total number of tasks inside.
     */

    public static void UpdateStatus() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * This method helps undo the most recent command and print the Tasks in the TaskList before the last command was given.
     * <br> It also erases the previous change which was added to the History function.
     * @throws DukeException Throws an error if the user tries to undo before entering any input or when reached at the initial loaded list.
     */

    public static void Undo() throws DukeException {
        list = Undo.undo();
        count--;
        System.out.println("Undo completed.");
        if (list.size() == 0) {
            System.out.println("The list is now empty");
        }
        int seq = 1;
        for (Task l : list) {
            System.out.println(seq + ". " + l);
            seq++;
        }
    }

    /**
     * This method helps to take a find keyword and displays any tasks in the TaskList that contain that task description.
     * @param line Contains the user-input for the tasks description that he wants to find.
     */

    public static void Find(String line) {
        int seq = 1;
        for (Task l : list) {
            if(l.description.toLowerCase().contains(line.toLowerCase())) {
                System.out.println(seq + "." + l);
                seq++;
            }
        }
    }
}
