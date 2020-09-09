public class Task {

    protected String description;
    protected boolean isdone;

    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }

    public String symbols() {
        return (isdone ? "\u2713" : "\u2718"); // tick for yes and x for no
    }

    public void markDone() { //mark task as done
        this.isdone = true;
        this.symbols();
    }

    public String toString() {
        return ( "[" + this.symbols() + "]" + description);
    }
}
