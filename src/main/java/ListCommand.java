/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command{
    private static final String FORMAT = "%1$d. %2$s";
    private static String output;
    public static final int TASK_NUMBER = 1;
    public ListCommand(boolean isExit, String commandLine){
        super(isExit, commandLine);
    }

    private String showTaskListView(TaskList taskList) {
        List<String> newTaskList = new ArrayList<>();
        output = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            newTaskList.add(taskList.getTasks(i).toString());
        }
        return indexedList(newTaskList);
    }

    private String indexedList(List<String> newTaskList) {
        return newList(newTaskList);
    }

    private static String getItem(int index, String task) {
        return String.format(FORMAT, index, task);
    }

    private static String newList(List<String> newTaskList) {
        int taskNumber = 0 + TASK_NUMBER;
        output += " Here are the tasks in your list:";
        for (String item : newTaskList) {
            output += " \n\t" + getItem(taskNumber, item);
            taskNumber++;
        }
        return output;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        if(taskList.getSize() == 0){
            throw new DukeException("The tasks list cannot be empty.");
        }
        ui.showOutput(showTaskListView(taskList));
    }
}