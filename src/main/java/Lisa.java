import exceptions.*;
import classes.*;
import enumerations.*;
import exceptions.Exception;
import java.io.IOException;

public class Lisa {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lisa(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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
                            ui.printAddMessage(input);
                            storage.insert(input);
                        } catch (NoDescriptionException e) {
                            ui.showNoDescriptionError();
                        } catch (NoDeadlineException e) {
                            ui.showNoDeadlineError();
                        } catch (NoTimeframeException e) {
                            ui.showNoTimeFrameError();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            ui.showArrayIndexError();
                        } catch (TooManySpacesException e) {
                            ui.showTooManySpacesError();
                        }
                        break;
                    }
                    case LIST: {
                        ui.list(tasks);
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
                            System.out.println("Error~ Invalid function number.");
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

    public static void main(String[] args) throws Exception, IOException {
        new Lisa("data/task_list.txt").run();
    }
}
