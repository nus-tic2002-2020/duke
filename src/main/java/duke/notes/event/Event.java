package duke.notes.event;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
import duke.notes.Note;
import java.util.Date;

/**
 * An extension of the {@code Note} object that records an event with a start date and time,
 * end date and time as well as duration in minutes.
 *
 * To create an {@code Event} object, a start date and time as well as end date and time
 * must be provided in addition to the requirements by the {@code Note} object.
 * Class level members are available to keep count of the total outstanding and completed events.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Event extends Note {

    //VARIABLES-----------------------------------------
    protected Date startDate = null;
    protected Date endDate = null;
    protected long durationMinutes;
    protected static int eventsOutstanding;
    protected static int eventsCompleted;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Event} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Event}.
     * @param startDate The start date and time of the {@code Event}.
     * @param endDate The end date and time of the {@code Event}.
     * @param addDate The date and time the note was added.
     */
    public Event(int serialNum, String description, Date startDate, Date endDate, Date addDate) throws DateException {
        super(serialNum, description, addDate);
        eventsOutstanding++;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
    }

    /**
     * This method is used to initialise a {@code Event} object.
     *
     */
    public Event() {
        eventsOutstanding++;
    }

    /**
     * This method is used to construct a concluded {@code Event} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Event}.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Event} had concluded.
     * @param isDone True if the {@code Event} had concluded.
     * @param startDate The start date and time of the {@code Event}.
     * @param endDate The end date and time of the {@code Event}.
     * @param durationMinutes The duration of the {@code Event} in minutes.
     */
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

    /**
     * This method is used to construct an outstanding {@code Event} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Event}.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Event} had concluded.
     * @param startDate The start date and time of the {@code Event}.
     * @param endDate The end date and time of the {@code Event}.
     * @param durationMinutes The duration of the {@code Event} in minutes.
     */
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
    /**
     * This method is used to mark an outstanding {@code Event} object as completed.
     * Class-level members will be updated to reflect the change in completion status.
     *
     * @param doneDate The date and time the {@code Event} had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException {
        if(doneDate.before(this.startDate)) {
            throw new DateException(doneDate, "DoneB4Start", this);
        } else if(super.markAsDone(doneDate)) {
            eventsOutstanding--;
            eventsCompleted++;
            this.printList();
            return true;
        } else {
            this.printList();
            return false;
        }
    }

    /**
     * This method is used to delete an {@code Event} object.
     * Class-level members will be updated to reflect the change in completion status.
     */
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

    /**
     * This method is used to set or edit the start date and time of the {@code Event} object.
     *
     * @param startDate The start date and time of the {@code Event}.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    public void setStartDate(Date startDate) throws DateException {
        Date now = new Date();
        if(startDate.before(now)){
            throw new DateException(startDate, "StartB4Now");
        } else {
            if(this.endDate != null) {
                if(startDate.after(this.endDate)) {
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

    /**
     * This method is used to set or edit the end date and time of the {@code Event} object.
     *
     * @param endDate The end date and time of the {@code Event}.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    public void setEndDate(Date endDate) throws DateException {
        Date now = new Date();
        if(this.startDate == null){
            throw new DateException(endDate, "NoStartDate");
        } else {
            if(endDate.before(this.startDate)){
                throw new DateException(endDate, "EndB4Start");
            } else if(endDate.before(now)) {
                throw new DateException(endDate, "EndB4Now");
            } else {
                this.endDate = endDate;
                this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
            }
        }
    }

    /**
     * This method is used to reset the static variables of the {@code Event}
     * class in the event of a program reset.
     */
    public static void resetStaticVariables() {
        eventsOutstanding = 0;
        eventsCompleted = 0;
    }

    //GET STATEMENTS------------------------------------
    /**
     * This method is used to retrieve the start date and time of the {@code Event} object.
     *
     * @return Date The start date and time of the {@code Event}.
     */
    public Date getStartDate() { return (this.startDate); }

    /**
     * This method is used to retrieve the end date and time of the {@code Event} object.
     *
     * @return Date The end date and time of the {@code Event}.
     */
    public Date getEndDate() { return (this.endDate); }

    public long getDurationMinutes() {
        return (this.durationMinutes);
    }

    /**
     * This method is used to print detailed information of the {@code Event} object.
     */
    @Override
    public void printDetails(){
        System.out.println("\t\t\tDuration : " +
                String.format("%,5d", this.durationMinutes) +  "mins");
        System.out.println("\t\t\tFrom     : " +
                TASK_TIME.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                TASK_TIME.format(this.endDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    TASK_TIME.format(this.doneDate));
        }
    }

    /**
     * This method is used to retrieve the number of outstanding {@code Event} objects.
     *
     * @return int The number of outstanding {@code Event} objects.
     */
    public static int getEventsOutstanding() {
        return (eventsOutstanding);
    }

    /**
     * This method is used to retrieve the number of completed {@code Event} objects.
     *
     * @return int The number of completed {@code Event} objects.
     */
    public static int getEventsCompleted() {
        return (eventsCompleted);
    }

    /**
     * This method exports the {@code Event} object as a string in a format that is
     * readable and re-constructable as a {@code Event} object.
     *
     * @return String The {@code Event} object as a string in a format
     * readable and re-constructable as a {@code Event} object.
     */
    public String getSaveText() {
        String text = "Event/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_TIME.format(this.startDate) + "/" +
                INPUT_TIME.format(this.endDate) + "/" +
                this.durationMinutes;
        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Event} object as a {@code String}.
     *
     * @return String The class name of the {@code Event} object.
     */
    @Override
    public String getObjectClass() {
        return "Event";
    }

    //ABSTRACT METHODS----------------------------------
    /**
     * This method returns the {@code Budget} object attached to the {@code Event} object, if any.
     *
     * @return Budget The {@code Budget} object attached to the {@code Event} object, if any.
     */
    public Budget getBudgetObject() {
        return null;
    }

}
