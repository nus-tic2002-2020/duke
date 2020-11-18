package duke.task;

public class Event extends Task {

    /**
     * Variables of Event class
     */
    protected String at;
    private char category;

    /**
     * Constructor of Event class
     */
    public Event(String description, String by) {
        super(description);
        String[] place = by.split(" ",2);
        this.at = place[1];
        this.category = 'E';
    }

    /**
     * Getter of category aka 'E' for event class
     */
    public char getD(){

        return this.category;
    }

    /**
     * Getter of when event is at
     */
    public String getTime(){

        return this.at;
    }

    /**
     * Override of print statement to fit event formatting
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
