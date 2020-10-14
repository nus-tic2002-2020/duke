package classes;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        setSymbol();
    }

    public void setDone() {
        this.done = true;
    }

    public void setSymbol() {
        symbol = "[T]";
    }

    public String getSymbol() {
        return symbol;
    }
}
