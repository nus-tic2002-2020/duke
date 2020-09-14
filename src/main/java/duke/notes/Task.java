package duke.notes;

import duke.budget.*;
import duke.commands.*;
import duke.ui.*;
import java.util.Date;

public interface Task extends DukeUI {

    //SET STATEMENTS------------------------------------
    boolean markAsDone(Date doneDate) throws CommandException, DateException;
    void setSerialNum(int serialNum);
    void setDescription(String description);

    //GET STATEMENTS------------------------------------
    void printList() throws CommandException;
    void printDetails();
    Date getAddDate();
    public boolean getIsDone();
    Date getDoneDate();
    String getDescription();
    String getStatusIcon();
    int getSerialNum();
    void deleteExistingNote();
    public abstract String getSaveText();
    public abstract Budget getBudgetObject();
    public abstract String getObjectClass();
}
