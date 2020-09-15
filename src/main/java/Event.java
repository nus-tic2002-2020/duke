public class Event extends Todo{

    protected String at;

    public Event(String description) {
        super(description);
        at = this.description[1].substring(2).trim();
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.getDescription(), this.getAt());
    }
}
