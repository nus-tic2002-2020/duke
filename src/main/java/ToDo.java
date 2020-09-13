public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        setSymbol();
    }

    public void setDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[T][✓] " + this.description);
    }

    public void setSymbol() {
        symbol = "[T]";
    }

    public String getSymbol() {
        return symbol;
    }


}
