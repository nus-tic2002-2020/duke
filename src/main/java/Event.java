import java.util.Date;

public class Event extends Task {

    //VARIABLES-----------------------------------------
    protected Date startDate;
    protected Date endDate;
    protected long durationMinutes;

    //CONSTRUCTORS--------------------------------------
    public Event(String description, Date startDate, Date endDate, Date addDate) {

        super(description, addDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = (endDate.getTime() - startDate.getTime())/60000;
    }

    public Event() {

        super();
    }

    //SET STATEMENTS------------------------------------
    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    //GET STATEMENTS------------------------------------
    public Date getStartDate() {

        return (this.startDate);
    }

    public Date getEndDate() {

        return (this.endDate);
    }

    public long getDurationMinutes() {

        return (this.durationMinutes);
    }

    public String getTaskIcon() {

        return("[E]");
    }

    //ABSTRACT TASK METHODS
    @Override
    public String getDoneAhead() {
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
