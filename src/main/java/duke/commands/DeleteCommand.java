package duke.commands;

import duke.Duke;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An extension of the {@code DukeCommand} object that performs the deletion of existing {@code Note} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DeleteCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private ArrayList<Integer> toDelete;
    private boolean confirmDelete;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code DeleteCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param toDelete The serial number(s) of {@code Note} objects to be deleted.
     */
    public DeleteCommand(String cmdType, ArrayList<Integer> toDelete) {
        super(cmdType);
        this.toDelete = toDelete;
        this.confirmDelete = false;
    }

    /**
     * This method initialises a {@code DeleteCommand} object.
     */
    @SuppressWarnings("unused")
    public DeleteCommand() {
        super();
    }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code DeleteCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException {

        ByteArrayOutputStream popupOut = new ByteArrayOutputStream();
        final PrintStream orgOut = System.out;
        System.setOut(new PrintStream(popupOut));

        DukeUI.printDivider();
        if(this.toDelete.size() == 0) {
            System.out.println("    There are no notes on your list, but I could still get a deep clean.");
        } else {
            System.out.println("    Are you sure you want to delete the following notes?");
            for (int note : this.toDelete) {
                for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                    if (dukeNotes.getNotes().get(i).getSerialNum() == note) {
                        dukeNotes.getNotes().get(i).printList();
                    }
                }
            }
        }
        System.out.println("    Data would be lost forever.");
        if(!Duke.isGUIMode){ DukeUI.askForConfirmation(); }
        DukeUI.printDivider();

        String notes = popupOut.toString();
        System.setOut(orgOut);

        if(Duke.isGUIMode) {
            DeleteConfirm deleteConfirm = new DeleteConfirm(notes);
            Stage popup = new Stage();
            deleteConfirm.start(popup);
            this.confirmDelete = deleteConfirm.getConfirmation();

        } else {
            System.out.println(notes);
            Scanner delete = new Scanner(System.in);
            this.confirmDelete = delete.nextLine().toUpperCase().equals("Y");
        }

        if(this.confirmDelete) {
            DukeUI.printDivider();
            for (int note : this.toDelete) {
                for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                    if (dukeNotes.getNotes().get(i).getSerialNum() == note) {
                        dukeNotes.getNotes().get(i).deleteExistingNote();
                        dukeNotes.getNotes().remove(i);
                        break;
                    }
                }
            }
            System.out.println("    Deletion(s) completed...");

            if(dukeNotes.getNotes().size() == 0) {
                System.out.println("    ...there are no notes on your list.");

            } else {
                System.out.println("    ...renumbering the remaining note(s)...");
                for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                    System.out.print("    #");
                    System.out.printf("%3d", dukeNotes.getNotes().get(i).getSerialNum());
                    System.out.print("     >>> ");
                    dukeNotes.getNotes().get(i).setSerialNum(i + 1);
                    System.out.print("    #");
                    System.out.printf("%3d", dukeNotes.getNotes().get(i).getSerialNum());
                    System.out.print("\n");
                }
                System.out.println("    The remaining notes have been renumbered!");
            }
            System.out.print("\n");
            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
            DukeUI.suggestListNotes();

        } else {
            DukeUI.printDivider();
            System.out.println("    Deletion aborted.");
        }
        DukeUI.printDivider();
    }
}
