package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.io.IOException;

/**
 * An extension of the {@code DukeCommand} object that saves existing {@code Note} to file.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class SaveCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private boolean autoSave = true;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code SaveCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     */
    public SaveCommand(String cmdType) {
        super(cmdType);
    }

    /**
     * This method initialises a {@code SaveCommand} object.
     */
    public SaveCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method toggles the auto-save feature in {@code Duke} between on and off.
     */
    public void toggleAutoSave() {

        this.autoSave = !this.autoSave;
    }

    /**
     * This method executes the function of the {@code SaveCommand} object via auto-save in the background.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @return boolean True if the auto-save has been performed successfully. 
     */
    public boolean autoSave(DukeList dukeNotes, DukeStorage dukeStorage) {

        try {
            if (autoSave) {
                dukeStorage.archiveToFile();
                dukeStorage.writeToFile(dukeNotes.getNotes());
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This method executes the function of the {@code SaveCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception IOException If the saved file specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws IOException {

        DukeUI.printDivider();
        dukeStorage.archiveToFile();
        dukeStorage.writeToFile(dukeNotes.getNotes());
        System.out.println("\tSave operation completed!");
        DukeUI.printDivider();
    }
}
