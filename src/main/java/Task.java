public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

   // public Task() {
     //   this.list = new String[100];
       // this.done = new boolean[100];
    //}

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //... do i need anything here?
    public void setDone (boolean state){
        this.isDone = state;
    }
}