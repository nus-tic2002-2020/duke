import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public void showLogo(){
        String logo = " ____  _      ,-.___,-.     \n"
                + "|  _ \\|_|     \\_/_ _\\_/ \n"
                + "| |_| | |       )O_O(      \n"
                + "|  __/| |      { (_) }     \n"
                + "|_|   |_|       `-^-'      \n";

        System.out.println("Hello,\n" + logo + "   I'm Pi,\n" + "   What can I do for you?");
        showLine();

        //ArrayList<Task> tasks = new ArrayList<Task>();
    }

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void showLine(){
        System.out.println("   ________________________");
    }

    public void showLoadingError(String message){
        System.out.println(message);
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        if (tasks.size() == 0){
            System.out.println("Currently your list is empty.");
        }

        for (int i = 0; i < tasks.size(); i++ ) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).getTaskListInfo());
        }
    }

    public void printDeleteMessage(TaskList tasks, int removedTaskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + tasks.getTask(removedTaskIndex).getTaskListInfo());
    }

    public void printDoneMessage(TaskList tasks, int finishedTaskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(finishedTaskIndex).getTaskListInfo());
    }

    public void printUpdateMessage(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }


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

    public void printAddMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(tasks.size() - 1).getTaskListInfo());
    }

    public void printExceptionMessage(DukeException exception) {
        System.out.println(exception);
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
