package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
* Task type: Event. 
*/

public class Event extends Task{
	
	  protected LocalDate date;

	    public Event(String description, LocalDate at, String userInput) {
	        super(description, userInput);
	        this.date = at;
	    }

	    @Override
		public LocalDate getDate() {
			return this.date;
		}
		
	    @Override
	    public String printTask() {
	        return "[E]" + "["+icon()+"] "+getTitle() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")) + ")";
	    }

}
