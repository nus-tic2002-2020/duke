package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Event.
 */

public class Event extends Task {

	protected LocalDate date;
	protected int time;

	public Event(String description, LocalDate date, int time, String userInput) {
		super(description, userInput);
		this.date = date;
		this.time = time;
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public String printTask() {
		return "[E]" + "[" + getIcon() + "] " + getTitle() + " (at: "
				+ date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " " + time + ")";
	}

}
