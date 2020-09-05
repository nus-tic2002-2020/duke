package duke.commands;

import duke.notes.*;
import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;

public class InfoCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String infoType;

    //CONSTRUCTORS--------------------------------------
    public InfoCommand(ArrayList<String> inputs) {
        super(inputs);
        this.infoType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        DukeUI.printDivider();
        switch (CmdType.getKey(infoType).toString()) {
            case "COMMANDS" -> {
                DukeUI.showCommandList();
            }
            case "LISTNOTES" -> {
                if (dukeNotes.getNotes().size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of anything yet.");
                } else {
                    System.out.println("\tHere are the things you told me to note:-");
                    for (Task note : dukeNotes.getNotes()) {
                        note.printList();
                    }
                    DukeUI.printCompleted();
                    DukeUI.printOutstanding();
                }
            }
        }
        DukeUI.printDivider();
    }
}
