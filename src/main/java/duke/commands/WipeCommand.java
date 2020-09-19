package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
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

        DukeUI.printDivider();

        String confirmWipe;
        Scanner wipeDuke = new Scanner(System.in);

        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        System.out.println("\tAre you sure you want to wipe Duke's Memory?");
        System.out.println("\tAll data, including saved files, would be lost.");
        DukeUI.askForConfirmation();
        DukeUI.printDivider();

        confirmWipe = wipeDuke.nextLine();

        if(confirmWipe.equals("Y")) {

            ArrayList<Integer> dukeMemory = new ArrayList<Integer>();
            for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                int serialNum = dukeNotes.getNotes().get(i).getSerialNum();
                dukeMemory.add(serialNum);
            }

            DeleteCommand wipeMemory = new DeleteCommand("DELETE", dukeMemory);
            wipeMemory.execute(dukeNotes, dukeStorage);

            File newFile = new File(dukeStorage.getPath());
            dukeStorage.setFile(newFile);

            System.out.println("\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:");
            DukeUI.printDivider();
            System.out.println("\tGood Bye! I won't remember who you are.");

        } else {
            System.out.println("\tYay! Thank you for keeping me!");
        }
        DukeUI.printDivider();
    }
}
