package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * variables in Deadline class
     */
    protected String by;
    private char category;
    LocalDate date;
    private String description;

    /**
     * Constructor of deadline class
     * @Param description is the task description
     * @Param by is the deadline due
     */
    public Deadline(String description, String by) {
        super(description);
       String[] time = by.split(" ",2); //split to at and after
        this.description = description;
       this.by = time[1];
        this.category = 'D';
        if (time[1].contains(" ")){
            time[1]=time[1].substring(0,time[1].indexOf(" "));
        }
        String[] split_date = time[1].split("/",3); //split at the dates
        date = LocalDate.of(Integer.parseInt(split_date[2]),Integer.parseInt(split_date[1]),Integer.parseInt(split_date[0]));
       // System.out.println(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String getDescription(){

        return this.description;
    }
    /**
     * Getter of deadline due
     */
    public  String getTime(){
        return date.getDayOfMonth() + "/" +  date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * Getter of character D which represents deadline
     */
    public char getD(){

        return this.category;
    }

    public  String getby(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Override of print in deadline format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

