package duke.task;

public class Deadline extends Task {

    /**
     * variables in Deadline class
     */
    protected String by;
    private char category;

    /**
     * Constructor of deadline class
     * @Param description is the task description
     * @Param by is the deadline due
     */
    public Deadline(String description, String by) {
        super(description);
       String[] time = by.split(" ",2);
       this.by = time[1];
        this.category = 'D';
    }

    /**
     * Getter of deadline due
     */
    public  String getTime(){

        return this.by;
    }

    /**
     * Getter of character D which represents deadline
     */
    public char getD(){

        return this.category;
    }

    /**
     * Override of print in deadline format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

