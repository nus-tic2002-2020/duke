package duke.commands;

import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;


public class InfoCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private String filterType;

    //CONSTRUCTORS--------------------------------------
    public InfoCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        if(CmdType.getKey(this.cmdType).toString().equals("COMMANDS")) {
            DukeUI.printDivider();
            DukeUI.showCommandList();
            DukeUI.printDivider();
        }
    }
}
