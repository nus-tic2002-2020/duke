package duke.notes.todo;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
import java.util.Date;
import java.util.Scanner;

/**
 * An extension of the {@code Todo} object that labels the todo task as a {@code Shoplist} object, and
 * includes an attached {@code Budget} object reflecting the budget set.
 *
 * To create a {@code Shoplist} object, a budget amount for the item must be set on top of
 * the requirements of the {@code Todo} object.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Shoplist extends Todo {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Shoplist} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Shoplist} object.
     * @param itemBudget The budget amount for the {@code Shoplist} object.
     * @param addDate The date and time the note was added.
     */
    public Shoplist(int serialNum, String description, double itemBudget, Date addDate) {
        super(serialNum, description, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    /**
     * This method is used to initialise a {@code Shoplist} object.
     *
     */
    public Shoplist() { super(); }

    /**
     * This method is used to construct a concluded {@code Shoplist} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Shoplist} object.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Shoplist} object had concluded.
     * @param isDone True if the {@code Shoplist} object had concluded.
     * @param itemBudget The budget amount for the {@code Shoplist} object.
     */
    public Shoplist(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Budget itemBudget) {
        super(serialNum, description, addDate, doneDate, isDone);
        this.itemBudget = itemBudget;
    }

    /**
     * This method is used to construct a concluded {@code Shoplist} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Shoplist} object.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Shoplist} objecthad concluded.
     * @param itemBudget The budget amount for the {@code Shoplist} object.
     */
    public Shoplist(int serialNum, String description, Date addDate,
                    boolean isDone, Budget itemBudget) {
        super(serialNum, description, addDate, isDone);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Shoplist} object as completed.
     *
     * @param doneDate The date and time the {@code Shoplist} object had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if(!this.isDone) {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the price you paid for " +
                    this.description + "?");
            inputPrice = markDone.nextLine();
            double itemPrice = Double.parseDouble(inputPrice.substring(1));
            itemBudget.setBudgetUsed(itemPrice);
        }
        return super.markAsDone(doneDate);
    }

    /**
     * This method is used to delete an {@code Shoplist} object.
     * The attached {@code Budget} object and associated class-level members would be adjusted accordingly.
     */
    @Override
    public void deleteExistingNote() {
        super.deleteExistingNote();
        this.itemBudget.deleteExistingBudget();
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to print detailed information of the {@code Shoplist} object.
     */
    @Override
    public void printDetails(){
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
     * This method is used to retrieve the {@code Budget} object attached to the {@code Shoplist} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Shoplist} object.
     */
    public Budget getBudgetObject() {
        return (this.itemBudget);
    }

    /**
     * This method is used to retrieve the initial budget set in the {@code Budget} object attached
     * to the {@code Shoplist} object.
     *
     * @return double The initial budget set in {@code Budget} object attached to the {@code Shoplist} object.
     */
    public double getItemBudget() {
        return (this.itemBudget.getBudgetSet());
    }

    /**
     * This method is used to retrieve the revised budget amount in the {@code Budget} object attached
     * to the {@code Shoplist} object.
     *
     * @return double The revised budget amount in {@code Budget} object attached to the {@code Shoplist} object.
     */
    public double getItemBudgetRevised() {
        return (this.itemBudget.getBudgetRevised());
    }

    /**
     * This method is used to retrieve the amount of budget utilised in the {@code Budget} object attached
     * to the {@code Shoplist} object.
     *
     * @return double The amount of budget utilised in {@code Budget} object attached to the {@code Shoplist} object.
     */
    public double getItemPrice() {
        return (this.itemBudget.getBudgetUsed());
    }

    /**
     * This method returns as a String a report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Shoplist} object.
     *
     * @return String A report on the budget utilisation status and budget balance
     * in the {@code Budget} object attached to the {@code Shoplist} object.
     */
    public String getWithinBudget() {
        return this.itemBudget.getWithinBudget();
    }

    /**
     * This method exports the {@code Shoplist} object as a string in a format that is
     * readable and re-constructable as a {@code Shoplist} object.
     *
     * @return String The {@code Shoplist} object as a string in a format
     * readable and re-constructable as a {@code Shoplist} object.
     */
    @Override
    public String getSaveText() {
        String text = "Shoplist/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Shoplist} object as a {@code String}.
     *
     * @return String The class name of the {@code Shoplist} object.
     */
    @Override
    public String getObjectClass() {
        return "Shoplist";
    }

}
