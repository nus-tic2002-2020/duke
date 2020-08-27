import java.util.Date;

public class Todo extends Task {

    //VARIABLES-----------------------------------------

    //CONSTRUCTORS--------------------------------------
    public Todo(String description, Date addDate) {

        super(description, addDate);
    }

    public Todo() {

        super();
    }

    //SET STATEMENTS------------------------------------
    @Override
    public void markAsDone(Date doneDate) {

        this.isDone = true;
        this.doneDate = doneDate;
    }

    //GET STATEMENTS------------------------------------
    public String getTaskIcon() {

        return("[T]");
    }

    //ABSTRACT TASK METHODS
    @Override
    public String getDoneAhead() {
        return null;
    }

    @Override
    public long getDurationMinutes() {
        return 0;
    }

    @Override
    public Date getEndDate() {
        return null;
    }

    @Override
    public Double getItemBudget() {
        return null;
    }

    @Override
    public Double getItemPrice() {
        return null;
    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Date getTargetDate() {
        return null;
    }

    @Override
    public String getWithinBudget() {
        return null;
    }

    @Override
    public void markAsDone(Date doneDate, Double itemPrice){

    }
}
