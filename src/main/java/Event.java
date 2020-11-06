import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //private String time;
    private LocalDate date;
    private LocalTime timing;

    public Event(String description, String taskType, LocalDate date, LocalTime timing){
        super(description, taskType);
        this.date = date;
        this.timing = timing;
        //this.time = time;
    }

    public Event(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
        this.date = date;
        this.timing = timing;
        //this.time = time;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String getTaskListInfo(){
        return "[E]" + super.getTaskListInfo() + " at " + this.getDate() + " " + getTiming();
        //return "[E]" + super.getTaskListInfo() + " (at: " + time + ")";
    }

   /* public String formatForFile() {
        return "E" + super.formatForFile() + super.getDescription();
    }*/

}
