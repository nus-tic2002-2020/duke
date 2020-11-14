import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.NoSuchFileException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showLoadingError("File not found or unable to load the tasks");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

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

                    ui.printDeleteMessage(tasks, removedTask - 1);
                    tasks.deletedTask(removedTask - 1);
                    ui.printUpdateMessage(tasks);
                } catch (DukeException exception) {
                    System.out.println(exception);
                }
                //System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

                try {
                    storage.updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else if (parser.getUserCommand().contains("find")) {
                try {
                    //this.findTask(parser);
                    String keyword = parser.getDescription();
                    //ArrayList<String> targets = tasks.findTargets(keyword);
                    tasks.find(keyword);

                } catch (DukeException exception) {
                    ui.printExceptionMessage(exception);
                }

                //String findKeyword = input.substring(5);
                //tasks.findTask(findKeyword);

            } else {
                try {
                    this.addTask(parser);

                    ui.printUpdateMessage(tasks);
                    //System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

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



    /***
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

}


