package duke.task;
import java.time.LocalDate;

public class todo extends Task{
    /**Variables of todo class*/
    private char category;

    /**
     * Constructor of todo class
     *
     * @param desc Description of todo
     */
    public todo(String desc){
        super(desc);
        this.category = 'T';
    }

    public void updateDate(String new_date){
        return;
    }

    /**
     * Return category of the class
     *
     * @return Category
     */
    public char getCat(){
        return this.category;
    }
    public String getDateStr(){
        return "";
    }
    public void incrementDate(int days){
        return;
    };
    public LocalDate getDate(){
        LocalDate date = LocalDate.now();
        return date;
    }
}
