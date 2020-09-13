public class Deadline extends Task {
    private String dateAndTime;

    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public void setDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[D][✓] " + this.description + " (by: " + getDateAndTime() + ")");
    }

    public void setSymbol() {
        symbol = "[D]";
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getDateAndTime() {
        return dateAndTime;
    }
}
