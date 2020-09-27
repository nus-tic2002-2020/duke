package classes;

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

    public void printAdd(int storeSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(getSymbol() + "[✗] " + getDescription());
        System.out.println("Now you have " + (storeSize) + " tasks in the list");
    }

    public void printDelete(int storeSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(getSymbol() + getDone() + " " + getDescription());
        System.out.println("Now you have " + (storeSize) + " tasks in the list");
    }

    public void setSymbol() {
        symbol = "[T]";
    }

    public String getSymbol() {
        return symbol;
    }

}
