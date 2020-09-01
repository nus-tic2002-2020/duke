import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    //VARIABLES-----------------------------------------
    protected Date startDate;
    protected Date endDate;
    private long durationMinutes;

    //CONSTRUCTORS--------------------------------------
    public Event(int serialNum, String description, Date startDate, Date endDate, Date addDate) {
        super(serialNum, description, addDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = (endDate.getTime() - startDate.getTime())/60000;
    }

    public Event() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        System.out.print("\t" + String.format("%3d", this.serialNum));
        System.out.print(". ");
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        System.out.println(String.format("%1$-30s%2$29s",
                this.description.toString() + " (" +
                this.durationMinutes +
                "mins)", "Added: " +
                taskDate.format(this.addDate)));
        System.out.println("\t\t\tFrom     : " +
                taskDate.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                taskDate.format(this.endDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.doneDate));
        }
    }

    public Date getStartDate() {
        return (this.startDate);
    }

    public Date getEndDate() {
        return (this.endDate);
    }

    public long getDurationMinutes() {
        return (this.durationMinutes);
    }

    public String getTaskIcon() {
        return("[E]");
    }

    //ABSTRACT TASK METHODS
    @Override
    public String getDoneAhead() {
        return null;
    }
    public Double getItemBudget() {
        return null;
    }
    public Double getItemPrice() {
        return null;
    }
    public Date getTargetDate() {
        return null;
    }
    public String getWithinBudget() {
        return null;
    }
    public void markAsDone(Date doneDate, Double itemPrice){ }
}
