import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
    protected Date targetDate;
    private boolean doneAhead = false;

    //CONSTRUCTORS--------------------------------------
    public Deadline(int serialNum, String description, Date targetDate, Date addDate) {
        super(serialNum, description, addDate);
        this.targetDate = targetDate;
    }

    public Deadline() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public void setDoneAhead(Date doneDate) {
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        System.out.print("\t" + String.format("%3d", this.serialNum));
        System.out.print(". ");
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        System.out.println(String.format("%1$-30s%2$29s",
                this.description.toString(), "Added: " +
                taskDate.format(this.addDate)));
        System.out.println("\t\t\tDeadline : " +
                taskDate.format(this.targetDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.doneDate) + " " +
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
