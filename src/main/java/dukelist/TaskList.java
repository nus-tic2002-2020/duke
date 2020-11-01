package dukelist;

import duketask.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of <code>TaskList</code> class, initialize a new ArrayList task list.
     *
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Return the <code>size</code> of the list.
     *
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return the <code>tasks</code> in the list.
     *
     * @return the tasks list
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Return the <code>task</code> based on the index in the list.
     *
     * @param index the index of the task to be returned
     *
     * @return the task based on the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a <code>new task</code> to the tasks.
     *
     * @param task the task which is going to be added into the task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a <code>task</code> to the tasks.
     *
     * @param taskIndex the index of the task in the task list
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Change the <code>task</code> status to done.
     *
     * @param taskIndex the index of the task in the task list
     */
    public void doneTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Find the tasks contains the <code>keyword</code> in the task list.
     *
     * @param key the String of the find keyword
     *
     * @return the task list of the tasks with the keyword
     */
    public TaskList findKey(String key) {
        TaskList results = new TaskList();

        for(Task task: tasks){
            if(task.find(key)){
                results.addTask(task);
            }
        }

        return results;
    }
}
