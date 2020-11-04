package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
* Task type: Deadline. 
*/

public class Deadline extends Task{
	
	  protected LocalDate date;

	    public Deadline(String description, LocalDate by, String userInput) {
	        super(description, userInput);
	        this.date = by;
	    }

	    @Override
		public LocalDate getDate() {
			return this.date;
		}
		
	    @Override
	    public String printTask() {
	        return "[D]" + "["+icon()+"] "+getTitle() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")) + ")";
	    }

}
