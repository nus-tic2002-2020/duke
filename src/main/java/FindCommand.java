/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command{
    private static final String FORMAT = "%1$d. %2$s";
    private static final int TASK_NUMBER = 1;
    private final static String FORMAT_ERROR_MESSAGE = "Please provide 1 keyword for me to search!!";
    private static String output;

    public FindCommand(boolean isExit, String input){
        super(isExit, input);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((commandLine.substring(5).trim()).isEmpty()) {
            throw new DukeException(FORMAT_ERROR_MESSAGE);
        }
        List<String> newTaskList = new ArrayList<>();
        output = "";
        String keyword = commandLine.split(" ")[1];
        for (int i = 0; i < taskList.getSize(); i++){
            Task task = taskList.getTasks(i);
            if(task.description.contains(keyword)){
                newTaskList.add(task.toString());
            }
        }
        if(!newTaskList.isEmpty()){
            ui.showOutput(showTaskListView(newTaskList));
        }else{
            ui.showOutput("There are no matches!");
        }
    }
    private String showTaskListView(List<String> taskList) {
        return indexedList(taskList);
    }

    private String indexedList(List<String> newTaskList) {
        return newList(newTaskList);
    }

    private static String getItem(int index, String task) {
        return String.format(FORMAT, index, task);
    }

    private static String newList(List<String> newTaskList) {
        int taskNumber = 0 + TASK_NUMBER;
        output += "Here are the matching tasks in your list:";
        for (String item : newTaskList) {
            output += " \n\t" + getItem(taskNumber, item);
            taskNumber++;
        }
        return output;
    }
}