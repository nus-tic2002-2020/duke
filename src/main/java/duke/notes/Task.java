package duke.notes;

import duke.budget.Budget;
import duke.commands.CommandException;
import duke.commands.DateException;
import duke.ui.DukeUI;
import java.util.Date;

/**
 * An interface that lists all the methods that are required by objects classified as {@code Tasks}.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public interface Task extends DukeUI {

    //SET STATEMENTS------------------------------------
    /**
     * This method is used to mark an outstanding {@code Task} object as completed.
     *
     * @param doneDate The date and time the {@code Note} had concluded.
     * @return boolean True if the operation is successful.
     * @exception CommandException If there are errors in the command input.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    boolean markAsDone(Date doneDate) throws CommandException, DateException, InterruptedException;

    /**
     * This method is used to assign or reassign serial numbers to {@code Task} objects.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     */
    void setSerialNum(int serialNum);

    /**
     * This method is used to set or edit the description of {@code Task} objects.
     *
     * @param description The description of the {@code Task} object.
     */
    void setDescription(String description);

    //GET STATEMENTS------------------------------------
    /**
     * This method is used to retrieve serial numbers of the {@code Task} object.
     *
     * @return int The serial number automatically assigned for identification purposes.
     */
    int getSerialNum();

    /**
     * This method is used to retrieve the description of the {@code Task} object.
     *
     * @return String The description of the {@code Task} object.
     */
    String getDescription();

    /**
     * This method is used to retrieve the {@code Date} object reflecting
     * the date the {@code Task} object was added.
     *
     * @return Date The {@code Task} object reflecting the date the {@code Note} object was added.
     */
    Date getAddDate();

    /**
     * This method is used to retrieve the completion status of the {@code Task} object.
     *
     * @return boolean True if the {@code Task} object was completed.
     */
    boolean getIsDone();

    /**
     * This method is used to print details of the {@code Task} object.
     *
     * @exception CommandException If there are errors in the command input.
     */
    void printList() throws CommandException;

    /**
     * This method is used to delete existing {@code Task} object and make associated adjustments.
     */
    void deleteExistingNote();

    /**
     * This method exports the {@code Task} object as a string in a format that is
     * readable and re-constructable as a {@code Task} object.
     *
     * @return String The {@code Task} object as a string in a format
     * readable and re-constructable as a {@code Task} object.
     */
    String getSaveText();

    /**
     * This method returns the class name of the {@code Task} object as a {@code String}.
     *
     * @return String The class name of the {@code Task} object.
     */
    String getObjectClass();

    /**
     * This method returns the {@code Budget} object attached to the {@code Task} object, if any.
     *
     * @return Budget The {@code Budget} object attached to the {@code Task} object, if any.
     */
    Budget getBudgetObject();

}
