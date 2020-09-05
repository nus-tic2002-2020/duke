import java.text.SimpleDateFormat;
import java.util.Date;

public interface Task extends DukeUI {

    //SET STATEMENTS------------------------------------
    boolean markAsDone(Date doneDate) throws CommandException;
    void setSerialNum(int serialNum);

    //GET STATEMENTS------------------------------------
    void printList() throws CommandException;
    void printDetails();
    Date getAddDate();
    Date getDoneDate();
    String getDescription();
    String getStatusIcon();
    int getSerialNum();
    void deleteExistingNote();
    public abstract String getSaveText();
    public abstract Budget getBudgetObject();

}
