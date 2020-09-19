package duke.commands;

import duke.notes.event.Event;
import duke.notes.todo.Todo;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

/**
 * An extension of the {@code DukeCommand} object that performs the edition of descriptions in existing notes.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class EditDescriptionCommand extends DukeCommand {

    //VARIABLES-----------------------------------------
    protected int targetNote;
    protected String newDescription;
    protected String oldDescription;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs an {@code EditDescriptionCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param targetNote The {@code Note} whose {@code Date} object is to be edited.
     * @param newDescription The new description that is to replace the old description.
     */
    public EditDescriptionCommand(String cmdType, int targetNote, String newDescription) {
        super(cmdType);
        this.targetNote = targetNote;
        this.newDescription = newDescription;
    }

    /**
     * This method initialises a {@code EditDescriptionCommand} object.
     */
    public EditDescriptionCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code EditDescriptionCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException {

        for(int i=0; i<dukeNotes.getNotes().size(); i++) {
            if(dukeNotes.getNotes().get(i).getSerialNum() == this.targetNote) {

                DukeUI.printDivider();
                if(dukeNotes.getNotes().get(i).getIsDone()) {
                    if(dukeNotes.getNotes().get(i) instanceof Todo) {
                        System.out.println("\tThe task had already been completed.");
                    } else if(dukeNotes.getNotes().get(i) instanceof Event) {
                        System.out.println("\tThe event had already concluded.");
                    }
                    System.out.println("\tThe description shouldn't be edited anymore.");
                } else {
                    this.oldDescription = dukeNotes.getNotes().get(i).getDescription();
                    dukeNotes.getNotes().get(i).setDescription(this.newDescription);

                    System.out.println("\tDescription of Note #" + this.targetNote + " changed from...");
                    DukeUI.commandWrap(this.oldDescription, 66);
                    System.out.println("\tto...");
                    DukeUI.commandWrap(this.newDescription, 66);
                    DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                    DukeUI.suggestListNotes();
                }
                DukeUI.printDivider();
                break;
            }
        }
    }
}
