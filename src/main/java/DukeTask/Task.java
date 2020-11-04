package DukeTask;

public class Task {

    protected String description;
    protected boolean isdone;

    /**
     *
     * @param description task input by user
     */
    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }
    public Task(String description, boolean status) {
        this.description = description;
        this.isdone = status;
    }

    /**
     *
     * @return ✓ or ✘ depending if task is completed
     */
    public String symbols() {
        return (isdone ? "\u2713" : "\u2718");
    }

    public void markDone() { //mark task as done
        this.isdone = true;
        this.symbols();
    }

    public String toString() {
        assert !description.isBlank() : "No Description";
        return ( "[" + this.symbols() + "]" + description);
    }

    /**
     *
     * @return convert to 1 or 0 in txt file for task completion since text file unable to capture tick and cross
     */
    public String toSaveString() {
        assert !description.isEmpty() : "Description field is empty";
        return  + (isdone ? 1 : 0) + " | " + description;
    }
}
