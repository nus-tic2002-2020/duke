package duke.notes.event;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
import duke.notes.NoteType;

import java.util.Date;

public class Wedding extends Event {

    //VARIABLES-----------------------------------------
    protected Budget itemBudget;


    //CONSTRUCTORS--------------------------------------
    public Wedding(int serialNum, String description, Date startDate, Date endDate,
                   double itemBudget, Date addDate) throws DateException {
        super(serialNum, description, startDate, endDate, addDate);
        this.itemBudget = new Budget(itemBudget);
    }

    public Wedding() {
        super();
    }

    public Wedding(int serialNum, String description, Date addDate, Date doneDate, boolean isDone,
                    Date startDate, Date endDate, long durationMinutes, Budget itemBudget)  {
        super(serialNum, description, addDate, doneDate, isDone, startDate,  endDate,  durationMinutes);
        this.itemBudget = itemBudget;
    }

    public Wedding(int serialNum, String description, Date addDate, boolean isDone,
                    Date startDate, Date endDate, long durationMinutes, Budget itemBudget) {
        super(serialNum, description, addDate, isDone, startDate,  endDate,  durationMinutes);
        this.itemBudget = itemBudget;
    }

    //SET STATEMENTS------------------------------------
    public void setItemBudget(double itemBudget) {
        this.itemBudget.setBudgetSet(itemBudget);
    }

    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if (!this.isDone) {
            this.itemBudget.setBudgetUsed(this.itemBudget.getBudgetRevised());
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
    public void printDetails() {
        System.out.println("\t\t\tDuration : " +
                String.format("%,5d", this.durationMinutes) +  "mins");
        System.out.println("\t\t\tFrom     : " +
                TASK_DATE.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                TASK_DATE.format(this.endDate));
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
        return NoteType.getTaskIcon("Wedding");
    }

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

    @Override
    public String getObjectClass() {
        return "Wedding";
    }
}
