import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {

    protected final SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");

    //VARIABLES-----------------------------------------
    protected Date addDate;
    protected Date doneDate = null;
    protected int serialNum;
    protected String description;
    protected boolean isDone = false;
    protected static int tasksOutstanding;
    protected static int tasksCompleted;

    //CONSTRUCTORS--------------------------------------
    public Task(int serialNum, String description, Date addDate) {
        this.addDate = addDate;
        this.description = description;
        this.serialNum = serialNum;
        tasksOutstanding++;
    }

    public Task() {
    }

    //SET STATEMENTS------------------------------------
    public void markAsDone(Date doneDate) {
        if(this.isDone) {
            System.out.println("\tTask #" + this.serialNum + " was already done!");
        } else {
            this.isDone = true;
            this.doneDate = doneDate;
            tasksOutstanding--;
            tasksCompleted++;
            System.out.println("\tNoted! I've marked Task #" + this.serialNum + " as done.");
        }
        System.out.print(". ");
        this.printList();
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

    public static int getTasksOutstanding() {
        return (tasksOutstanding);
    }

    public static int getTasksCompleted() {
        return (tasksCompleted);
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
