package Duke;

import java.util.Scanner;

public class Ui {
    Scanner input;
    Ui() {
        this.input = new Scanner(System.in);
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public String showBye() {
        String byeText = "Goodbye!";
        System.out.println(byeText);
        return byeText;
    }
    public String showError(String err) {
        System.out.println(err);
        return err;
    }
    public void showLine() {
        System.out.println("__________________________________");
    }
    public void askInput() {
        System.out.println("How can I help you mate?");
    }
    public String readCommand() {
        return input.nextLine();
    }
    public void showLoadingError(){
        System.out.println("test.txt does not exists.");
    }
    public static String showTaskAdded(Task task, TaskList taskList) {
        String print = "Task added: " + task.toString() + "\n\r"+taskList.size() + " task(s) in your list!";
        System.out.println(print);
        return print;
    }
    public String showTodoError() {
        String errorMessage = "☹ OOPS!!! The description of a todo cannot be empty.";
        System.out.println(errorMessage);
        return errorMessage;
    }
    public String showDeadlineError() {
        String errorMessage = "Format of deadline should be deadline <Description> /by <YYYY-MM-DD HH:MM>";
        System.out.println(errorMessage);
        return errorMessage;
    }
    public String showEventError() {
        String errorMessage = "Format of event should be event <Description> /at <YYYY-MM-DD HH:MM>";
        System.out.println(errorMessage);
        return errorMessage;
    }
    public String showDoneError() {
        String errorMessage = "Task does not exists. Use 'List' command to check existing tasks.";
        System.out.println(errorMessage);
        return errorMessage;
    }
    public String showDeleteError() {
        String errorMessage = "Task does not exists. Use 'List' command to check existing tasks.";
        System.out.println(errorMessage);
        return errorMessage;
    }
    public String showFindError() {
        String errorMessage = "Keyword cannot be empty";
        System.out.println(errorMessage);
        return errorMessage;
    }

}
