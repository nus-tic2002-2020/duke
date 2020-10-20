package duke;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.notes.task.Task;
import duke.parser.DateException;
import duke.commands.DukeCommand;
import duke.notes.event.Event;
import duke.parser.DukeParser;
import duke.parser.PrefixException;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;

/**
 * {@code Duke} is a note-keeper, task manager, budget assistant, and more...!
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Duke implements DukeParser, DukeUI {

    //DUKE VARIABLES------------------------------------
    private static DukeStorage dukeStorage;
    private static DukeList dukeNotes;
    private static boolean isLoadedFromFile;
    public static boolean isGUIMode;
    private static boolean isConfirmedExit = false;
    private static boolean isErrorReturn = false;


    //RUN DUKE------------------------------------------
    /**
     * This method constructs a {@code Duke} object.
     *
     * @param path The path to the saved files in the hard drive.
     */
    public Duke(String path) {

        dukeStorage = new DukeStorage(path);

        try {
            dukeNotes = new DukeList(dukeStorage.readFromFile());
            isLoadedFromFile = true;

        } catch (IOException | ParseException e) {
            dukeNotes = new DukeList();
            isLoadedFromFile = false;
        }
    }

    /**
     * This method is used to initialise a {@code Duke} object.
     *
     */
    @SuppressWarnings("unused")
    public Duke(){}

    /**
     * This method run {@code Duke} when using the {@code Duke Console}.
     *
     * @exception ParseException If there is an error in reading and understanding inputs.
     * @exception CommandException If there are errors in the command input.
     */
    public void run() throws ParseException, CommandException {


        isGUIMode = false;

        //Get Date & Time on startup
        Date now = new Date();

        //Run startup sequence
        DukeUI.printOnStartup(now, isLoadedFromFile);

        while(!isConfirmedExit) {
            String input = DukeUI.receiveCommand();
            try {
                DukeCommand dukeCommand = DukeParser.readCommand(input);
                dukeCommand.execute(dukeNotes, dukeStorage);
                isConfirmedExit = dukeCommand.getConfirmExit();

            } catch (NullPointerException | IndexOutOfBoundsException | IOException e) {
                System.out.println("    I don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                e.printStackTrace();
                System.out.println("    The note(s) you mentioned cannot be found.");
                System.out.println("    There could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.suggestListNotes();
                DukeUI.printDivider();

            } catch (NumberFormatException | ParseException e) {
                System.out.println("    I don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("    The attribute(s) you mentioned cannot be understood.");
                System.out.println("    There could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.printDivider();

            } catch (CommandException e) {
                e.printExplanation(input);

            } catch (DateException e) {
                e.printExplanation();

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (PrefixException e) {
                e.printExplanation(input);
            }
        }
    }

    /**
     * This method is used to print the start up sequence when
     * running {@code Duke} when using the {@code Duke GUI Launcher}.
     *
     * @return String The text to be presented on the {@code Duke GUI Launcher}.
     */
    public static String startUp() throws ParseException, CommandException {

        isGUIMode = true;
        ByteArrayOutputStream outputGUI = new ByteArrayOutputStream();
        final PrintStream psConsole = System.out;
        System.setOut(new PrintStream(outputGUI));

        //Get Date & Time on startup
        Date now = new Date();

        //Run startup sequence
        DukeUI.printOnStartup(now, isLoadedFromFile);
        System.setOut(psConsole);
        return outputGUI.toString();
    }

    /**
     * This method is used to run {@code Duke} when using the {@code Duke GUI Launcher}.
     *
     * @return String The text to be presented on the {@code Duke GUI Launcher}.
     */
    public String getResponse(String input) throws IOException, CommandException {

        isErrorReturn = false;

        assert input != null;
        ByteArrayOutputStream outputGUI = new ByteArrayOutputStream();
        final PrintStream psConsole = System.out;
        System.setOut(new PrintStream(outputGUI));

        try {
            DukeCommand dukeCommand = DukeParser.readCommand(input);
            dukeCommand.execute(dukeNotes, dukeStorage);
            isConfirmedExit = dukeCommand.getConfirmExit();

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            isErrorReturn = true;
            System.out.println("    I don't understand what you meant by...\n");
            DukeUI.commandWrap(input, 66);
            e.printStackTrace();
            System.out.println("    The note(s) you mentioned cannot be found.");
            System.out.println("    There could be errors or omissions in the data entry, format or delimiters.");
            DukeUI.suggestFormat();
            DukeUI.suggestListNotes();
            DukeUI.printDivider();

        } catch (NumberFormatException | ParseException e) {
            isErrorReturn = true;
            System.out.println("    I don't understand what you meant by...\n");
            DukeUI.commandWrap(input, 66);
            System.out.println("    The attribute(s) you mentioned cannot be understood.");
            System.out.println("    There could be errors or omissions in the data entry, format or delimiters.");
            DukeUI.suggestFormat();
            DukeUI.printDivider();

        } catch (CommandException e) {
            isErrorReturn = true;
            e.printExplanation(input);

        } catch (DateException e) {
            isErrorReturn = true;
            e.printExplanation();

        } catch (InterruptedException e) {
            isErrorReturn = true;
            e.printStackTrace();

        } catch (PrefixException e) {
            isErrorReturn = true;
            e.printExplanation(input);
        }

        System.setOut(psConsole);
        return outputGUI.toString();
    }

    /**
     * This method is used to retrieve the confirmation status on whether an exit command is received.
     *
     * @return boolean True if an exit command is received and confirmed.
     */
    public static boolean getIsConfirmedExit() {
        return isConfirmedExit;
    }

    /**
     * This method is used to retrieve the status on whether a message returned by {@code Duke} is an error message.
     *
     * @return boolean True if message returned by {@code Duke} is an error message.
     */
    public static boolean getIsErrorReturn() {
        return isErrorReturn;
    }

    /**
     * This method is used to reset all static variables of in {@code Duke}
     * in the event of a program reset or reading from a new file without restarting.
     */
    public static void resetStaticVariables() {
        Event.resetStaticVariables();
        Task.resetStaticVariables();
        Budget.resetStaticVariables();
    }

    //DUKE MAIN-----------------------------------------
    public static void main(String[] args) throws Exception {
        String path = "data/notes.txt";
        new Duke(path).run();

    }
}
