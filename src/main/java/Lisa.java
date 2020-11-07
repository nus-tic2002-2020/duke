import exceptions.NoDescriptionException;
import exceptions.NoTimeframeException;
import exceptions.TooManySpacesException;
import exceptions.Exception;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;
import enumerations.CommandEnum;

/** Lisa class. Stores tasks to a list and saves it to a txt file on device. */
public class Lisa {
    /** Handles any interaction or editing regarding the file. */
    private Storage storage;
    /** Handles any interaction or editing regarding the list of tasks. */
    private TaskList tasks;
    /** Handles any interaction regarding the terminal, e.g printing. */
    private Ui ui;

    /**
     * Constructor. Creates new Ui, Storage and Tasklist class for interaction with the terminal, txt file and
     * the ArrayList of tasks.
     *
     * @param filePath File path of the txt file (task_list.txt).
     * @throws IOException Error for input-output.
     */
    public Lisa(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the programme. Accepts commands from user if matches with CommandEnum, otherwise prints
     * error message with template for correction.
     *
     * @throws IOException Error for input-output.
     * @throws Exception Exception superclass. Has all the relevant self-created exceptions under it as subclasses.
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
                            tasks.setDone(input);
                            storage.setDone(input);
                            ui.printDoneMessage();
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            ui.showInvalidNumberError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;

                    case PRIORITY:
                        try {
                            if (tasks.getStore().size() == 0) {
                                ui.printEmptyListMessage();
                                break;
                            }
                            tasks.setPriority(input);
                            storage.setPriority(input);
                            ui.printPriorityMessage();
                        } catch (IllegalArgumentException e) {
                            ui.showIncorrectPriorityError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
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
     * Main function
     *
     * @param args
     * @throws Exception All self-created errors. Refer to Exception class for specifics.
     * @throws IOException Error for input-output.
     */
    public static void main(String[] args) throws Exception, IOException {
        new Lisa("data/task_list.txt").run();
    }
}
