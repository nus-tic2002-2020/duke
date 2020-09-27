public class Event extends Task {
    private String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskListInfo(){
        return "[E]" + super.getTaskListInfo() + " (at: " + time + ")";
    }

}
