package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String LIST_MSG = "Here are the tasks in your list:\n\t";
    //private static ArrayList<Task> taskList = new ArrayList<Task>(100);

    public ListCommand(boolean isExit, String description) {
        super(isExit, description);
    }

    public static void getTaskList(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i< TaskList.length(); i++){
            System.out.println("\t" + (i+1) + ". " +TaskList.getATask(i).getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if(TaskList.length() == 0){
            throw new DukeException("The task list cannot be displayed.");
        }
        getTaskList();
    }
}