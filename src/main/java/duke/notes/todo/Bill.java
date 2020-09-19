package duke.notes.todo;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
import java.util.Date;
import java.util.Scanner;

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
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if(this.isDone) {
            System.out.println("\tTask #" + this.serialNum + " was already done!");
        } else {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the amount you paid for " +
                    this.description + "?");
            inputPrice = markDone.nextLine();
            double itemPrice = Double.parseDouble(inputPrice.substring(1));
            itemBudget.setBudgetUsed(itemPrice);
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
        System.out.print("\t\t\tDeadline : " +
                TASK_TIME.format(this.targetDate));
        if(this.timesExtended > 0){
            System.out.print("(" + this.timesExtended +
                    " extensions)\n");
        } else {
            System.out.print("\n");
        }
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
     * This method is used to retrieve the {@code Budget} object attached to the {@code Bill} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Bill} object.
     */
    public Budget getBudgetObject() {
        return (this.itemBudget);
    }

    /**
     * This method is used to retrieve the initial budget set in the {@code Budget} object attached
     * to the {@code Bill} object.
     *
     * @return double The initial budget set in {@code Budget} object attached to the {@code Bill} object.
     */
    public double getItemBudget() {
        return (this.itemBudget.getBudgetSet());
    }

    /**
     * This method is used to retrieve the revised budget amount in the {@code Budget} object attached
     * to the {@code Bill} object.
     *
     * @return double The revised budget amount in {@code Budget} object attached to the {@code Bill} object.
     */
    public double getItemBudgetRevised() {
        return (this.itemBudget.getBudgetRevised());
    }

    /**
     * This method is used to retrieve the amount of budget utilised in the {@code Budget} object attached
     * to the {@code Bill} object.
     *
     * @return double The amount of budget utilised in {@code Budget} object attached to the {@code Bill} object.
     */
    public double getItemPrice() {
        return (this.itemBudget.getBudgetUsed());
    }

    /**
     * This method returns as a String a report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Bill} object.
     *
     * @return String A report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Bill} object.
     */
    public String getWithinBudget() {
        return this.itemBudget.getWithinBudget();
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
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_TIME.format(this.targetDate) + "/" +
                this.doneAhead + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
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
}
