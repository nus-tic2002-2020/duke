package duke.notes.event;

import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import duke.notes.todo.*;
import java.util.Date;

public class Event extends Note {

    //VARIABLES-----------------------------------------
    protected Date startDate = null;
    protected Date endDate = null;
    private long durationMinutes;
    protected static int eventsOutstanding;
    protected static int eventsCompleted;


    //CONSTRUCTORS--------------------------------------
    public Event(int serialNum, String description, Date startDate, Date endDate, Date addDate) throws DateException {
        super(serialNum, description, addDate);
        eventsOutstanding++;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
    }

    public Event() {
        eventsOutstanding++;
    }

    public Event(int serialNum, String description, Date addDate, Date doneDate,
                 boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        this.serialNum = serialNum;
        this.description = description;
        this.addDate = addDate;
        this.doneDate = doneDate;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = durationMinutes;
        eventsCompleted++;
    }

    public Event(int serialNum, String description, Date addDate,
                 boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        this.serialNum = serialNum;
        this.description = description;
        this.addDate = addDate;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = durationMinutes;
        eventsOutstanding++;
    }


    //SET STATEMENTS------------------------------------
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException {
        if(super.markAsDone(doneDate)) {
            eventsOutstanding--;
            eventsCompleted++;
            this.printList();
            return true;
        } else {
            this.printList();
            return false;
        }
    }

    public void deleteExistingNote() {
        if(isDone){
            System.out.print("\tEvent #" + this.serialNum + " was already done!");
            System.out.println("\t...deleting the event anyway.");
            eventsCompleted--;
        } else {
            System.out.println("\tNoted! I've deleted Event #" + this.serialNum + ".");
            eventsOutstanding--;
        }
    }

    public void setStartDate(Date startDate) throws DateException {
        Date now = new Date();
        if(startDate.compareTo(now) < 0 ){
            throw new DateException(startDate, "StartB4Now");
        } else {
            if(this.endDate != null) {
                if(startDate.compareTo(this.endDate) > 0 ) {
                    throw new DateException(startDate, "StartAFEnd");
                } else {
                    this.startDate = startDate;
                    this.durationMinutes = (this.endDate.getTime() - startDate.getTime()) / 60000;
                }
            } else {
                this.startDate = startDate;
            }
        }
    }

    public void setEndDate(Date endDate) throws DateException {
        if(this.startDate == null){
            throw new DateException(endDate, "NoStartDate");
        } else {
            if(endDate.compareTo(this.startDate) > 0 ){
                throw new DateException(endDate, "EndB4Start");
            } else {
                this.endDate = endDate;
                this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
            }
        }
    }

    public static void resetStaticVariables() {
        eventsOutstanding = 0;
        eventsCompleted = 0;
    }

    //GET STATEMENTS------------------------------------
    public Date getStartDate() { return (this.startDate); }

    public Date getEndDate() { return (this.endDate); }

    public long getDurationMinutes() {
        return (this.durationMinutes);
    }

    @Override
    public void printDetails(){
        System.out.println("\t\t\tDuration : " +
                String.format("%,5d", this.durationMinutes) +  "mins");
        System.out.println("\t\t\tFrom     : " +
                TASK_DATE.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                TASK_DATE.format(this.endDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    TASK_DATE.format(this.doneDate));
        }
    }

    @Override
    public String getTaskIcon() throws CommandException {
        return NoteType.getTaskIcon("Event");
    }

    public static int getEventsOutstanding() {
        return (eventsOutstanding);
    }

    public static int getEventsCompleted() {
        return (eventsCompleted);
    }

    public String getSaveText() {
        String text = "Event/" +
                this.serialNum + "/" +
                this.description.toString() + "/" +
                INPUT_DATE.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_DATE.format(this.startDate) + "/" +
                INPUT_DATE.format(this.endDate) + "/" +
                this.durationMinutes;
        if(isDone) {
            text = text + "/" + INPUT_DATE.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }

    @Override
    public String getObjectClass() {
        return "Event";
    }

    //ABSTRACT METHODS----------------------------------
    public Budget getBudgetObject() {
        return null;
    }

}
