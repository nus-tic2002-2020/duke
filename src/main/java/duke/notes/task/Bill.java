package duke.notes.task;

import duke.notes.budget.Budget;
import duke.commands.CommandException;
import duke.parser.DateException;
import duke.ui.DukeUI;

import java.util.Date;

/**
 * An extension of the {@code Deadline} object that labels the deadline as a {@code Bill} object, and
 * includes an attached {@code Budget} object reflecting the budget set.
 *
 * To create a {@code Bill} object, a budget amount for the item must be set on top of
 * the requirements of the {@code Deadline} object.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Bill extends Deadline {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Bill} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Bill}.
     * @param targetDate The target date and time of the {@code Bill}.
     * @param itemBudget The budget amount for the {@code Bill}.
     * @param addDate The date and time the note was added.
     */
    public Bill(int serialNum, String description, Date targetDate, double itemBudget,
                Date addDate) {
        super(serialNum, description, targetDate, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    /**
     * This method is used to initialise a {@code Bill} object.
     *
     */
    @SuppressWarnings("unused")
    public Bill() { super(); }

    /**
     * This method is used to construct a concluded {@code Bill} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Bill} object.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Bill} object had concluded.
     * @param isDone True if the {@code Bill} object had concluded.
     * @param targetDate The target date and time of the {@code Bill}.
     * @param doneAhead True if the {@code Bill} had concluded ahead of the target date.
     * @param itemBudget The budget amount for the {@code Bill} object.
     */
    public Bill(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Date targetDate, boolean doneAhead, Budget itemBudget) {
        super(serialNum, description, addDate, doneDate, isDone, targetDate, doneAhead);
        this.itemBudget = itemBudget;
    }

    /**
     * This method is used to construct a concluded {@code Bill} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Bill} object.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Bill} object had concluded.
     * @param targetDate The target date and time of the {@code Bill}.
     * @param doneAhead True if the {@code Bill} had concluded ahead of the target date.
     * @param itemBudget The budget amount for the {@code Bill} object.
     */
    public Bill(int serialNum, String description, Date addDate,
                boolean isDone, Date targetDate, boolean doneAhead, Budget itemBudget) {
        super(serialNum, description, addDate, isDone, targetDate, doneAhead);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Bill} object as completed.
     * Class-level members will be updated to reflect the change in completion status.
     *
     * @param doneDate The date and time the {@code Deadline} had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException {
        if(!this.isDone) {
            itemBudget.setBudgetUsed(itemBudget.getBudgetRevised());
        }
        return super.markAsDone(doneDate);
    }

    /**
     * This method is used to delete an {@code Bill} object.
     * The attached {@code Budget} object and associated class-level members would be adjusted accordingly.
     */
    @Override
    public void deleteExistingNote() {
        super.deleteExistingNote();
        this.itemBudget.deleteExistingBudget();
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to print detailed information of the {@code Bill} object.
     */
    @Override
    public void printDetails(){
        System.out.print("            Deadline : " +
                DukeUI.NOTE_TIME.format(this.targetDate));
        if(this.timesExtended > 0){
            System.out.print("(" + this.timesExtended +
                    " extensions)\n");
        } else {
            System.out.print("\n");
        }
        System.out.println("            Budget   : $" +
                String.format("%,14.2f", this.getBudgetObject().getBUDGET_SET()));
        if (this.itemBudget.getIsRevised()) {
            System.out.println("            Revised  : $" +
                    String.format("%,14.2f", this.getBudgetObject().getBudgetRevised()));
        }
        if (this.isDone) {
            System.out.println("            Actual   : $" +
                    String.format("%,14.2f", this.getBudgetObject().getBudgetUsed()) +
                    " " + this.getBudgetObject().printWithinBudget());
            System.out.println("            Done     : " +
                    DukeUI.NOTE_TIME.format(this.doneDate));
        }
    }

    /**
     * This method exports the {@code Bill} object as a string in a format that is
     * readable and re-constructable as a {@code Bill} object.
     *
     * @return String The {@code Bill} object as a string in a format
     * readable and re-constructable as a {@code Bill} object.
     */
    @Override
    public String getSaveText() {
        String text = "Bill/" +
                this.serialNum + "/" +
                this.description + "/" +
                DukeUI.INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                DukeUI.INPUT_TIME.format(this.targetDate) + "/" +
                this.doneAhead + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + DukeUI.INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Bill} object as a {@code String}.
     *
     * @return String The class name of the {@code Bill} object.
     */
    @Override
    public String getObjectClass() {
        return "Bill";
    }

    /**
     * This method is used to retrieve the {@code Budget} object attached to the {@code Bill} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Bill} object.
     */
    public Budget getBudgetObject() {
        return (this.itemBudget);
    }
}
