package duke.commands;

import duke.notes.event.*;
import duke.notes.todo.*;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;

public class EditDescriptionCommand extends DukeCommand {

    //VARIABLES-----------------------------------------
    protected String oldDescription;
    protected String newDescription;
    protected String targetNote;

    //CONSTRUCTORS--------------------------------------
    public EditDescriptionCommand(ArrayList<String> inputs) {
        super(inputs);
        this.targetNote = inputs.get(1);
        this.newDescription = inputs.get(2);
    }

    //METHODS-------------------------------------------
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
