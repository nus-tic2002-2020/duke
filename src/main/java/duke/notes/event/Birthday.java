package duke.notes.event;

import duke.commands.CommandException;
import duke.commands.DateException;
import duke.notes.NoteType;
import java.util.Date;


public class Birthday extends Event {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    public Birthday(int serialNum, String description, Date startDate, Date endDate,
                    Date addDate) throws DateException {
        super(serialNum, description, startDate, endDate, addDate);

    }

    public Birthday() { super(); }

    public Birthday(int serialNum, String description, Date addDate, Date doneDate,
                    boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        super(serialNum, description, addDate, doneDate, isDone, startDate, endDate, durationMinutes);
    }

    public Birthday(int serialNum, String description, Date addDate,
                    boolean isDone, Date startDate, Date endDate, long durationMinutes) {
        super(serialNum, description, addDate, isDone, startDate, endDate, durationMinutes);
    }

    //SET STATEMENTS------------------------------------


    //GET STATEMENTS------------------------------------
    @Override
    public String getTaskIcon() throws CommandException {
        return NoteType.getTaskIcon("Birthday");
    }

    @Override
    public String getObjectClass() {
        return "Birthday";
    }

    @Override
    public String getSaveText() {
        String text = "Birthday/" +
                this.serialNum + "/" +
                this.description + "/" +
                INPUT_DATE.format(this.addDate) + "/" +
                this.isDone + "/" +
                INPUT_DATE.format(this.startDate) + "/" +
                INPUT_DATE.format(this.endDate) + "/" +
                this.getDurationMinutes();

        if(isDone) {
            text = text + "/" + INPUT_DATE.format(this.doneDate) + "\n";
        } else {
            text = text + "\n";
        }
        return text;
    }
}
