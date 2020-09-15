public class Deadline extends Todo {
    protected String by;

    public Deadline(String description) {
        super(description);
        by = this.description[1].substring(2).trim();
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}
