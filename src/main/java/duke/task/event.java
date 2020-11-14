package duke.task;
import java.time.LocalDate;

public class event extends Task{
    /**Variables of event class*/
    private char category;
    private LocalDate date;

    /**
     *Constructor of event class
     *
     * @param desc Description of deadline
     * @param timing Completion time of deadline
     */
    public event(String desc, String timing){
        super(desc);
        String[] time_input = timing.split(" ", 2);
        this.category = 'E';

        //date processing
        if(time_input[1].contains(" ")){
            time_input[1] = time_input[1].substring(0, time_input[1].indexOf(" "));
        }
        String timeParse[] = time_input[1].split("/", 3);
        date = LocalDate.of(Integer.parseInt(timeParse[2]), Integer.parseInt(timeParse[1]), Integer.parseInt(timeParse[0]));
        //System.out.println("Year: " + date.getYear() + " Month: " + date.getMonthValue() + " Day: " + date.getDayOfMonth());
    }

    public void updateDate(String new_date){
        String[] timeParse = new_date.split("/", 3);
        this.date = LocalDate.of(Integer.parseInt(timeParse[2]), Integer.parseInt(timeParse[1]), Integer.parseInt(timeParse[0]));
    }


    public String getDateStr(){
        return date.getDayOfMonth() + "/" +  date.getMonthValue() + "/" + date.getYear();
    };
    /**
     * Return category of the class
     *
     * @return Category
     */
    public char getCat(){
        return this.category;
    }
    public void incrementDate(int days){
        if(days == 30){
            this.date = this.date.plusMonths(1);
            return;
        }
        this.date = this.date.plusDays(days);
    }
    public LocalDate getDate(){
        return date;
    }
}
