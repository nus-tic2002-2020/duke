package main;

public class Deadline extends Task{
    protected String by;
    public Deadline (String description, boolean isDone, String by)
    {
        super(description, isDone); // calls the parent constructor
        this.by = by;
    }

    public String toString()
    {
        return "[D][" + "\u2718" + "] " + super.toString() + " (by : "+ by + ")";
    }
}