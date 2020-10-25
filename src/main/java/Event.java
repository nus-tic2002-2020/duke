public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "]" + super.toString() + " (at:" + by + ")";
    }
}
