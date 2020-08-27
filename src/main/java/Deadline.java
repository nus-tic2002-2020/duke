import java.util.Date;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
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
