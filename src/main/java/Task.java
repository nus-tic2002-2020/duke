import java.text.SimpleDateFormat;
import java.util.Date;

public interface Task extends DukeUI {

    //SET STATEMENTS------------------------------------
    boolean markAsDone(Date doneDate);
    void setSerialNum(int serialNum);

    //GET STATEMENTS------------------------------------
    void printList();
    void printDetails();
    Date getAddDate();
    Date getDoneDate();
    String getDescription();
    String getStatusIcon();
    int getSerialNum();
    void deleteExistingNote();
    public abstract String getSaveText();

}
