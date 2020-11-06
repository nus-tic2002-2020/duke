package tasks;

import tasks.Task;

/**
 * Represents the "ToDo" task.
 *
 */

public class ToDo extends Task {


    /**
     * This is the constructor for ToDo
     * @param description This is the description/activity of the ToDo itself.
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * This converts the ToDo to a String that will be used for printing
     * @return String This returns the ToDo in the form of a string.
     */
    public String toString(){
        return "[T]" + super.toString();
    }
}
