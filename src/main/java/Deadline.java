import java.util.Date;
import java.util.Scanner;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
    protected Date targetDate;
    private boolean doneAhead = false;

    //CONSTRUCTORS--------------------------------------
    public Deadline(int serialNum, String description, Date targetDate, Date addDate) throws DateException {
        super(serialNum, description, addDate);
        Date now = new Date();
        if(targetDate.compareTo(now) < 0 ){
            throw new DateException("TargetDate", true);
        } else {
            this.targetDate = targetDate;
        }
    }

    public Deadline() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setTargetDate(Date targetDate) throws DateException {
        Date now = new Date();
        if(targetDate.compareTo(now) < 0 ){
            throw new DateException("TargetDate", false);
        } else {
            this.targetDate = targetDate;
        }
    }

    @Override
    public void markAsDone(Date doneDate) {
        super.markAsDone(doneDate);
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
    }


    //GET STATEMENTS------------------------------------
    public void printList(){
        super.printList();
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
