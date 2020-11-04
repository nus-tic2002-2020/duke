/***************************************************************
 *
 *         Allows user to add deadlines tasks.
 *
 * @param output                To print our tasks
 * @param index                 Used to split the commandline for
 *                              the date and timing.
 * @param taskNumber            Used to indicate the selected
 *                              task
 * @param taskList              ArrayList storing our tasks.
 * @param dtf                   To format the date/time
 *
 * @param storage               To store values to the arraylist
 * @throws DukeException        Catching errors
 * *************************************************************/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    private DateFormat dtf = new SimpleDateFormat("d/MM/yyyy HHmm");
    private DateFormat outputDtf = new SimpleDateFormat("yyyy-MM-d hhmm aa");
    private String outputDate = "";
    Date date = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        try{
            date = dtf.parse(by);
            outputDate = outputDtf.format(date);
        }catch(ParseException e){
            System.out.println("Please provide a valid date format for the following task: " + description);
        }
        return "[D]" + super.toString() + " (by: " + outputDate + ")";
    }
}