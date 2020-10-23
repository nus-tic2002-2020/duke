package seedu.duke.commands;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (at: " + at + ")";
    }
}