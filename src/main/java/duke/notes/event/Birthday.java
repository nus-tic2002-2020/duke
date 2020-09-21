package duke.notes.event;

import duke.commands.DateException;
import java.util.Date;

/**
 * An extension of the {@code Event} object that labels the event as a {@code Birthday} object.
 *
 * To create a {@code Birthday} object, the same requirements as the {@code Event} object is required.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class Birthday extends Event {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code Birthday} object.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Birthday}.
     * @param startDate The start date and time of the {@code Birthday}.
     * @param endDate The end date and time of the {@code Birthday}.
     * @param addDate The date and time the note was added.
     */
    public Birthday(int serialNum, String description, Date startDate, Date endDate,
                    Date addDate) throws DateException {
        super(serialNum, description, startDate, endDate, addDate);

    }

    /**
     * This method is used to initialise a {@code Birthday} object.
     *
     */
    public Birthday() { super(); }

    /**
     * This method is used to construct a concluded {@code Birthday} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Birthday}.
     * @param addDate The date and time the note was added.
     * @param doneDate The date and time the {@code Birthday} had concluded.
     * @param isDone True if the {@code Birthday} had concluded.
     * @param startDate The start date and time of the {@code Birthday}.
     * @param endDate The end date and time of the {@code Birthday}.
     * @param durationMinutes The duration of the {@code Birthday} in minutes.
     */
    public Birthday(int serialNum, String description, Date addDate, Date doneDate,
                    boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        super(serialNum, description, addDate, doneDate, isDone, startDate, endDate, durationMinutes);
    }

    /**
     * This method is used to construct an outstanding {@code Birthday} object from saved files.
     *
     * @param serialNum The serial number automatically assigned for identification purposes.
     * @param description The description of the {@code Birthday}.
     * @param addDate The date and time the note was added.
     * @param isDone True if the {@code Birthday} had concluded.
     * @param startDate The start date and time of the {@code Birthday}.
     * @param endDate The end date and time of the {@code Birthday}.
     * @param durationMinutes The duration of the {@code Birthday} in minutes.
     */
    public Birthday(int serialNum, String description, Date addDate,
                    boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        super(serialNum, description, addDate, isDone, startDate, endDate, durationMinutes);
    }

    //SET STATEMENTS------------------------------------


    //GET STATEMENTS------------------------------------
    /**
     * This method exports the {@code Birthday} object as a string in a format that is
     * readable and re-constructable as a {@code Birthday} object.
     *
     * @return String The {@code Birthday} object as a string in a format
     * readable and re-constructable as a {@code Birthday} object.
     */
    @Override
    public String getSaveText() {
        String text = "Birthday/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_TIME.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_TIME.format(this.startDate) + "/" +
                INPUT_TIME.format(this.endDate) + "/" +
                this.getDurationMinutes();

        if(isDone) {
            text = text + "/" + INPUT_TIME.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }

    /**
     * This method returns the class name of the {@code Birthday} object as a {@code String}.
     *
     * @return String The class name of the {@code Birthday} object.
     */
    @Override
    public String getObjectClass() {
        return "Birthday";
    }
}
