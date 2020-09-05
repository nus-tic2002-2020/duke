import java.util.ArrayList;
import java.util.Date;

public class MarkDoneCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String cmdType;

    //CONSTRUCTORS--------------------------------------
    public MarkDoneCommand(ArrayList<String> inputs) {
        super(inputs);
        this.cmdType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {

        Date doneDate = new Date();
        if(CmdType.getKey(cmdType).toString().equals("MARKDONE")) {
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

