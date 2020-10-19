package main;

public class Event extends Task{
    protected String at;
    public Event (String taskName, boolean taskDone, String at)
    {
        super(taskName, taskDone); // calls the parent constructor
        this.at = at;
    }

    public String toString()
    {
        return "[E][" + "\u2718" + "] " + super.toString() + " (at : "+ at + ")";
    }

}