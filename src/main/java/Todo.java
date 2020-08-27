import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo extends Task {

    //VARIABLES-----------------------------------------
    SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");

    //CONSTRUCTORS--------------------------------------
    public Todo(String description, Date addDate) {
        super(description, addDate);
    }

    public Todo() {
        super();
    }

    //SET STATEMENTS------------------------------------
    @Override
    public void markAsDone(Date doneDate) {
        this.isDone = true;
        this.doneDate = doneDate;
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        System.out.println(String.format("%1$-30s%2$29s",
                this.getDescription(), "Added: " +
                taskDate.format(this.getAddDate())));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.getDoneDate()));
        }
    }

    public String getTaskIcon() {
        return("[T]");
    }

    //ABSTRACT TASK METHODS
    @Override
    public String getDoneAhead() { return null; }
    public long getDurationMinutes() { return 0; }
    public Date getEndDate() { return null; }
    public Double getItemBudget() { return null; }
    public Double getItemPrice() { return null; }
    public Date getStartDate() { return null; }
    public Date getTargetDate() { return null; }
    public String getWithinBudget() { return null; }
    public void markAsDone(Date doneDate, Double itemPrice){ }
}
