package duke.commands;

import duke.Duke;
import duke.budget.AskPrice;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import javafx.stage.Stage;

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
     * @param cmdType The type of {@code DukeCommand} being constructed.
     */
    public ExitCommand(String cmdType) {
        super(cmdType);
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
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) {

        if(Duke.isGUIMode) {
            ExitConfirm exitConfirm = new ExitConfirm();
            Stage popup = new Stage();
            exitConfirm.start(popup);
            this.confirmExit = exitConfirm.getConfirmation();

        } else {
            Scanner quitDuke = new Scanner(System.in);

            DukeUI.printDivider();
            DukeUI.printOutstanding();
            System.out.println("    Are you sure you want to exit Duke?");
            System.out.println("    All unsaved data would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            this.confirmExit = quitDuke.nextLine().toUpperCase().equals("Y");
        }

        DukeUI.printDivider();
        if(this.confirmExit) {
            System.out.println("    Good Bye! Hope to see you again soon!");
        } else {
            System.out.println("    Yay! Thanks for staying!");
        }
        DukeUI.printDivider();
    }
}

