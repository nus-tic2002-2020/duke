package tasks;


/**
 * Represents the Parent Class Task. It has a String for the description of the task.
 * The boolean is meant to indicate if the task is done.
 * The cumulatedTasksAdded is meant to count total number of tasks added throughout the session
 *
 */

public class Task {

    private String description;
    private boolean isDone;
    private static int cumulatedTasksAdded = 0;



    public Task(String description){
        this.description = description;
        this.isDone = false;
        cumulatedTasksAdded++;
    }

    public void changeDoneTo(boolean value){
        this.isDone = value;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public String toString(){
        String symbol;
        if(getIsDone() == true){
            symbol = "\u2713";
        }else{
            symbol = "\u2717";
        }
        return ("["+ symbol + "] " + getDescription());
    }

    public int getCumulatedTasksAdded(){
        return cumulatedTasksAdded;
    }



}
