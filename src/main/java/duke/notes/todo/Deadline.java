package duke.notes.todo;


import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import java.util.Date;

public class Deadline extends Todo {

    //VARIABLES-----------------------------------------
    protected Date targetDate;
    protected boolean doneAhead = false;
    protected int timesExtended = 0;


    //CONSTRUCTORS--------------------------------------
    public Deadline(int serialNum, String description, Date targetDate,
                    Date addDate) {
        super(serialNum, description, addDate);
        this.targetDate = targetDate;
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
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public boolean markAsDone(Date doneDate) throws CommandException {
        super.markAsDone(doneDate);
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
        return false;
    }


    //GET STATEMENTS------------------------------------
    @Override
    public void printDetails(){
        System.out.print("\t\t\tDeadline : " +
                TASK_DATE.format(this.targetDate));
        if(this.timesExtended > 0){
            System.out.print("(" + this.timesExtended +
                    " extensions)\n");
        } else {
            System.out.print("\n");
        }
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
    public String getTaskIcon() throws CommandException {
        return NoteType.getTaskIcon("Deadline");
    }

    @Override
    public String getSaveText() {
        String text = "Deadline/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_DATE.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_DATE.format(this.targetDate) + "/" +
                this.doneAhead;

        if(isDone) {
            text = text + "/" + INPUT_DATE.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }

    @Override
    public String getObjectClass() {
        return "Deadline";
    }

    //ABSTRACT METHODS----------------------------------
    public Budget getBudgetObject() {
        return null;
    }
}
