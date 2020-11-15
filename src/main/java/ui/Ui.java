package ui;

import tasks.Task;
import tasks.TaskList;
import tasks.Deadline;
import exception.DukeException;
import parser.Parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface with user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Displaying Pi logo and welcome message to user.
     */
    public void showLogo(){
        String logo = " ____  _      ,-.___,-.     \n"
                + "|  _ \\|_|     \\_/_ _\\_/ \n"
                + "| |_| | |       )O_O(      \n"
                + "|  __/| |      { (_) }     \n"
                + "|_|   |_|       `-^-'      \n";

        System.out.println("Hello,\n" + logo + "   I'm Pi,\n" + "   What can I do for you?");
        showLine();

    }

    /**
     * Constructor
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void showLine(){
        System.out.println("   ________________________");
    }

    /**
     * Show error message if cannot load or be found.
     *
     * @param message print error message.
     */
    public void showLoadingError(String message){
        System.out.println(message);
    }

    /**
     * Read user command.
     *
     * @return user command.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * To print out all the tasks in list.
     *
     * @param tasks a list of saved tasks.
     */
    public void printList(TaskList tasks) {
        boolean haveOverdueTasks = false;

        System.out.println("Here are the tasks in your list:");
        if (tasks.size() == 0){
            System.out.println("Currently your list is empty.");
        }

        for (int i = 0; i < tasks.size(); i++ ) {
            if (tasks.getTask(i).getTaskType().equals("D") && isOverdue(tasks.getTask(i))) {
                System.out.println((i + 1)+". "+tasks.getTask(i).getTaskListInfo() + " **Overdue** " );
                haveOverdueTasks = true;
            } else {
                System.out.println((i + 1) + ". " + tasks.getTask(i).getTaskListInfo());
            }
        }
        if (haveOverdueTasks){
            System.out.println("You may postpone overdue deadline task by a week by using 'snooze', " +
                    "please follow the format: snooze task number.");
        }
    }

    /**
     *To check whether deadline tasks are overdue.
     *
     * @param overdueTask Overdue task in task list.
     * @return Return as true if task is overdue, or return false.
     */
    public boolean isOverdue(Task overdueTask) {
        if (overdueTask instanceof Deadline) {
            ((Deadline)overdueTask).getDate();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String oldDateFormatted = ((Deadline)overdueTask).getDate();
        LocalDate taskDeadlineDate = LocalDate.parse(oldDateFormatted, formatter);
        if ( taskDeadlineDate.isBefore(java.time.LocalDate.now())){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print a message to user when task is successfully removed.
     *
     * @param tasks list of tasks.
     * @param removedTaskIndex the task that user wants to remove by index.
     */
    public void printDeleteMessage(TaskList tasks, int removedTaskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + tasks.getTask(removedTaskIndex).getTaskListInfo());
    }

    /**
     * Print a message to user when task is successfully mark as done.
     *
     * @param tasks list of tasks.
     * @param finishedTaskIndex task that user marks as done by index.
     */
    public void printDoneMessage(TaskList tasks, int finishedTaskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(finishedTaskIndex).getTaskListInfo());
    }

    /**
     * Print a message to user that number of tasks in the list.
     *
     * @param tasks list of tasks.
     */
    public void printUpdateMessage(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Print a list to user when task is found, or no task is found in the list.
     *
     * @param results found tasks in the list.
     */
    public void printFoundKeyword(ArrayList<Task> results) {
        System.out.println("Here are the matching tasks in your list:");
        if (results.size() == 0){
            System.out.println("   ☹ OOPS!!!, No record found!");
        }

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != null) {
                System.out.println((i + 1) + ". " + results.get(i).getTaskListInfo());
            }
        }
    }

    /**
     * Print a message to user when task is successfully added.
     *
     * @param tasks list of tasks.
     */
    public void printAddMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(tasks.size() - 1).getTaskListInfo());
    }

    /**
     * Print exception message when DukeException is thrown.
     *
     * @param exception exception message.
     */
    public void printExceptionMessage(DukeException exception) {
        System.out.println(exception);
    }

    /**
     * Print goodbye message when user exits.
     */
    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print message to user when task is successfully postponed.
     *
     * @param tasks the task is snoozed.
     */
    public void snooze(Task tasks) {
        System.out.println("Postpone 7 days: " + tasks.getTaskListInfo());
    }

}
