import java.io.IOException;
import java.time.format.DateTimeParseException;

import exceptions.*;
import exceptions.Exception;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;
import enumerations.CommandEnum;

/**
 * <h1>Task tracker, codenamed LISA.</h1>
 * This programme is an application to add and delete tasks to a list for tracking.
 * Available functions include finding tasks by description, listing out all tasks, finding tasks by day/month/year,
 * setting the task status to 'done', changing the priority of the task and a 'bye' call to terminate the programme.
 *
 * @author Aloysius Wong
 * @version 1.0
 * @since 08-11-2020
 */
public class Lisa {

    /**
     * These are the variables of the main LISA class, each variable being another class-level with their own methods.
     * Storage handles all methods that access and modifies the text file, where the task list is stored.
     * TaskList is a class which contains an ArrayList to store the list of tasks. It handles all methods that access
     * and modifies that ArrayList.
     * Ui (user interface) contains all methods that print to the terminal in IntelliJ.
     */
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * This creates the LISA-class object.
     *
     * @param filePath This is path to the text file from the root of the project, in string format.
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public Lisa(String filePath) throws IOException, Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the programme of LISA. Accepts commands from user if it matches with CommandEnum, otherwise prints
     * error message with template for correction.
     *
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     * @throws Exception This is the exception superclass. Has all the relevant self-created
     *                   exceptions under it as subclasses.
     */
    public void run() throws IOException, Exception {
        String input = "blank";

        while (!input.toLowerCase().equals("bye")) {
            input = ui.receive();
            String[] splitCmd = input.split(" ", 2);

            try {
                CommandEnum cmd = CommandEnum.valueOf(splitCmd[0].toUpperCase());
                switch (cmd) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                    case WITHIN:
                        try {
                            tasks.add(input);
                            storage.insert(tasks.getStore().get(tasks.getStore().size() - 1));
                            ui.printAddMessage(tasks.getStore().get(tasks.getStore().size() - 1));
                        } catch (NoDescriptionException e) {
                            ui.showNoDescriptionError();
                        } catch (NoTimeframeException e) {
                            ui.showNoTimeFrameError();
                        } catch (IndexOutOfBoundsException e) {
                            ui.showIndexError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError1();
                        } catch (EndTimeBeforeStartTimeException e) {
                            ui.showImpossibleDateTimeError();
                        }
                        break;

                    case LIST:
                        ui.list(tasks);
                        break;

                    case FIND:
                        try {
                            ui.find(input, tasks);
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case DAY:
                        try {
                            ui.listByDay(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError2();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case MONTH:
                        try {
                            ui.listByMonth(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError3();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case YEAR:
                        try {
                            ui.listByYear(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError4();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case DELETE:
                        try {
                            if (tasks.getStore().size() == 0) {
                                ui.printEmptyListMessage();
                                break;
                            }
                            tasks.delete(input);
                            storage.delete(input);
                            ui.printDeleteMessage();
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            ui.showInvalidNumberError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case DONE:
                        try {
                            if (tasks.getStore().size() == 0) {
                                ui.printEmptyListMessage();
                                break;
                            }
                            if (!tasks.setDone(input)) {
                                ui.printAlreadyDoneMessage();
                            } else {
                                storage.setDone(input);
                                ui.printDoneMessage();
                            }
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            ui.showInvalidNumberError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case CHANGE_PRIORITY:
                        try {
                            if (tasks.getStore().size() == 0) {
                                ui.printEmptyListMessage();
                                break;
                            }
                            tasks.setPriority(input);
                            storage.setPriority(input);
                            ui.printPriorityMessage();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            ui.showInvalidNumberError();
                        } catch (IllegalArgumentException e ) {
                            ui.showIncorrectPriorityError();
                        }
                        break;

                    case SEARCH_PRIORITY:
                        try {
                            ui.searchPriority(input, tasks);
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        } catch (IllegalArgumentException e) {
                            ui.showIncorrectPriorityError();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            ui.showMissingPriorityError();
                        }
                        break;

                    case HELP:
                        ui.printCmdList();
                        break;

                    case BYE:
                        ui.printByeMessage();
                        break;
                }
            } catch (IllegalArgumentException e) {
                ui.showIllegalArgumentError();
            }
        }
    }

    /**
     * This is the main function. Calls the constructor for a new LISA-class object and executes it's run method to
     * begin the programme.
     *
     * @param args This is to allow user-input from the command line (terminal).
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     * @throws Exception This is the exception superclass. Has all the relevant self-created
     *                   exceptions under it as subclasses.
     */
    public static void main(String[] args) throws Exception, IOException {
        new Lisa("data/task_list.txt").run();
    }
}
