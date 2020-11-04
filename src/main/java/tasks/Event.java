package tasks;


/**
 * This represents the Event Class. It includes a String at.
 * at will represent the destination or "time" for the task. Anything that the user
 * wants to input for it.
 */

public class Event extends Task {
    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public String getAt(){
        return this.at;
    }

    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
