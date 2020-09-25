package duke.commands;

import duke.Duke;
import duke.budget.Budget;
import duke.notes.event.Event;
import duke.notes.todo.Todo;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An extension of the {@code DukeCommand} object that wipes the memory and saved files on {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class WipeCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------

    private boolean confirmWipe;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code WipeCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     */
    public WipeCommand(String cmdType) {
        super(cmdType);
    }

    /**
     * This method initialises a {@code WipeCommand} object.
     */
    public WipeCommand( ) { super(); }

    /**
     * This method is used to reset all static variables of in {@code Duke}
     * in the event of a program reset.
     */
    public static void resetStaticVariables() {
        Event.resetStaticVariables();
        Todo.resetStaticVariables();
        Budget.resetStaticVariables();
    }

    /**
     * This method is used to reset the file path for the {@code DukeStorage} object in {@code Duke}
     * in the event of a program reset.
     */
    public static void resetFilePath(DukeStorage dukeStorage) {
        File newFile = new File(dukeStorage.getPath());
        dukeStorage.setFile(newFile);
    }

    //METHODS-------------------------------------------

    /**
     * This method executes the function of the {@code WipeCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException {

        if(Duke.isGUIMode) {
            WipeConfirm wipeConfirm = new WipeConfirm();
            Stage popup = new Stage();
            wipeConfirm.start(popup);
            this.confirmWipe = wipeConfirm.getConfirmation();

        } else {
            Scanner wipeDuke = new Scanner(System.in);

            DukeUI.printDivider();
            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            System.out.println("    Are you sure you want to wipe Duke's memory?");
            System.out.println("    All data, including saved files, would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            this.confirmWipe = wipeDuke.nextLine().toUpperCase().equals("Y");
        }

        if(this.confirmWipe) {
            ArrayList<Integer> dukeMemory = new ArrayList<Integer>();
            for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                int serialNum = dukeNotes.getNotes().get(i).getSerialNum();
                dukeMemory.add(serialNum);
            }

            DeleteCommand wipeMemory = new DeleteCommand("DELETE", dukeMemory);
            wipeMemory.execute(dukeNotes, dukeStorage);

            DukeUI.printDivider();
            if(dukeNotes.getNotes().size() == 0) {
                resetStaticVariables();
                dukeStorage.deleteFiles();
                resetFilePath(dukeStorage);

                System.out.println("    :\n    :\n    :\n    :\n    :\n    :\n    :\n    :\n    :");
                System.out.println("    Good Bye! I won't remember who you are.");
            } else {
                System.out.println("    Yay! Thank you for keeping me!");
            }
        } else {
            DukeUI.printDivider();
            System.out.println("    Yay! Thank you for keeping me!");
        }
        DukeUI.printDivider();
    }
}
