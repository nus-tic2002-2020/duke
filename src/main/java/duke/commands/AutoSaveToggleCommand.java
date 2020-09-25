package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.commands.SaveCommand;
import duke.ui.DukeUI;

/**
 * An extension of the {@code DukeCommand} object that toggles on and off, the auto-save function in {@code Duke}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class AutoSaveToggleCommand extends DukeCommand {

    //VARIABLES-----------------------------------------

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code AutoSaveToggleCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     */
    public AutoSaveToggleCommand(String cmdType) {
        super(cmdType);
    }

    /**
     * This method initialises a {@code AutoSaveToggleCommand} object.
     */
    public AutoSaveToggleCommand() { super(); }


    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code AutoSaveToggleCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) {

        DukeUI.printDivider();
        SaveCommand.toggleAutoSave();
        if(SaveCommand.getAutoSave()){
            System.out.println("        \u2615 Auto-Save has been toggled on.");
        } else {
            System.out.println("        \u26D4 Auto-Save has been toggled off.");
        }
        DukeUI.printDivider();
    }
}
