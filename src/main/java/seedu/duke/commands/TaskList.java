package seedu.duke.commands;

import java.util.ArrayList;

public class TaskList{
    public static ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }

    public static void setTaskList(Task description){
        taskList.add(description);
    }

    public static Task toDelete(int description){
        return taskList.remove(description);
    }

    public static int length(){
        return taskList.size();
    }

    public static Task getATask(int description){
        return taskList.get(description);
    }
}