package tasklist;

public class Events extends Task {

    protected String byDateTime;


    public Events(String description, String byDateTime) {
        super(description);
        this.byDateTime = byDateTime;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.byDateTime + ")";
    }

    @Override
    public String saveFormat() { return "E" + super.saveFormat() + "|" + this.byDateTime; }

}
