import java.text.SimpleDateFormat;
import java.util.Date;

public class Shoplist extends Todo {

    //VARIABLES-----------------------------------------
    SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");
    protected Double itemBudget;
    protected Double itemPrice;
    protected boolean withinBudget = false;

    //CONSTRUCTORS--------------------------------------
    public Shoplist(String description, Double itemBudget, Date addDate) {
        super(description, addDate);
        this.itemBudget = itemBudget;
    }

    public Shoplist() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setItemBudget(Double itemBudget) {
        this.itemBudget = itemBudget;
    }

    public void markAsDone(Date doneDate, Double itemPrice) {
        this.isDone = true;
        this.doneDate = doneDate;
        this.itemPrice = itemPrice;
        if(itemPrice <= itemBudget){
            this.withinBudget = true;
        }
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        System.out.println(String.format("%1$-30s%2$29s",
                this.getDescription(), "Added: " +
                taskDate.format(this.getAddDate())));
        System.out.println("\t\t\tBudget   : $" +
                String.format("%10.2f", this.getItemBudget()));
        if (this.isDone) {
            System.out.println("\t\t\tActual   : $" +
                    String.format("%10.2f", this.getItemPrice()) +
                    " " + this.getWithinBudget());
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.getDoneDate()));
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
