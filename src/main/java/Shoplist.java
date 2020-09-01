import java.util.Date;
import java.util.Scanner;

public class Shoplist extends Todo {

    //VARIABLES-----------------------------------------
    protected Double itemBudget;
    protected Double itemPrice;
    private boolean withinBudget = false;

    //CONSTRUCTORS--------------------------------------
    public Shoplist(int serialNum, String description, Double itemBudget, Date addDate) {
        super(serialNum, description, addDate);
        this.itemBudget = itemBudget;
    }

    public Shoplist() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setItemBudget(Double itemBudget) {
        this.itemBudget = itemBudget;
    }

    @Override
    public void markAsDone(Date doneDate) {
        if(this.isDone) {
            System.out.println("\tTask #" + this.serialNum + " was already done!");
        } else {
            String inputPrice;
            Scanner markDone = new Scanner(System.in);
            System.out.println("\tWhat is the price you paid for " +
                    this.description + "?");
            inputPrice = markDone.nextLine();
            this.itemPrice = Double.parseDouble(inputPrice.substring(1));
            if(itemPrice <= itemBudget){
                this.withinBudget = true;
            }
            this.isDone = true;
            this.doneDate = doneDate;
            tasksOutstanding--;
            tasksCompleted++;
            System.out.println("\tNoted! I've marked Task #" + this.serialNum + " as done.");
        }
        System.out.print(". ");
        this.printList();
    }

    public void setItemPrice(Double itemPrice) {

    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        super.printList();
        System.out.println("\t\t\tBudget   : $" +
                String.format("%10.2f", this.itemBudget));
        if (this.isDone) {
            System.out.println("\t\t\tActual   : $" +
                    String.format("%10.2f", this.itemPrice) +
                    " " + getWithinBudget());
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.doneDate));
        }
    }

    public Double getItemBudget() {
        return (this.itemBudget);
    }

    public Double getItemPrice() {
        return (this.itemPrice);
    }

    public String getWithinBudget() {
        if(this.withinBudget){
            return("\u2611");
        } else {
            return("\u2612");
        }
    }

    @Override
    public String getTaskIcon() {
        return("[S]");
    }
}
