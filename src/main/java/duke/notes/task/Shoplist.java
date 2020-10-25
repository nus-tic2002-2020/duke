package duke.notes.task;

import duke.Duke;
import duke.notes.budget.AskPrice;
import duke.notes.budget.Budget;
import duke.commands.CommandException;
import duke.parser.DateException;
import duke.ui.DukeUI;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Scanner;

/**
 * An extension of the {@code Task} object that labels the todo task as a {@code Shoplist} object, and
 * includes an attached {@code Budget} object reflecting the budget set.
 *
 * To create a {@code Shoplist} object, a budget amount for the item must be set on top of
 * the requirements of the {@code Task} object.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Shoplist extends Task {

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
    @SuppressWarnings("unused")
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
     * @param isDone True if the {@code Shoplist} object had concluded.
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
    public boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException {

        double itemPrice;
        if(!this.isDone) {
            if(Duke.isGUIMode) {
                AskPrice askPrice = new AskPrice(this.description);
                Stage popup = new Stage();
                askPrice.start(popup);
                try {
                    itemPrice = Double.parseDouble(askPrice.returnPrice());
                } catch (Exception e) {
                    System.out.println("    Aborted!");
                    return false;
                }
            } else {
                System.out.println("    What is the price you paid for " +
                        this.description + "?");
                Scanner markDone = new Scanner(System.in);
                itemPrice = Double.parseDouble(markDone.nextLine().substring(1));
            }
            this.itemBudget.setBudgetUsed(itemPrice);
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
                DukeUI.INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + DukeUI.INPUT_TIME.format(this.doneDate);
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

    /**
     * This method is used to retrieve the {@code Budget} object attached to the {@code Shoplist} object.
     *
     * @return Budget The {@code Budget} object attached to the {@code Shoplist} object.
     */
    public Budget getBudgetObject() {
        return (this.itemBudget);
    }
}
