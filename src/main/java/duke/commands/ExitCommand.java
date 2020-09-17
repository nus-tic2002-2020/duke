package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An extension of the {@code DukeCommand} object that confirms and terminates the operation of {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class ExitCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs an {@code ExitCommand} object.
     *
     * @param inputs The accompanying attributes of the command as provided by the user.
     */
    public ExitCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    /**
     * This method initialises a {@code ExitCommand} object.
     */
    public ExitCommand() {
        super();
    }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code ExitCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        DukeUI.printDivider();
        if(CmdType.getKey(this.cmdType).toString().equals("EXITDUKE")) {
            String confirmQuit;
            Scanner quitDuke = new Scanner(System.in);

            DukeUI.printOutstanding();
            System.out.println("\tAre you sure you want to exit Duke?");
            System.out.println("\tAll unsaved data would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            confirmQuit = quitDuke.nextLine();

            DukeUI.printDivider();
            if(confirmQuit.equals("Y")) {
                System.out.println("\tGood Bye! Hope to see you again soon!");
                this.confirmExit = true;

            } else {
                System.out.println("\tYay! Thanks for staying!");
                this.confirmExit = false;
            }
        }
        DukeUI.printDivider();
    }

}

