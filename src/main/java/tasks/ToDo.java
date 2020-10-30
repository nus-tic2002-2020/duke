package tasks;

import tasks.Task;

public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
