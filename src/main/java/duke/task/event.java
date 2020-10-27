package duke.task;

import java.time.LocalDate;

public class event extends Task{
    private char category;
    String time;
    LocalDate dateTime; //Y M D T

    public event(String desc, String timing){
        super(desc);
        String[] time_input = timing.split(" ", 2);
        this.time = time_input[1];
        this.category = 'E';
    }
    public String getTime(){
        return this.time;
    }
    public char getCat(){
        return this.category;
    }
}
