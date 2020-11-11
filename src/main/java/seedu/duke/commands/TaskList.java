package seedu.duke.commands;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList;

    /**
     * Creates an taskList and initialised it as an empty arraylist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates an taskList and initialised it as a specified type.
     * @param   tasks       The specified task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds the task to task list.
     * @param   description       The task added into the list.
     */
    public static void setTaskList(Task description) {
        taskList.add(description);
    }

    /**
     * Deletes the task from the task list.
     * @param    description       The task needed to delete from the task list.
     * @return   Task              The task deleted.
     */
    public static Task toDelete(int description) {
        return taskList.remove(description);
    }

    /**
     * Returns the size of the task list.
     * @return   int       The size of the task list.
     */
    public static int length() {
        return taskList.size();
    }

    /**
     * Returns the specified task from the task list.
     * @param   description   The index of the specified task.
     * @return  Task          The specified task.
     */
    public static Task getATask(int description) {
        return taskList.get(description);
    }
}