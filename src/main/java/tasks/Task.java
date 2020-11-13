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
    protected static int cumulatedTasksAdded = 0;


    /**
     * This is a constructor for a Task
     * @param description This describes the activity of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
        cumulatedTasksAdded++;
    }


    /**
     * This changes the isDone boolean to the parameter "value". Which is either true or false.
     * If true, it means the task is comepleted.
     * @param value This is the boolean value which indicates if the task is comepleted or not
     */
    public void changeDoneTo(boolean isDone){
        this.isDone = isDone;
    }


    /**
     * This is an accessor of the description
     * @return the description of the task.
     * @throws
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * This is an accessor of the isDone boolean.
     * @return if the task is done or not
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * This converts the Task to a String for printing
     * @return the Task in the form of a String
     * @throws
     */
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
