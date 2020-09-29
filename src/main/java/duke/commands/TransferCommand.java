package duke.commands;

import duke.budget.Budget;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

/**
 * An extension of the {@code DukeCommand} object that transfers budget amounts between {@code Budget} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class TransferCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private int from;
    private int to;
    private double amount;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code TransferCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param from The serial number of the {@code Note} object from which an amount from its
     *             attached {@code Budget} object is to be transferred.
     * @param to The serial number of the {@code Note} object from which an amount is to be
     *           transferred to its attached {@code Budget} object.
     * @param amount The amount to be transferred between the {@code Budget} objects.
     */
    public TransferCommand(String cmdType, int from, int to, double amount) {
        super(cmdType);
        assert amount > 0 : "Transfer amount cannot be less than or equals to zero.";
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    /**
     * This method initialises a {@code TransferCommand} object.
     */
    @SuppressWarnings("unused")
    public TransferCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code TransferCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException {

        if(this.from > dukeNotes.getNotes().size() || this.to > dukeNotes.getNotes().size()) {
            throw new IndexOutOfBoundsException();
        }

        for(int i=0; i<dukeNotes.getNotes().size(); i++){
            if(dukeNotes.getNotes().get(i).getSerialNum() == this.from) { this.from = i; }
            if(dukeNotes.getNotes().get(i).getSerialNum() == this.to) { this.to = i; }
        }

        Budget fromBudget = dukeNotes.getNotes().get(this.from).getBudgetObject();
        Budget toBudget = dukeNotes.getNotes().get(this.to).getBudgetObject();

        DukeUI.printDivider();
        if(fromBudget.transferBudgetOut(this.amount, toBudget)){
            System.out.println("    Budget transferred from...");
            dukeNotes.getNotes().get(this.from).printList();
            System.out.println("    to...");
            dukeNotes.getNotes().get(this.to).printList();
            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
            DukeUI.suggestListNotes();
        }
        DukeUI.printDivider();
    }
}
