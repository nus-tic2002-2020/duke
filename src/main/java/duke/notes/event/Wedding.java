package duke.notes.event;

import duke.notes.budget.Budget;
import duke.commands.CommandException;
import duke.parser.DateException;
import duke.ui.DukeUI;

import java.util.Date;

/**
 * An extension of the {@code Event} object that labels the event as a {@code Wedding} object, and
 * includes an attached {@code Budget} object reflecting the budget set.
 *
 * To create a {@code Wedding} object, a budget amount for the cash gift must be set on top of
 * the requirements of the {@code Event} object.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Wedding extends Event {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Wedding} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Wedding} object.
     * @param startDate The start date and time of the {@code Wedding} object.
     * @param endDate The end date and time of the {@code Wedding} object.
     * @param itemBudget The budget amount for the {@code Wedding} object.
     * @param addDate The date and time the note was added.
     */
    public Wedding(int serialNum, String description, Date startDate, Date endDate,
                   double itemBudget, Date addDate) {
        super(serialNum, description, startDate, endDate, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    /**
     * This method is used to initialise a {@code Wedding} object.
     *
     */
    @SuppressWarnings("unused")
    public Wedding() {
        super();
    }

    /**
     * This method is used to construct a concluded {@code Wedding} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Wedding} object.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Wedding} object had concluded.
     * @param isDone True if the {@code Wedding} object had concluded.
     * @param startDate The start date and time of the {@code Wedding} object.
     * @param endDate The end date and time of the {@code Wedding} object.
     * @param durationMinutes The duration of the {@code Wedding} object in minutes.
     * @param itemBudget The budget amount for the {@code Wedding} object.
     */
    public Wedding(int serialNum, String description, Date addDate, Date doneDate, boolean isDone,
                    Date startDate, Date endDate, long durationMinutes, Budget itemBudget)  {
        super(serialNum, description, addDate, doneDate, isDone, startDate,  endDate,  durationMinutes);
        this.itemBudget = itemBudget;
    }

    /**
     * This method is used to construct a concluded {@code Wedding} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Wedding} object.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Wedding} object had concluded.
     * @param startDate The start date and time of the {@code Wedding} object.
     * @param endDate The end date and time of the {@code Wedding} object.
     * @param durationMinutes The duration of the {@code Wedding} object in minutes.
     * @param itemBudget The budget amount for the {@code Wedding} object.
     */
    public Wedding(int serialNum, String description, Date addDate, boolean isDone,
                    Date startDate, Date endDate, long durationMinutes, Budget itemBudget) {
        super(serialNum, description, addDate, isDone, startDate,  endDate,  durationMinutes);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Wedding} object as completed.
     *
     * @param doneDate The date and time the {@code Wedding} object had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException {
        if (!this.isDone) {
            this.itemBudget.setBudgetUsed(this.itemBudget.getBudgetRevised());
        }
        return super.markAsDone(doneDate);
    }

    /**
     * This method is used to delete an {@code Wedding} object.
     * The attached {@code Budget} object and associated class-level members would be adjusted accordingly.
     */
    @Override
    public void deleteExistingNote() {
        super.deleteExistingNote();
        this.itemBudget.deleteExistingBudget();
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to print detailed information of the {@code Wedding} object.
     */
    @Override
    public void printDetails() {
        System.out.println("            Duration : " +
                String.format("%,5d", this.durationMinutes) +  "mins");
        System.out.println("            From     : " +
                DukeUI.NOTE_TIME.format(this.startDate));
        System.out.println("            To       : " +
                DukeUI.NOTE_TIME.format(this.endDate));
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
     * This method exports the {@code Wedding} object as a string in a format that is
     * readable and re-constructable as a {@code Wedding} object.
     *
     * @return String The {@code Wedding} object as a string in a format
     * readable and re-constructable as a {@code Wedding} object.
     */
    @Override
    public String getSaveText() {
        String text = "Wedding/" +
                this.serialNum + "/" +
                this.description + "/" +
                DukeUI.INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                DukeUI.INPUT_TIME.format(this.startDate) + "/" +
                DukeUI.INPUT_TIME.format(this.endDate) + "/" +
                this.getDurationMinutes() + "/" +
                this.itemBudget.getSaveText();
        if(isDone) {
            text = text + "/" + DukeUI.INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Wedding} object as a {@code String}.
     *
     * @return String The class name of the {@code Wedding} object.
     */
    @Override
    public String getObjectClass() {
        return "Wedding";
    }

    /**
     * This method is used to retrieve the {@code Budget} object attached to the {@code Wedding} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Wedding} object.
     */
    public Budget getBudgetObject() { return (this.itemBudget); }
}
