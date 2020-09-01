import java.util.Date;

public class Todo extends Task {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    public Todo(int serialNum, String description, Date addDate) {
        super(serialNum, description, addDate);
    }

    public Todo() {
        super();
    }

    //SET STATEMENTS------------------------------------


    //GET STATEMENTS------------------------------------
    public void printList(){
        super.printList();
        if (this.isDone) {
            System.out.println("\t\t\tDone     : " +
                    taskDate.format(this.doneDate));
        }
    }

    public String getTaskIcon() {
        return("[T]");
    }

    //ABSTRACT TASK METHODS
    @Override
    public String getDoneAhead() { return null; }
    public long getDurationMinutes() { return 0; }
    public Date getEndDate() { return null; }
    public Double getItemBudget() { return null; }
    public Double getItemPrice() { return null; }
    public Date getStartDate() { return null; }
    public Date getTargetDate() { return null; }
    public String getWithinBudget() { return null; }
    public void markAsDone(Date doneDate, Double itemPrice){ }
}
