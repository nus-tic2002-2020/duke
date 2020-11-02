/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.util.ArrayList;
public class TaskList{
    private static ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }

    public void addToTaskList(Task tasks){
        this.taskList.add(tasks);
    }

    public Task deleteFromTaskList(int tasks){
        return this.taskList.remove(tasks);
    }

    public static int getSize(){
        return taskList.size();
    }

    public static Task getTasks(int tasks){
        return taskList.get(tasks);
    }


}
