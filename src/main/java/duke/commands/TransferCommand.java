package duke.commands;

import duke.budget.*;
import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;
import java.util.Date;

public class TransferCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String cmdType;

    //CONSTRUCTORS--------------------------------------
    public TransferCommand(ArrayList<String> inputs) {
        super(inputs);
        this.cmdType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {

        Date doneDate = new Date();
        if(CmdType.getKey(cmdType).toString().equals("TRANSFER")) {
            int from = Integer.parseInt(this.inputs.get(1));
            int to = Integer.parseInt(this.inputs.get(2));
            double amount = Double.parseDouble(this.inputs.get(3));

            System.out.println("\t" + from + "\t" + to + "\t" + amount);

            if (from == to){
                throw new CommandException("Transferring from and to the same account achieves nothing, at all.");
            } else if (from > dukeNotes.getNotes().size() || to > dukeNotes.getNotes().size()) {
                throw new IndexOutOfBoundsException();
            }
            for(int i=0; i<dukeNotes.getNotes().size(); i++){
                if(dukeNotes.getNotes().get(i).getSerialNum() == from) { from = i; }
                if(dukeNotes.getNotes().get(i).getSerialNum() == to) { to = i; }
            }

            Budget fromBudget = dukeNotes.getNotes().get(from).getBudgetObject();
            Budget toBudget = dukeNotes.getNotes().get(to).getBudgetObject();

            DukeUI.printDivider();
            if(fromBudget.transferBudgetOut(amount, toBudget)){
                System.out.println("\tBudget transferred from...");
                dukeNotes.getNotes().get(from).printList();
                System.out.println("\tto...");
                dukeNotes.getNotes().get(to).printList();
                DukeUI.printCompleted();
                DukeUI.printOutstanding();
                DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                DukeUI.suggestListNotes();
            }
            DukeUI.printDivider();
        }
    }
}
