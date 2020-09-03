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

    public Shoplist() {
        super();
    }


    //SET STATEMENTS------------------------------------
    public void setItemBudget(double itemBudget) {
        this.itemBudget.setBudgetSet(itemBudget);
    }

    @Override
    public boolean markAsDone(Date doneDate) {
        if(this.isDone) {
            System.out.println("\tTask #" + this.serialNum + " was already done!");
        } else {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the price you paid for " +
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

    public void setItemPrice(double itemPrice) {
        this.itemBudget.setBudgetUsed(itemPrice);
    }


    //GET STATEMENTS------------------------------------
    @Override
    public void printDetails(){
        System.out.println("\t\t\tBudget   : $" +
                String.format("%10.2f", this.getItemBudget()));
        if (this.isDone) {
            System.out.println("\t\t\tActual   : $" +
                    String.format("%10.2f", this.getItemPrice()) +
                    " " + this.getWithinBudget());
            System.out.println("\t\t\tDone     : " +
                    TASK_DATE.format(this.doneDate));
        }
    }

    public double getItemBudget() {
        return (this.itemBudget.getBudgetSet());
    }

    public double getItemPrice() {
        return (this.itemBudget.getBudgetUsed());
    }

    public String getWithinBudget() {
        if(this.itemBudget.getIsOverBudget()){
            return "\u2612\t$" + String.format("%10.2f", Math.abs(this.itemBudget.getBudgetBalance())) + " over budget.";
        } else {
            return "\u2611\t$" + String.format("%10.2f", Math.abs(this.itemBudget.getBudgetBalance())) + " under budget.";
        }
    }

    @Override
    public String getTaskIcon() {
        return("[S]");
    }

}
