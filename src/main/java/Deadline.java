import java.util.Date;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
    protected Date targetDate;
    private boolean doneAhead = false;


    //CONSTRUCTORS--------------------------------------
    public Deadline(int serialNum, String description, Date targetDate, Date addDate) throws DateException {
        super(serialNum, description, addDate);
        Date now = new Date();
        if(targetDate.compareTo(now) < 0 ){
            throw new DateException("TargetDate", "Todo", true);
        } else {
            this.targetDate = targetDate;
        }
    }

    public Deadline() {
        super();
    }

    public Deadline(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Date targetDate, boolean doneAhead) {
        super(serialNum, description, addDate, doneDate, isDone);
        this.targetDate = targetDate;
        this.doneAhead = doneAhead;
    }

    public Deadline(int serialNum, String description, Date addDate,
                    boolean isDone, Date targetDate, boolean doneAhead) {
        super(serialNum, description, addDate, isDone);
        this.targetDate = targetDate;
        this.doneAhead = doneAhead;
    }


    //SET STATEMENTS------------------------------------
    public void setTargetDate(Date targetDate) throws DateException {
        Date now = new Date();
        if(targetDate.compareTo(now) < 0 ){
            throw new DateException("TargetDate", "Todo", false);
        } else {
            this.targetDate = targetDate;
        }
    }

    @Override
    public boolean markAsDone(Date doneDate) {
        super.markAsDone(doneDate);
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
        return false;
    }


    //GET STATEMENTS------------------------------------
    @Override
    public void printDetails(){
        System.out.println("\t\t\tDeadline : " +
                TASK_DATE.format(this.targetDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    TASK_DATE.format(this.doneDate) + " " +
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

    @Override
    public String getSaveText() {
        String text = "Deadline/" +
                this.serialNum + "/" +
                this.description.toString() + "/" +
                INPUT_DATE.format(this.addDate) + "/" +
                this.isDone + "/" +
                this.targetDate + "/" +
                this.doneAhead;

        if(isDone) {
            text = text + "/" + INPUT_DATE.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }
}
