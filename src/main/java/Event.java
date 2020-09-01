import java.util.Date;

public class Event extends Task {

    //VARIABLES-----------------------------------------
    protected Date startDate = null;
    protected Date endDate = null;
    private long durationMinutes;

    //CONSTRUCTORS--------------------------------------
    public Event(int serialNum, String description, Date startDate, Date endDate, Date addDate) throws DateException {
        super(serialNum, description, addDate);
        Date now = new Date();
        if(startDate.compareTo(now) < 0 ){
            throw new DateException("StartB4Now", true);
        } else {
                this.startDate = startDate;
        }
        if(this.startDate.compareTo(endDate) > 0 ){
            throw new DateException("EndB4Start", true);
        } else {
            this.endDate = endDate;
            this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
        }
    }

    public Event() {
        super();
    }

    //SET STATEMENTS------------------------------------
    public void setStartDate(Date startDate) throws DateException {
        Date now = new Date();
        if(startDate.compareTo(now) < 0 ){
            throw new DateException("StartB4Now", false);
        } else {
            if(this.endDate != null) {
                if(startDate.compareTo(this.endDate) > 0 ) {
                    throw new DateException("StartAFEnd", false);
                } else {
                    this.startDate = startDate;
                    this.durationMinutes = (this.endDate.getTime() - startDate.getTime()) / 60000;
                }
            } else {
                this.startDate = startDate;
            }
        }
    }

    public void setEndDate(Date endDate) throws DateException {
        if(this.startDate == null){
            throw new DateException("NoStartDate", false);
        } else {
            if(this.startDate.compareTo(endDate) > 0 ){
                throw new DateException("EndB4Start", false);
            } else {
                this.endDate = endDate;
                this.durationMinutes = (endDate.getTime() - this.startDate.getTime()) / 60000;
            }
        }
    }

    //GET STATEMENTS------------------------------------
    public void printList(){
        super.printList();
        System.out.println("\t\t\tDuration : " +
                this.durationMinutes + "mins");
        System.out.println("\t\t\tFrom     : " +
                taskDate.format(this.startDate));
        System.out.println("\t\t\tTo       : " +
                taskDate.format(this.endDate));
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.doneDate));
        }
    }

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
    public Double getItemBudget() {
        return null;
    }
    public Double getItemPrice() {
        return null;
    }
    public Date getTargetDate() {
        return null;
    }
    public String getWithinBudget() {
        return null;
    }
    public void markAsDone(Date doneDate, Double itemPrice){ }
}
