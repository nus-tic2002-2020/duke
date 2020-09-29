package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;
import java.util.Date;

/**
 * An extension of the {@code DukeCommand} object that marks existing {@code Note} objects as done.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class MarkDoneCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private ArrayList<Integer> toMarkDone;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code MarkDoneCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param toMarkDone The serial number(s) of {@code Note} objects to be marked as done.
     */
    public MarkDoneCommand(String cmdType, ArrayList<Integer> toMarkDone) {
        super(cmdType);
        this.toMarkDone = toMarkDone;
    }

    /**
     * This method initialises a {@code MarkDoneCommand} object.
     */
    @SuppressWarnings("unused")
    public MarkDoneCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code MarkDoneCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the format of {@code Date} objects.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException, DateException, InterruptedException {

        Date doneDate = new Date();

        DukeUI.printDivider();
        for (int note : this.toMarkDone) {
            dukeNotes.getNotes().get(note-1).markAsDone(doneDate);
        }
        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
        DukeUI.suggestListNotes();
        DukeUI.printDivider();
    }
}

