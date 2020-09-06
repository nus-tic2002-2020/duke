package duke;


import duke.commands.*;
import duke.parser.*;
import duke.storage.*;
import duke.ui.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Duke implements DukeParser, DukeUI {

    private static DukeStorage dukeStorage;
    private static DukeList dukeNotes;
    boolean isLoadedFromFile = false;

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
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                e.printStackTrace();
                System.out.println("\tThe task(s) you mentioned cannot be found.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.suggestListNotes();
                DukeUI.printDivider();

            } catch (NumberFormatException | ParseException e) {
                DukeUI.printDivider();
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tThe attribute(s) you mentioned cannot be understood.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                DukeUI.suggestFormat();
                DukeUI.printDivider();

            } catch (CommandException e) {
                e.printExplanation(input);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "data/notes.txt";
        new Duke(path).run();

    }
}
