package seedu.duke.commands;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "]" + this.description;
    }

    public void setDone(){
        isDone = true;
    }

//    public void setDelete(){
//        System.out.println("\t____________________________________________________________");
//        System.out.println("\tNoted. I've removed this task:");
//        System.out.println("\t" + getDescription() ); //ticked symbols
//    }
}