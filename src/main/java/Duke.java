import java.util.Scanner;
import tasks.*;
import exception.DukeException;

public class Duke {

    private static TaskList tasks;

    public static void main(String[] args) throws DukeException {
        Scanner scan = new Scanner(System.in);
        String echo;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (true) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                System.out.println("See you!");
                break;
            }
            else if (echo.equals("list")) {
                tasks.printList();
            }
            else if (echo.contains("done")) {
                tasks.checkForError(echo);
                tasks.taskDone(echo);
            }
            else if (echo.contains("delete")) {
                String[] splitMessage = tasks.messageSplitter(echo);
                tasks.deleteTask(splitMessage[1]);
            }
            else {
                tasks.checkForError(echo);
                tasks.addTask(echo);
            }
        }
    }
}
