import exceptions.*;
import classes.*;
import enumerations.*;
import exceptions.Exception;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Lisa class. Stores tasks to a list and saves it to a txt file on device.
 */
public class Lisa {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor. Creates new Ui, Storage and Tasklist class for interaction with the terminal, txt file and
     * the ArrayList of tasks
     *
     * @param filePath File path of the txt file (task_list.txt)
     * @throws IOException Error for input-output
     */
    public Lisa(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Method to start running the programme. Accepts commands from user if matches with CommandEnum, otherwise prints
     * error message with template for correction
     *
     * @throws IOException Error for input-output
     * @throws Exception Exception superclass. Has all the relevant self-created exceptions under it as subclasses
     */
    public void run() throws IOException, Exception {
        String input = "blank";
        while (!input.toLowerCase().equals("bye")) {
            input = ui.receive();
            String[] temp = input.split(" ", 2);
            try {
                CommandEnum cmd = CommandEnum.valueOf(temp[0].toUpperCase());
                switch (cmd) {
                    case TODO:
                    case DEADLINE:
                    case EVENT: {
                        try {
                            tasks.add(input);
                            storage.insert(tasks.getStore().get(tasks.getStore().size()-1));
                            ui.printAddMessage(tasks.getStore().get(tasks.getStore().size()-1));
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
                    }
                    case LIST: {
                        ui.list(tasks);
                        break;
                    }
                    case DAY: {
                        try {
                            ui.listByDay(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError2();
                        }
                        break;
                    }
                    case MONTH: {
                        try {
                            ui.listByMonth(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError3();
                        }
                        break;
                    }
                    case YEAR: {
                        try {
                            ui.listByYear(input, tasks);
                        } catch (DateTimeParseException e) {
                            ui.showDateFormatError4();
                        }
                        break;
                    }
                    case DELETE: {
                        try {
                            tasks.delete(input);
                            storage.delete(input);
                            ui.printDeleteMessage();
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException | Exception e) {
                            ui.showInvalidNumberError();
                        }
                        break;
                    }
                    case DONE: {
                        try {
                            tasks.done(input);
                            ui.printDoneMessage();
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            ui.showInvalidNumberError();
                        }
                        break;
                    }
                    case BYE: {
                        ui.printByeMessage();
                        break;
                    }
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
     * @throws Exception All self-created errors
     * @throws IOException Error for input-output
     */
    public static void main(String[] args) throws Exception, IOException {
        new Lisa("data/task_list.txt").run();
    }
}
