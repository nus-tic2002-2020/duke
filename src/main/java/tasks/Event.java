package tasks;


/**
 * This represents the Event Class. It includes a String at.
 * at will represent the destination or "time" for the task. Anything that the user
 * wants to input for it.
 */

public class Event extends Task {
    protected String at;

    /**
     * This is a constructor for the Event Class.
     * @param description This describes the activity of the event.
     * @param at This is indicates the location for the event.
     */
    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * This is a accessor for the object "at"
     * @return the location of the event.
     */
    public String getAt(){
        return this.at;
    }

    /**
     * This converts the Event Object to a String for printing.
     * @return the Event written as a String
     */
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
