package duke.notes.event;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
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
                   double itemBudget, Date addDate) throws DateException {
        super(serialNum, description, startDate, endDate, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    /**
     * This method is used to initialise a {@code Wedding} object.
     *
     */
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
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
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
        System.out.println("\t\t\tDuration : " +
                String.format("%,5d", this.durationMinutes) +  "mins");
        System.out.println("\t\t\tFrom     : " +
                TASK_TIME.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                TASK_TIME.format(this.endDate));
        System.out.println("\t\t\tBudget   : $" +
                String.format("%,14.2f", this.getItemBudget()));
        if (this.itemBudget.getIsRevised()) {
            System.out.println("\t\t\tRevised  : $" +
                    String.format("%,14.2f", this.getItemBudgetRevised()));
        }
        if (this.isDone) {
            System.out.println("\t\t\tActual   : $" +
                    String.format("%,14.2f", this.getItemPrice()) +
                    " " + this.getWithinBudget());
            System.out.println("\t\t\tDone     : " +
                    TASK_TIME.format(this.doneDate));
        }
    }

    /**
     * This method is used to retrieve the {@code Budget} object attached to the {@code Wedding} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Wedding} object.
     */
    public Budget getBudgetObject() {
        return (this.itemBudget);
    }

    /**
     * This method is used to retrieve the initial budget set in the {@code Budget} object attached
     * to the {@code Wedding} object.
     *
     * @return double The initial budget set in {@code Budget} object attached to the {@code Wedding} object.
     */
    public double getItemBudget() {
        return (this.itemBudget.getBudgetSet());
    }

    /**
     * This method is used to retrieve the revised budget amount in the {@code Budget} object attached
     * to the {@code Wedding} object.
     *
     * @return double The revised budget amount in {@code Budget} object attached to the {@code Wedding} object.
     */
    public double getItemBudgetRevised() {
        return (this.itemBudget.getBudgetRevised());
    }

    /**
     * This method is used to retrieve the amount of budget utilised in the {@code Budget} object attached
     * to the {@code Wedding} object.
     *
     * @return double The amount of budget utilised in {@code Budget} object attached to the {@code Wedding} object.
     */
    public double getItemPrice() {
        return (this.itemBudget.getBudgetUsed());
    }

    /**
     * This method returns as a String a report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Wedding} object.
     *
     * @return String A report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Wedding} object.
     */
    public String getWithinBudget() {
        return this.itemBudget.getWithinBudget();
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
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_TIME.format(this.startDate) + "/" +
                INPUT_TIME.format(this.endDate) + "/" +
                this.getDurationMinutes() + "/" +
                this.itemBudget.getSaveText();
        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
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
}
