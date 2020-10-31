public class Task {

    protected String description;
    protected boolean isdone;

    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }
    public Task(String description, boolean status) {
        this.description = description;
        this.isdone = status;
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

    public String toSaveString() {
        assert !description.isEmpty() : "Description field is empty";
       return  (isdone ? 1 : 0) + " | " + description;
    }
}
