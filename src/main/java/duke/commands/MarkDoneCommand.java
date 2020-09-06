package duke.commands;

import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;
import java.util.Date;

public class MarkDoneCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    public MarkDoneCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {

        Date doneDate = new Date();
        if(CmdType.getKey(this.cmdType).toString().equals("MARKDONE")) {
            ArrayList<Integer> notes = new ArrayList<Integer>();
            for(int i=1; i<inputs.size(); i++) {
                int toDelete = Integer.parseInt(inputs.get(i));
                if(toDelete > dukeNotes.getNotes().size()) {
                    throw new IndexOutOfBoundsException();
                } else {
                    notes.add(toDelete);
                }
            }

            DukeUI.printDivider();
            for (int note : notes) {
                dukeNotes.getNotes().get(note-1).markAsDone(doneDate);
            }
            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
            DukeUI.suggestListNotes();
            DukeUI.printDivider();
        }
    }
}

