import java.util.Date;

public class Shoplist extends Todo {

    //VARIABLES-----------------------------------------
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
