package tasks;

public class Task {

    private String description;
    private boolean isDone;
    private static int totalTasks = 0;



    public Task(String description){
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    public void changeCompletedTo(boolean value){
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

    public void printTotalTasks(){
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }



}
