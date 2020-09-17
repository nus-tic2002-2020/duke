package duke.commands;

import duke.notes.event.Event;
import duke.notes.todo.Todo;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;

/**
 * An extension of the {@code DukeCommand} object that performs the edition of descriptions in existing notes.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class EditDescriptionCommand extends DukeCommand {

    //VARIABLES-----------------------------------------
    protected String oldDescription;
    protected String newDescription;
    protected String targetNote;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs an {@code EditDescriptionCommand} object.
     *
     * @param inputs The accompanying attributes of the command as provided by the user.
     */
    public EditDescriptionCommand(ArrayList<String> inputs) {
        super(inputs);
        this.targetNote = inputs.get(1);
        this.newDescription = inputs.get(2);
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
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {

        if(CmdType.getKey(this.cmdType).toString().equals("EDITDESC")) {

            int targetSerialNum = Integer.parseInt(this.targetNote);
            for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                if(dukeNotes.getNotes().get(i).getSerialNum() == targetSerialNum) {

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
}
