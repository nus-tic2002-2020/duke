import java.util.Date;

public class Task {
    protected Date addDate;
    protected Date doneDate;
    protected String description;
    protected boolean isDone;

    public Task(String description, Date addDate) {
        this.addDate = addDate;
        this.description = description;
        this.isDone = false;
    }

    public Date getAddDate() {
        return (this.addDate);
    }

    public Date getDoneDate() {
        return (this.doneDate);
    }

    public String getDescription() {
        return (this.description.toString());

    }

    public String getStatusIcon() {
        if(this.isDone){
            return ("[\u2713]");
        } else {
            return ("[\u2718]");
        }
    }

    public void markAsDone(Date doneDate) {
        this.isDone = true;
        this.doneDate = doneDate;
    }
}
