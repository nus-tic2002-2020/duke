package duke;

import duke.commands.CommandException;
import duke.commands.DateException;
import duke.commands.DukeCommand;
import duke.parser.DukeParser;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

import java.io.IOException;
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
    boolean isLoadedFromFile;


    //RUN DUKE------------------------------------------
    /**
     * This method constructs a {@code Duke} object.
     *
     * @param path The path to the saved files in the hard drive.
     */
    public Duke(String path){

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
    public Duke(){}

    /**
     * This method run a {@code Duke} object.
     *
     * @exception ParseException If there is an error in reading and understanding inputs.
     * @exception IOException If (@code Note} or {@code File} objects specified could not be found.
     * @exception CommandException If there are errors in the command input.
     */
    public void run() throws ParseException, IOException, CommandException {

        boolean confirmExit = false;

        //Get Date & Time on startup
        Date now = new Date();

        //Run startup sequence
        DukeUI.printOnStartup(now, isLoadedFromFile);

        while(!confirmExit) {
            String input = DukeUI.receiveCommand();
            try {
                DukeCommand dukeCommand = DukeParser.readCommand(input);
                assert dukeCommand != null;
                dukeCommand.execute(dukeNotes, dukeStorage);
                confirmExit = dukeCommand.getConfirmExit();

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                DukeUI.printDivider();
                System.out.println("\tI don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                e.printStackTrace();
                System.out.println("\tThe task(s) you mentioned cannot be found.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.suggestListNotes();
                DukeUI.printDivider();

            } catch (NumberFormatException | ParseException e) {
                DukeUI.printDivider();
                System.out.println("\tI don't understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tThe attribute(s) you mentioned cannot be understood.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.printDivider();

            } catch (CommandException e) {
                e.printExplanation(input);

            } catch (DateException e) {
                e.printExplanation();
            }
        }
    }


    //DUKE MAIN-----------------------------------------
    public static void main(String[] args) throws Exception {
        String path = "data/notes.txt";
        new Duke(path).run();

    }
}
