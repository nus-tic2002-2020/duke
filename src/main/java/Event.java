package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     * Variables of Event class
     */
    protected String at;
    private char category;
    LocalDate date;
    /**
     * Constructor of Event class
     */
    public Event(String description, String by) {
        super(description);
        String[] place = by.split(" ",2);
        //this.at = place[1];
        this.category = 'E';
        if (place[1].contains(" ")){
            place[1]=place[1].substring(0,place[1].indexOf(" ")); //removes time
        }
        String[] split_date = place[1].split("/",3); //split at the dates
        date = LocalDate.of(Integer.parseInt(split_date[2]),Integer.parseInt(split_date[1]),Integer.parseInt(split_date[0]));
        //System.out.println();
    }

    /**
     * Getter of category aka 'E' for event class
     */
    public char getD(){

        return this.category;
    }

    @Override
    public String getDescription(){

        return this.description;
    }
    /**
     * Getter of when event is at
     */
    public String getTime(){

        return date.getDayOfMonth() + "/" +  date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * Override of print statement to fit event formatting
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
