public class Event extends Task {
    private String time;
    //protected String at;

    public Event(String description, String taskType, String time){
        super(description, taskType);
        this.time = time;
    }

    public Event(String description, String taskType, String time, boolean isDone) {
        super(description, taskType, isDone);
        this.time = time;
    }

    @Override
    public String getTaskListInfo(){

        return "[E]" + super.getTaskListInfo() + " (at: " + time + ")";
    }

    public String formatForFile() {
        return "E" + super.formatForFile() + super.getDescription();
    }

}
