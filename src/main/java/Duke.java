import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import tasks.Deadline;
import tasks.DoWithinPeriod;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

/**
 * Pi is a chatbot that helps user to manage different type of tasks.
 * User can add, delete, list, find, snooze and done his tasks.
 * Data is saved to text file. System will prompt user if there is no file.
 * Otherwise system will create a file to save data.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * To construct Pi application.
     *
     * @param filePath file path that used to save data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showLoadingError("File not found or unable to load the tasks");
                //e.printStackTrace();
            }
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Run the application.
     * Chatbot will do different tasks based on user's input.
     */
    public void run() {
        ui.showLogo();
        String input = ui.readCommand();

        while (!input.equals("bye")) {
            Parser parser = new Parser(input);

            if (parser.getUserCommand().equals("list")) {
                ui.printList(tasks);
            } else if (parser.getUserCommand().contains("done")) {
                try {
                    int finishedTask = parser.getTaskIndex(tasks.size());
                    assert finishedTask > 0 : "Invalid number, index shall be greater than 0.";
                    tasks.getTask(finishedTask - 1).markAsDone();

                    ui.printDoneMessage(tasks, finishedTask - 1);
                } catch (DukeException exception) {
                    System.out.println(exception);
                }

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else if (parser.getUserCommand().contains("delete")) {
                try {
                    int removedTask = parser.getTaskIndex(tasks.size());
                    assert removedTask > 0 : "Invalid number, index shall be greater than 0.";

                    ui.printDeleteMessage(tasks, removedTask - 1);
                    tasks.deletedTask(removedTask - 1);
                    ui.printUpdateMessage(tasks);
                } catch (DukeException exception) {
                    System.out.println(exception);
                }

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else if (parser.getUserCommand().contains("find")) {
                try {
                    this.findTask(parser);

                } catch (DukeException exception) {
                    ui.printExceptionMessage(exception);
                }
            } else if (parser.getUserCommand().contains("snooze")) {
                try {
                    this.snoozeTask(parser);
                    //int snoozedTask = parser.getTaskIndex(tasks.size());
                    //tasks.snoozedTask(snoozedTask - 1);
                    //tasks.snoozePostpone(tasks.getTask(snoozedTask-1));
                } catch (DukeException exception) {
                    System.out.println(exception);
                }


                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else {
                try {
                    this.addTask(parser);

                    ui.printUpdateMessage(tasks);

                    try {
                        storage.updateFile(tasks);
                    } catch (IOException exception) {
                        System.out.println("☹ OOPS: " + exception.getMessage());
                    }

                } catch (DukeException exception) {
                    System.out.println(exception);
                }
            }
            input = ui.readCommand();
        }
        ui.printByeMessage();
    }

    /**
     * Add different tasks to the list
     *
     * @param parser To get the user's input
     * @throws DukeException To show the error message when detect the wrong input or missing input
     */
    public void addTask(Parser parser) throws DukeException {
        if (parser.getUserCommand().equals("todo")) {
            String toDo = parser.getDescription();
            tasks.addTask(new ToDo(toDo, "T", false));
        } else if (parser.getUserCommand().equals("event") || parser.getUserCommand().equals("deadline")) {
            String description = parser.getDescription();

            LocalDate date = parser.getDate();
            LocalTime timing = parser.getTime();

            if (parser.getUserCommand().equals("event")) {
                tasks.addTask(new Event(description, "E", date, timing, false));
            } else {
                tasks.addTask(new Deadline(description, "D", date, timing, false));
            }
        } else if (parser.getUserCommand().equals("doWithinPeriod")) {
            String description = parser.getDescription();

            LocalDate fromDate = parser.getFromDate();
            LocalDate toDate = parser.getToDate();

            tasks.addTask(new DoWithinPeriod(description, "P", fromDate, toDate, false));

        } else {
            throw new DukeException("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        ui.printAddMessage(tasks);
    }

    /**
     * Function to find the task description from user command.
     *
     * @param parser To interpret user input command.
     * @throws DukeException Error message.
     */
    public void findTask(Parser parser) throws DukeException {
        String keyword = parser.getDescription();

        ArrayList<String> targets = tasks.findTargets(keyword);

        ui.printTargets(targets);
    }

    /**
     * Function to find the task by using task index number that user wants to postpone.
     *
     * @param parser To interpret user input command.
     * @throws DukeException Error message.
     */
    public void snoozeTask(Parser parser) throws DukeException {
        int snoozedTask = parser.getTaskIndex(tasks.size());

        tasks.snoozedTask(snoozedTask - 1);

        ui.snooze(tasks.snoozePostpone(tasks.getTask(snoozedTask-1)));
    }

}


