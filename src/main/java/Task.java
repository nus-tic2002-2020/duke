public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description,String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;

    }

    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return symbols (✓ : ✘)
    }

    public String getDescription() {

        return description;
    }

    public void markAsDone(){

        isDone = true;
    }

    public String getTaskType(){
        return taskType;
    }

    public String getTaskListInfo() {

        return "[" + this.getStatusIcon() + "] " + getDescription();
    }

    public String formatForFile() {
        Integer intIsDone = isDone ? 1 : 0;
        return " | " + intIsDone + " | ";
        //return "|" + this.getStatusIcon() + "|" + this.getDescription();
    }
}
