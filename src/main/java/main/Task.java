package main;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task (String description, boolean isDone)
    {
        this.description = description;
        this.isDone = isDone;
    }
    public String Description()
    {
        return description;
    }
    public void Description(String description)
    {
        this.description = description;
    }
    public boolean getIsDone()
    {
        return isDone;
    }
    public void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }
    public String toString()
    {
        //return description;
        return description;
    }
    public String getStatusIcon()
    {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone()
    {
        isDone = true;
    }
}