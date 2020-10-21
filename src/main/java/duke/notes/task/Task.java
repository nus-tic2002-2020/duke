package duke.notes.task;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.notes.Note;
import duke.parser.DateException;
import duke.ui.DukeUI;

import java.util.Date;

/**
 * An extension of the {@code Note} object.
 *
 * To create a {@code Task} object, the same requirements as the {@code Note} object is required.
 * Class level members are available to keep count of the total outstanding and completed events.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Task extends Note {

    //VARIABLES-----------------------------------------
    protected static int tasksOutstanding;
    protected static int tasksCompleted;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Task} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Task}.
     * @param addDate The date and time the note was added.
     */
    public Task(int serialNum, String description, Date addDate) {
        super(serialNum, description, addDate);
        tasksOutstanding++;
    }

    /**
     * This method is used to initialise a {@code Task} object.
     *
     */
    @SuppressWarnings("unused")
    public Task() {
        tasksOutstanding++;
    }

    /**
     * This method is used to construct a concluded {@code Task} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Task}.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Task} had concluded.
     * @param isDone True if the {@code Task} had concluded.
     */
    public Task(int serialNum, String description, Date addDate, Date doneDate,
                boolean isDone) {
        this.serialNum = serialNum;
        this.description = description;
        this.addDate = addDate;
        this.doneDate = doneDate;
        this.isDone = isDone;
        tasksCompleted++;
    }

    /**
     * This method is used to construct an outstanding {@code Task} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Task}.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Task} had concluded.
     */
    public Task(int serialNum, String description, Date addDate,
                boolean isDone) {
        this.serialNum = serialNum;
        this.description = description;
        this.addDate = addDate;
        this.isDone = isDone;
        tasksOutstanding++;
    }


    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Task} object as completed.
     * Class-level members will be updated to reflect the change in completion status.
     *
     * @param doneDate The date and time the {@code Task} had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    @Override
    public boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException {
        if(super.markAsDone(doneDate)) {
            tasksOutstanding--;
            tasksCompleted++;

            this.printList();
            return true;
        } else {
            this.printList();
            return false;
        }
    }

    /**
     * This method is used to delete an {@code Task} object.
     * Class-level members will be updated to reflect the change in completion status.
     */
    public void deleteExistingNote() {
        if(isDone){
            System.out.print("    Note #" + this.serialNum + " was already done!");
            System.out.println("\t...deleting the task anyway.");
            tasksCompleted--;
        } else {
            System.out.println("    Noted! I've deleted Note #" + this.serialNum + ".");
            tasksOutstanding--;
        }
    }

    /**
     * This method is used to reset the static variables of the {@code Task}
     * class in the event of a program reset.
     */
    public static void resetStaticVariables() {
        tasksOutstanding = 0;
        tasksCompleted = 0;
    }


    //GET STATEMENTS------------------------------------
    /**
     * This method is used to retrieve the number of outstanding {@code Task} objects.
     *
     * @return int The number of outstanding {@code Task} objects.
     */
    public static int getTasksOutstanding() {
        return (tasksOutstanding);
    }

    /**
     * This method is used to retrieve the number of completed {@code Task} objects.
     *
     * @return int The number of completed {@code Task} objects.
     */
    public static int getTasksCompleted() {
        return (tasksCompleted);
    }

    /**
     * This method exports the {@code Task} object as a string in a format that is
     * readable and re-constructable as a {@code Task} object.
     *
     * @return String The {@code Task} object as a string in a format
     * readable and re-constructable as a {@code Task} object.
     */
    public String getSaveText() {
        String text = "Task/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone;

        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate);
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Task} object as a {@code String}.
     *
     * @return String The class name of the {@code Task} object.
     */
    @Override
    public String getObjectClass() {
        return "Task";
    }


    //ABSTRACT METHODS----------------------------------
    /**
     * This method returns the {@code Budget} object attached to the {@code Task} object, if any.
     *
     * @return Budget The {@code Budget} object attached to the {@code Task} object, if any.
     */
    public Budget getBudgetObject() {
        return null;
    }
}
