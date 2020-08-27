import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
    SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");
    protected Date targetDate;
    protected boolean doneAhead = false;

    //CONSTRUCTORS--------------------------------------
    public Deadline(String description, Date targetDate, Date addDate) {
        super(description, addDate);
        this.targetDate = targetDate;
    }

    public Deadline() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public void markAsDone(Date doneDate) {
        this.isDone = true;
        this.doneDate = doneDate;
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        System.out.println(String.format("%1$-30s%2$29s",
                this.getDescription(), "Added: " +
                taskDate.format(this.getAddDate())));
        System.out.println("\t\t\tDeadline : " +
                taskDate.format(this.getTargetDate()));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.getDoneDate()) + " " +
                    this.getDoneAhead());
        }
    }

    public Date getTargetDate() {
        return (this.targetDate);
    }

    public String getDoneAhead() {
        if(this.doneAhead){
            return("\u2611");
        } else {
            return("\u2612");
        }
    }

    @Override
    public String getTaskIcon() {
        return("[D]");
    }

}
