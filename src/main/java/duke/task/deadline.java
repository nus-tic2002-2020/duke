package duke.task;

import java.time.LocalDate;

public class deadline extends Task{
    private char category;
    String time;
    LocalDate date;

    public deadline(String desc, String timing){
        super(desc);
        String[] time_input = timing.split(" ", 2);
        this.time = time_input[1];
        this.category = 'D';
    }
    public String getTime(){
        return this.time;
    }
    public char getCat(){
        return this.category;
    }
}
