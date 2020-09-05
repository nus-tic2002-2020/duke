package duke.notes.todo;

import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import java.util.Date;
import java.util.Scanner;

public class Bill extends Deadline {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    public Bill(int serialNum, String description, Date targetDate, double itemBudget,
                Date addDate) throws DateException {
        super(serialNum, description, targetDate, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    public Bill() { super(); }

    public Bill(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Date targetDate, boolean doneAhead, Budget itemBudget) {
        super(serialNum, description, addDate, doneDate, isDone, targetDate, doneAhead);
        this.itemBudget = itemBudget;
    }

    public Bill(int serialNum, String description, Date addDate,
                boolean isDone, Date targetDate, boolean doneAhead, Budget itemBudget) {
        super(serialNum, description, addDate, isDone, targetDate, doneAhead);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    public void setItemBudget(double itemBudget) {
        this.itemBudget.setBudgetSet(itemBudget);
    }

    @Override
    public boolean markAsDone(Date doneDate) throws CommandException {
        if(this.isDone) {
            System.out.println("\tTask #" + this.serialNum + " was already done!");
        } else {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the amount you paid for " +
                    this.description.toString() + "?");
            inputPrice = markDone.nextLine();
            double itemPrice = Double.parseDouble(inputPrice.substring(1));
            itemBudget.setBudgetUsed(itemPrice);
            this.isDone = true;
            this.doneDate = doneDate;
            tasksOutstanding--;
            tasksCompleted++;
            System.out.println("\tNoted! I've marked Task #" + this.serialNum + " as done.");
        }
        this.printList();
        return false;
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
                    TASK_DATE.format(this.doneDate));
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
        return NoteType.getTaskIcon("Bill");
    }

    @Override
    public String getSaveText() {
        String text = "Bill/" +
                this.serialNum + "/" +
                this.description.toString() + "/" +
                INPUT_DATE.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_DATE.format(this.targetDate) + "/" +
                this.doneAhead + "/" +
                this.itemBudget.getSaveText();

        if(isDone) {
            text = text + "/" + INPUT_DATE.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }
}
