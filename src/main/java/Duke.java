import tasks.*;
import exception.DukeException;
import ui.Ui;

public class Duke {

    private static TaskList tasks;
    private static Ui ui;

    public Duke() {

        Ui ui = new Ui();
        ui.showWelcome();
    }

    public void run() throws DukeException {

        while (true) {
            String echo;
            echo = ui.readCommand();

            if (echo.equals("bye")) {
                System.out.println("See you!");
                break;
            }
            else if (echo.equals("list")) {
                System.out.println("-----------------------------------------------");
                ui.printTasks(tasks);
                System.out.println("-----------------------------------------------");
            }
            else if (echo.contains("undone")) {
                tasks.checkForError(echo);
                ui.taskUndone(tasks, echo);
            }
            else if (echo.contains("done")) {
                tasks.checkForError(echo);
                ui.taskDone(tasks, echo);
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

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
