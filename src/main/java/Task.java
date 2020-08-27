import java.util.Date;

public abstract class Task {

    //VARIABLES-----------------------------------------
    protected Date addDate;
    protected Date doneDate = null;
    protected String description;
    protected boolean isDone = false;

    //CONSTRUCTORS--------------------------------------
    public Task(String description, Date addDate) {
        this.addDate = addDate;
        this.description = description;
    }

    public Task() {
    }

    //SET STATEMENTS------------------------------------
    public void markAsDone(Date doneDate) {
        this.isDone = true;
        this.doneDate = doneDate;
    }

    //GET STATEMENTS------------------------------------
    public Date getAddDate() {
        return (this.addDate);
    }

    public Date getDoneDate() {
        return (this.doneDate);
    }

    public String getDescription() {
        return (this.description.toString());
    }

    public String getStatusIcon() {
        if(this.isDone){
            return ("[\u2713]");
        } else {
            return ("[\u2718]");
        }
    }

    //ABSTRACT METHODS
    public abstract String getDoneAhead();
    public abstract long getDurationMinutes();
    public abstract Date getEndDate();
    public abstract Double getItemBudget();
    public abstract Double getItemPrice();
    public abstract Date getStartDate();
    public abstract Date getTargetDate();
    public abstract String getTaskIcon();
    public abstract String getWithinBudget();
    public abstract void printList();
    public abstract void markAsDone(Date doneDate, Double itemPrice);
}
