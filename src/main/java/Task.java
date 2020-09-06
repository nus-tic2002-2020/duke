public class Task {

    //TODO: toString override

    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
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

}
