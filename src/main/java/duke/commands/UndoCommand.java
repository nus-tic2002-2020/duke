package duke.commands;

import duke.Duke;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * An extension of the {@code DukeCommand} object that reloads a previously saved version of {@code Note} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class UndoCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------

    private boolean confirmUndo;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code UndoLastCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     */
    public UndoCommand(String cmdType) {
        super(cmdType);
        this.confirmUndo = false;
    }

    /**
     * This method initialises a {@code UndoLastCommand} object.
     */
    @SuppressWarnings("unused")
    public UndoCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code UndoLastCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception IOException If previously saved files cannot be found.
     * @exception ParseException If there are errors reading previously saved files.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws IOException, ParseException {

        if(Duke.isGUIMode) {
            UndoConfirm undoConfirm = new UndoConfirm();
            Stage popup = new Stage();
            undoConfirm.start(popup);
            this.confirmUndo = undoConfirm.getConfirmation();

        } else {
            Scanner undoDuke = new Scanner(System.in);

            DukeUI.printDivider();
            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            System.out.println("    Are you sure you want to undo the last save?");
            System.out.println("    All unsaved data would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            this.confirmUndo = undoDuke.nextLine().toUpperCase().equals("Y");
        }

        DukeUI.printDivider();
        if(this.confirmUndo) {

            switch (dukeStorage.revertToLastSave(dukeNotes)) {
                case -1 -> System.out.println("    I have undone the maximum number of times.\n");
                case 0 -> {
                    System.out.println("    I have undone the last save operation.");
                    System.out.println("    You have no more opportunities to undo.\n");
                }
                case 1 -> {
                    System.out.println("    I have undone the last save operation.");
                    System.out.println("    You have 1 last opportunity to undo.\n");
                }
                case 2 -> {
                    System.out.println("    I have undone the last save operation.");
                    System.out.println("    You have 2 more opportunities to undo.\n");
                }
            }
        } else {
            System.out.println("    Undo last save aborted!");
        }
        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        DukeUI.printDivider();
    }
}
