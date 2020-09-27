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

    public void printAdd(int storeSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(getSymbol() + "[✗] " + getDescription() + " (by: " + getDateAndTime() +")");
        System.out.println("Now you have " + (storeSize) + " tasks in the list");
    }

    public void printDelete(int storeSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(getSymbol() + getDone() + " " + getDescription() + " (by: " + getDateAndTime() +")");
        System.out.println("Now you have " + (storeSize) + " tasks in the list");
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
