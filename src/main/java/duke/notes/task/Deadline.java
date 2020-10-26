package duke.notes.task;


import duke.notes.budget.Budget;
import duke.commands.CommandException;
import duke.parser.DateException;
import duke.ui.DukeUI;

import java.util.Date;

/**
 * An extension of the {@code Task} object that includes a target date for which the (@code Deadline}
 * object is to be completed. The (@code Deadline} object also includes performance measurements.
 *
 * To create a {@code Deadline} object, a target date and time is required on top of
 * the requirements of the {@code Task} object.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Deadline extends Task {

    //VARIABLES-----------------------------------------
    protected Date targetDate;
    protected boolean doneAhead = false;
    protected int timesExtended = 0;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Deadline} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Deadline}.
     * @param targetDate The target date and time of the {@code Deadline}.
     * @param addDate The date and time the note was added.
     */
    public Deadline(int serialNum, String description, Date targetDate,
                    Date addDate) {
        super(serialNum, description, addDate);
        this.targetDate = targetDate;
    }

    /**
     * This method is used to initialise a {@code Deadline} object.
     *
     */
    @SuppressWarnings("unused")
    public Deadline() {
        super();
    }

    /**
     * This method is used to construct a concluded {@code Deadline} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Deadline}.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Deadline} had concluded.
     * @param isDone True if the {@code Deadline} had concluded.
     * @param targetDate The target date and time of the {@code Deadline}.
     * @param doneAhead True if the {@code Deadline} had concluded ahead of the target date.
     */
    public Deadline(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone, Date targetDate, boolean doneAhead) {
        super(serialNum, description, addDate, doneDate, isDone);
        this.targetDate = targetDate;
        this.doneAhead = doneAhead;
    }

    /**
     * This method is used to construct a concluded {@code Deadline} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Deadline}.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Deadline} had concluded.
     * @param targetDate The target date and time of the {@code Deadline}.
     * @param doneAhead True if the {@code Deadline} had concluded ahead of the target date.
     */
    public Deadline(int serialNum, String description, Date addDate,
                    boolean isDone, Date targetDate, boolean doneAhead) {
        super(serialNum, description, addDate, isDone);
        this.targetDate = targetDate;
        this.doneAhead = doneAhead;
    }


    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Deadline} object as completed.
     * Class-level members will be updated to reflect the change in completion status.
     *
     * @param doneDate The date and time the {@code Deadline} had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException {
        super.markAsDone(doneDate);
        if(doneDate.compareTo(this.targetDate) < 0) {
            this.doneAhead = true;
        }
        return false;
    }

    /**
     * This method is used to set or edit the target date assigned to the {@code Deadline} object.
     *
     * @param targetDate The target date and time of the {@code Deadline}.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    public void setTargetDate(Date targetDate) throws DateException {

        Date now = new Date();
        if(targetDate.before(now)){
            throw new DateException(targetDate, "TargetDate");
        } else {
            this.targetDate = targetDate;
            this.timesExtended++;
        }
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to print details of the {@code Deadline} object.
     */
    @Override
    public void printDetails(){
        System.out.print("            Deadline : " +
                DukeUI.NOTE_TIME.format(this.targetDate));
        if(this.timesExtended > 0){
            System.out.print(" (" + this.timesExtended +
                    " extensions)\n");
        } else {
            System.out.print("\n");
        }
        if (this.isDone) {
            System.out.println("            Done     : " +
                    DukeUI.NOTE_TIME.format(this.doneDate) + " " +
                    this.getDoneAhead());
        }
    }

    /**
     * This method is used to retrieve the {@code Date} object reflecting
     * the target date of the {@code Deadline} object.
     *
     * @return Date The {@code Date} object reflecting the target date of the {@code Deadline} object.
     */
    public Date getTargetDate() {
        return (this.targetDate);
    }

    /**
     * This method is used to retrieve the performance status of the {@code Deadline} object.
     *
     * @return boolean True if the {@code Deadline} had concluded ahead of the target date.
     */
    public String getDoneAhead() {
        if(this.doneAhead){
            return("\u2611");
        } else {
            return("\u2612");
        }
    }

    /**
     * This method exports the {@code Deadline} object as a string in a format that is
     * readable and re-constructable as a {@code Deadline} object.
     *
     * @return String The {@code Deadline} object as a string in a format
     * readable and re-constructable as a {@code Deadline} object.
     */
    @Override
    public String getSaveText() {
        String text = "Deadline/" +
                this.serialNum + "/" +
                this.description + "/" +
                DukeUI.INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                DukeUI.INPUT_TIME.format(this.targetDate) + "/" +
                this.doneAhead;

        if(isDone) {
            text = text + "/" + DukeUI.INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Deadline} object as a {@code String}.
     *
     * @return String The class name of the {@code Deadline} object.
     */
    @Override
    public String getObjectClass() {
        return "Deadline";
    }

    //ABSTRACT METHODS----------------------------------
    /**
     * This method returns the {@code Budget} object attached to the {@code Deadline} object, if any.
     *
     * @return Budget The {@code Budget} object attached to the {@code Deadline} object, if any.
     */
    public Budget getBudgetObject() {
        return null;
    }
}
