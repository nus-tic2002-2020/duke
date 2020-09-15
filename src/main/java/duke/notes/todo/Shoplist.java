package duke.notes.todo;

import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import java.util.Date;
import java.util.Scanner;

public class Shoplist extends Todo {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    public Shoplist(int serialNum, String description, double itemBudget, Date addDate) {
        super(serialNum, description, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    public Shoplist() { super(); }

    public Shoplist(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Budget itemBudget) {
        super(serialNum, description, addDate, doneDate, isDone);
        this.itemBudget = itemBudget;
    }

    public Shoplist(int serialNum, String description, Date addDate,
                    boolean isDone, Budget itemBudget) {
        super(serialNum, description, addDate, isDone);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    public void setItemBudget(double itemBudget) {
        this.itemBudget.setBudgetSet(itemBudget);
    }

    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if(!this.isDone) {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the price you paid for " +
                    this.description.toString() + "?");
            inputPrice = markDone.nextLine();
            double itemPrice = Double.parseDouble(inputPrice.substring(1));
            itemBudget.setBudgetUsed(itemPrice);
        }
        return super.markAsDone(doneDate);
    }

    @Override
    public void deleteExistingNote() {
        super.deleteExistingNote();
        this.itemBudget.deleteExistingBudget();
    }

    public void setItemPrice(double itemPrice) {
        this.itemBudget.setBudgetUsed(itemPrice);
    }


    //GET STATEMENTS------------------------------------
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

    public Budget getBudgetObject() {
        return (this.itemBudget);
    }

    public double getItemBudget() {
        return (this.itemBudget.getBudgetSet());
    }

    public double getItemBudgetRevised() {
        return (this.itemBudget.getBudgetRevised());
    }

    public double getItemPrice() {
        return (this.itemBudget.getBudgetUsed());
    }

    public String getWithinBudget() {
        return this.itemBudget.getWithinBudget();
    }

    @Override
    public String getTaskIcon() throws CommandException {
        return NoteType.getTaskIcon("Shoplist");
    }

    @Override
    public String getSaveText() {
        String text = "Shoplist/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }

    @Override
    public String getObjectClass() {
        return "Shoplist";
    }

}
