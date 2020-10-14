package classes;

public class Event extends Task {
    private String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public void setDone() {
        this.done = true;
    }

    public void setSymbol() {
        symbol = "[E]";
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getDateAndTime() {
        return dateAndTime;
    }
}
