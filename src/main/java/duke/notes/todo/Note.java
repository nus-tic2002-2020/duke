package duke.notes.todo;


import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import duke.ui.*;
import java.util.Date;

public abstract class Note implements Task {

    //VARIABLES-----------------------------------------
    protected int serialNum;
    protected String description;
    protected Date addDate;
    protected Date doneDate = null;
    protected boolean isDone = false;


    //CONSTRUCTORS--------------------------------------
    public Note(int serialNum, String description, Date addDate) {
        this.serialNum = serialNum;
        this.description = description;
        this.addDate = addDate;
    }

    public Note() {}


    //SET STATEMENTS------------------------------------
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if(this.isDone) {
            System.out.println("\tNote #" + this.serialNum + " was already done!");
            return false;
        } else {
            this.isDone = true;
            this.doneDate = doneDate;
            System.out.println("\tNoted! I've marked Note #" + this.serialNum + " as done.");
            return true;
        }
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //GET STATEMENTS------------------------------------
    public int getSerialNum() {
        return (this.serialNum);
    }

    public String getDescription() {
        return (this.description.toString());
    }

    public Date getAddDate() {
        return (this.addDate);
    }

    public boolean getIsDone() {
        return (this.isDone);
    }

    public Date getDoneDate() {
        return (this.doneDate);
    }

    public void printList() throws CommandException {
        System.out.print("\t" + String.format("%3d", this.serialNum));
        System.out.print(". ");
        System.out.print(this.getTaskIcon());
        System.out.print(this.getStatusIcon() + " ");
        DukeUI.listWrap(this.description, 25, this.addDate);
        printDetails();
    }

    public void printDetails(){
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    TASK_DATE.format(this.doneDate));
        }
    }

    public String getStatusIcon() {
        if(this.isDone){
            return ("[\u2713]");
        } else {
            return ("[\u2718]");
        }
    }


    //ABSTRACT METHODS----------------------------------
    public abstract void deleteExistingNote();
    public abstract Budget getBudgetObject();
    public abstract String getTaskIcon() throws CommandException;
    public abstract String getObjectClass();
}
