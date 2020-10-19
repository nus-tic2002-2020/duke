package main;

public class ToDo extends Task {

    public ToDo(String taskName, boolean taskDone)
    {
        super(taskName, taskDone); // calls the parent constructor
    }

    public String toString()
    {
        return "[T][" + "\u2718" + "] " + super.toString();
    }
}