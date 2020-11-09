package seedu.duke.commands;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList;

    /**
     * To create an ArrayList named taskList and initialised it.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * To add the task to task list.
     * @param description
     */
    public static void setTaskList(Task description) {
        taskList.add(description);
    }

    /**
     * To delete task from task list.
     * @param description
     * @return Task
     */
    public static Task toDelete(int description) {
        return taskList.remove(description);
    }

    /**
     * To get the size of the task list
     * @return int
     */
    public static int length() {
        return taskList.size();
    }

    /**
     * To retrieve a task from the task list
     * @param description   The number of the task wanted to retrieve.
     * @return Task
     */
    public static Task getATask(int description) {
        return taskList.get(description);
    }
}